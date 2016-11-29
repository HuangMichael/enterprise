package com.linkbit.beidou.service.workOrder;

import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.dao.equipments.EquipmentsRepository;
import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.dao.workOrder.VworkOrderReportBillRepository;
import com.linkbit.beidou.dao.workOrder.WorkOrderHistoryRepository;
import com.linkbit.beidou.dao.workOrder.WorkOrderReportCartRepository;
import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.workOrder.VworkOrderReportBill;
import com.linkbit.beidou.domain.workOrder.WorkOrderHistory;
import com.linkbit.beidou.domain.workOrder.WorkOrderReportCart;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.equipments.EquipmentAccountService;
import com.linkbit.beidou.service.locations.LocationsService;
import com.linkbit.beidou.utils.CommonStatusType;
import com.linkbit.beidou.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * 报修车业务类
 */
@Service
public class WorkOrderReportCartService extends BaseService {
    @Autowired
    WorkOrderReportCartRepository workOrderReportCartRepository;

    @Autowired
    EquipmentsRepository equipmentsRepository;

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;


    @Autowired
    LocationsService locationsService;

    @Autowired
    EquipmentAccountService equipmentAccountService;

    @Autowired
    VlocationsRepository vlocationsRepository;

    @Autowired
    WorkOrderHistoryRepository workOrderHistoryRepository;

    @Autowired
    VworkOrderReportBillRepository vworkOrderReportBillRepository;
    @Autowired
    WorkOrderFixService workOrderFixService;
    @Autowired
    CommonDataService commonDataService;

    /**
     * @param equipmentId
     * @param userName
     * @return 将设备报修信息加入报修车
     */
    @Transactional
    public WorkOrderReportCart add2Cart(Long equipmentId, String userName) {
        Equipments equipments = equipmentsRepository.findById(equipmentId);
        WorkOrderReportCart workOrderReportCart = new WorkOrderReportCart();
        workOrderReportCart.setEquipments(equipments);
        //生成跟踪号
        String workOrderNo = commonDataService.genWorkOrderLineNo();
        workOrderReportCart.setOrderLineNo(workOrderNo);
        workOrderReportCart.setLocations(equipments.getLocations());
        workOrderReportCart.setLocation(equipments.getLocations().getLocation());
        workOrderReportCart.setEquipmentsClassification(equipments.getEquipmentsClassification());
        workOrderReportCart.setOrderDesc(equipments.getEquipmentsClassification().getDescription() + "报修");
        workOrderReportCart.setReporter(userName);
        workOrderReportCart.setCreator(userName);
        workOrderReportCart.setReportTime(new Date());
        workOrderReportCart.setLastStatusTime(new Date());
        workOrderReportCart.setNodeState("报修车");
        workOrderReportCart.setReportType(CommonStatusType.REPORT_BY_EQ);  //报修类型为设备报修
        workOrderReportCart.setStatus(CommonStatusType.CART_CREATED);
        workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);
        //加入关联位置信息
        workOrderReportCart.setVlocations(vlocationsRepository.findById(equipments.getLocations().getId()));
        equipments.setStatus(CommonStatusType.EQ_ABNORMAL); //将设备状态修改为不正常
        equipmentAccountService.save(equipments);
        WorkOrderHistory workOrderHistory = new WorkOrderHistory();
        workOrderHistory.setNodeDesc("报修车");
        workOrderHistory.setNodeTime(workOrderReportCart.getReportTime());
        workOrderHistory.setWorkOrderReportCart(workOrderReportCart);
        workOrderHistory.setStatus("1");
        workOrderHistoryRepository.save(workOrderHistory);
        return workOrderReportCart;
    }


    /**
     * @param locationId 位置id
     * @param orderDesc  报修描述
     * @param creator    录入人
     * @param reporter   报修人
     * @return 通过位置报修将设备报修信息加入报修车
     */
    @Transactional
    public WorkOrderReportCart add2LocCart(Long locationId, String orderDesc, String creator, String reporter, Long eqClassId) {

        WorkOrderReportCart workOrderReportCart = new WorkOrderReportCart();
        workOrderReportCart.setEquipments(null);
        //生成跟踪号

        String workOrderNo = commonDataService.genWorkOrderLineNo();
        workOrderReportCart.setOrderLineNo(workOrderNo);
        Locations locations = locationsService.findById(locationId);
        if (locations != null) {
            workOrderReportCart.setLocations(locations);
            workOrderReportCart.setLocation(locations.getLocation());
        }
        workOrderReportCart.setEquipmentsClassification(null);
        workOrderReportCart.setReporter(reporter);
        workOrderReportCart.setCreator(creator);
        workOrderReportCart.setEquipmentsClassification(equipmentsClassificationRepository.findById(eqClassId));
        workOrderReportCart.setReportTime(new Date());
        workOrderReportCart.setNodeState("报修车");
        workOrderReportCart.setLastStatusTime(new Date());//加入最新状态时间字段
        workOrderReportCart.setOrderDesc(orderDesc);
        workOrderReportCart.setReportType(CommonStatusType.REPORT_BY_LOC); //根据位置报修
        workOrderReportCart.setStatus(CommonStatusType.CART_CREATED);
        //加入关联位置信息
        workOrderReportCart.setVlocations(vlocationsRepository.findById(locationId));
        workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);

        WorkOrderHistory workOrderHistory = new WorkOrderHistory();
        workOrderHistory.setNodeDesc("报修车");
        workOrderHistory.setNodeTime(new Date());
        workOrderHistory.setWorkOrderReportCart(workOrderReportCart);
        workOrderHistory.setStatus("1");
        workOrderHistoryRepository.save(workOrderHistory);

        if (!locations.getStatus().equals(CommonStatusType.LOC_ABNORMAL)) {
            locations.setStatus(CommonStatusType.LOC_ABNORMAL); //位置不正常
            locationsService.save(locations); //报修之后更改位置状态为不正常状态
        }
        return workOrderReportCart;
    }


    /**
     * @param locationId 位置信息
     * @return 位置按照locationId查询是否有未完成的维修任务
     */
    public List<WorkOrderReportCart> checkLocationBeforeAdd2Cart(Long locationId) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartRepository.findByNocompletedLocations(locationId, CommonStatusType.ORDER_FIXED);
        return workOrderReportCartList;
    }


    /**
     * @param equipmentId 设备id
     * @return 设备按照设备equipmentId查询是否有未完成的维修任务
     */
    public List<WorkOrderReportCart> checkEquipmentBeforeAdd2Cart(Long equipmentId) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartRepository.findByNocompletedEquipments(equipmentId, CommonStatusType.ORDER_FIXED);
        return workOrderReportCartList;


    }


    /**
     * @param equipmentId 设备id
     * @return 设备按照设备equipmentId查询是否有未完成的维修任务
     */
    public List<WorkOrderReportCart> checkEqsBeforeAdd2Cart(Long equipmentId) {
        Equipments equipment = equipmentsRepository.findById(equipmentId);
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartRepository.findByEquipmentsAndNodeStateNot(equipment, "已完工");
        return workOrderReportCartList;
    }


    /**
     * @param locations 位置编号
     * @return 设备按照设备equipmentId查询是否有未完成的维修任务
     */
    public List<WorkOrderReportCart> checkLocsBeforeAdd2Cart(String locations) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartRepository.findByLocationStartingWithAndNodeStateNot(locations, "已完工");
        return workOrderReportCartList;
    }

    /**
     * @param status
     * @return 根据状态查询所有报修车信息
     */
    public List<WorkOrderReportCart> findByStatus(String status) {
        return workOrderReportCartRepository.findByStatus(status);
    }


    /**
     * @param location
     * @param status
     * @return 根据状态查询所有报修车信息
     */
    public List<WorkOrderReportCart> findByLocationsAndStatus(String location, String status) {
        List<WorkOrderReportCart> workOrderReportCartList = null;
        if (location != null) {
            workOrderReportCartList = workOrderReportCartRepository.findByLocationStartingWithAndStatus(location, status);
        }
        return workOrderReportCartList;
    }


    /**
     * @param location
     * @param nodeState
     * @return 根据状态查询所有报修车信息
     */
    public List<WorkOrderReportCart> findByLocationStartingWithAndNodeState(String location, String nodeState) {
        List<WorkOrderReportCart> workOrderReportCartList = null;
        if (location != null) {
            workOrderReportCartList = workOrderReportCartRepository.findByLocationStartingWithAndNodeState(location, nodeState);
        }
        return workOrderReportCartList;
    }


    /**
     * @param orderDesc
     * @param pageable
     * @return 根据状态查询所有报修车信息
     *//*
    public Page<WorkOrderReportCart> findByOrderDescContainingAndReportType(String orderDesc, String reportType, Pageable pageable) {
        Page<WorkOrderReportCart> workOrderReportCartList = null;
        if (orderDesc != null) {
            workOrderReportCartList = workOrderReportCartRepository.findByOrderDescContainingAndReportType(orderDesc, reportType, pageable);
        }
        return workOrderReportCartList;
    }

    *//**
     * @param location
     * @return 根据状态查询所有报修车信息
     *//*
    public List<WorkOrderReportCart> findByLocationStartingWith(String location) {
        List<WorkOrderReportCart> workOrderReportCartList = null;
        if (location != null) {
            workOrderReportCartList = workOrderReportCartRepository.findByLocationStartingWith(location);
        }
        return workOrderReportCartList;
    }*/


    /**
     * @param personName
     * @return 查询我的购物车
     */
    public List<WorkOrderReportCart> findMyCart(String personName) {
        return workOrderReportCartRepository.findMyCart(personName);
    }

    /**
     * @param personName
     * @return 查询我的购物车
     */
    public Long findMyCartSize(String personName) {
        return workOrderReportCartRepository.findMyCartSize(personName);
    }


    /**
     * @param id
     * @return 查询我的购物车
     */
    public WorkOrderReportCart findById(Long id) {
        return workOrderReportCartRepository.findById(id);
    }


    /**
     * @param id
     * @return 查询我的购物车
     */
    public WorkOrderReportCart delCart(Long id) {
        if (id != null) {
            workOrderReportCartRepository.delete(id);
        }
        return workOrderReportCartRepository.findById(id);
    }


    /**
     * @param ids
     * @return 根据id查询维修车信息集合
     */
    public List<WorkOrderReportCart> findWorkOrderReportCartByIds(String ids) {
        List<WorkOrderReportCart> workOrderReportCartList = new ArrayList<WorkOrderReportCart>();
        List<Long> longList = StringUtils.str2List(ids, ",");
        for (Long l : longList) {
            workOrderReportCartList.add(workOrderReportCartRepository.findById(l));
        }
        return workOrderReportCartList;
    }


    /**
     * @param id
     * @param orderDesc
     * @return 根据id更新维修描述
     */
    public WorkOrderReportCart updateOrderDesc(Long id, String orderDesc) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(id);
        workOrderReportCart.setOrderDesc(orderDesc);
        workOrderReportCart.setNodeState("已报修");
        workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);
        return workOrderReportCart;

    }

    /**
     * @param id
     * @param reporter
     * @return 根据id更新报修人
     */
    public WorkOrderReportCart updateReporter(Long id, String reporter) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(id);
        workOrderReportCart.setReporter(reporter);
        workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);
        return workOrderReportCart;

    }

    /**
     * @param id
     * @param reportTime
     * @return 根据id更新报修人
     */
    public WorkOrderReportCart updateReportTime(Long id, Date reportTime) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(id);
        workOrderReportCart.setReportTime(reportTime);
        workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);
        return workOrderReportCart;

    }


    /**
     * @param offset 月份偏移量
     * @return 查询当月报修的设备分类的前5
     */
    public List<Object> findTopNReportCartByEqClass(int offset) {
        return workOrderReportCartRepository.findTopNReportCartByEqClass(offset);
    }


    /**
     * @return 查询过去N个月的名称
     */
    public List<String> getLastNMonthName(int n) {
        Calendar today = Calendar.getInstance();
        int thisMonth = today.get(Calendar.MONTH);
        List<String> monthsNameList = new ArrayList<String>();

        for (int i = -1; i <= 1; i++) {
            monthsNameList.add((thisMonth - i) + "月");
        }
        return monthsNameList;
    }


    public List<WorkOrderReportCart> findWorkOrderReportDetailByIds(String ids) {
        List<Long> longList = StringUtils.str2List(ids, ",");
        return workOrderReportCartRepository.findWorkOrderReportDetailByIds(longList);
    }


    public Page<VworkOrderReportBill> findAll(Pageable pageable) {

        return vworkOrderReportBillRepository.findAll(pageable);
    }


    public List<VworkOrderReportBill> findAll() {

        return vworkOrderReportBillRepository.findAll();
    }


    public Long selectCount() {
        return vworkOrderReportBillRepository.selectCount();
    }


    /**
     * @param dateStr 模糊查询工单编号
     * @return
     */
    public List<WorkOrderReportCart> findByOrderLineNoContaining(String dateStr) {
        return workOrderReportCartRepository.findByOrderLineNoContaining(dateStr);
    }


    public List<VworkOrderReportBill> findByNodeState(String nodeState) {

        return vworkOrderReportBillRepository.findByNodeState(nodeState);
    }

    public Page<VworkOrderReportBill> findByNodeState(String nodeState, Pageable pageable) {

        return vworkOrderReportBillRepository.findByNodeState(nodeState, pageable);
    }


    /**
     * @return 查询 将要过期的
     */
    public List<WorkOrderReportCart> findBeingExpired() {

        //查询expired=0 的记录
        return workOrderReportCartRepository.findByExpiredAndStatusAndNodeState(false, CommonStatusType.STATUS_YES, "已派工");


    }


    /**
     * @param workOrderReportCartList 未过期工单集合
     */
    public int handleExpiredOrders(List<WorkOrderReportCart> workOrderReportCartList) {

        Date now = new Date();
        int dealNum = 0;
        //对其时间进行处理  并与当前时间比较
        for (WorkOrderReportCart workOrder : workOrderReportCartList) {
            if ((workOrder.getDeadLine() != null) && workOrder.getDeadLine().before(now)) {
                workOrder.setExpired(true);
                workOrderReportCartRepository.save(workOrder);
                dealNum++;
            } else {
                log.info("do nothing with this workOrder");
            }
        }

        return dealNum;
    }
}

