package com.novsky.service.workOrder;

import com.novsky.dao.equipments.EquipmentsRepository;
import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.novsky.dao.workOrder.*;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.units.Units;
import com.novsky.service.app.BaseService;
import com.novsky.utils.StringUtils;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.units.Units;
import com.novsky.domain.workOrder.*;
import com.novsky.service.app.BaseService;
import com.novsky.utils.StringUtils;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin  on 2016/5/20.
 */
@Service
public class WorkOrderReportService extends BaseService implements SortedSearchable {


    @Autowired
    EquipmentsRepository equipmentsRepository;

    @Autowired
    WorkOrderReportCartRepository workOrderReportCartRepository;

    @Autowired
    OutsourcingUnitRepository outsourcingUnitRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;
    @Autowired
    VworkOrderNumReportRepository vworkOrderNumReportRepository;

    @Autowired
    VworkOrderNumFinishRepository vworkOrderNumFinishRepository;

    @Autowired
    WorkOrderHistoryRepository workOrderHistoryRepository;

    @Autowired
    WorkOrderFixService workOrderFixService;


    @Autowired
    VworkOrderReportBillRepository vworkOrderReportBillRepository;

    /**
     * @param ids 选中的报修车列表id集合
     * @return
     */

    public List<WorkOrderReportCart> generateReport(String ids) {
        List<Long> idList = new ArrayList<Long>();
        if (ids != null && !ids.equals("")) {
            idList = StringUtils.str2List(ids, ",");
        }
        List<WorkOrderReportCart> workOrderReportCartList = new ArrayList<WorkOrderReportCart>();
        WorkOrderReportCart workOrderReportCart;
        for (Long id : idList) {
            workOrderReportCart = workOrderReportCartRepository.findById(id);
            workOrderReportCart.setNodeState("已报修");
            workOrderReportCart.setDeadLine(workOrderFixService.getDeadLineByEqClass(workOrderReportCart));
            workOrderReportCart.setLastStatusTime(new Date());//更新最新状态时间字段
            workOrderReportCart.setStatus("1");
            workOrderReportCartRepository.save(workOrderReportCart);
            workOrderFixService.updateNodeStatus(workOrderReportCart);
            WorkOrderHistory workOrderHistory = new WorkOrderHistory();
            workOrderHistory.setNodeDesc("已报修");
            workOrderHistory.setNodeTime(new Date());
            workOrderHistory.setStatus("1");
            workOrderHistory.setWorkOrderReportCart(workOrderReportCart);
            workOrderHistoryRepository.save(workOrderHistory);
        }
        return workOrderReportCartList;
    }


    /**
     * @return 根据维修单位Id规约
     * @version 0.1
     */

    public List mapByUnitId(String ids) {
        List<WorkOrderReportCart> workOrderReportCartList = new ArrayList<WorkOrderReportCart>();
        List<Long> idsList = StringUtils.str2List(ids, ",");
        for (Long id : idsList) {
            WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(id);
            workOrderReportCart.setNodeState("已派工");
            workOrderReportCart = setDefaultUnit(workOrderReportCart);//设置默认的维修单位
            workOrderReportCart.setLastStatusTime(new Date());//加入最新状态时间
            workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);
            workOrderFixService.updateNodeStatus(workOrderReportCart);
            //将已报修状态修改为0
            WorkOrderHistory workOrderHistory = new WorkOrderHistory();
            workOrderHistory.setNodeDesc("已派工");
            workOrderHistory.setNodeTime(new Date());
            workOrderHistory.setStatus("1");
            workOrderHistory.setWorkOrderReportCart(workOrderReportCart);
            workOrderHistoryRepository.save(workOrderHistory);
            workOrderReportCart = workOrderReportCartRepository.save(workOrderReportCart);
            workOrderReportCartList.add(workOrderReportCart);
        }
        return workOrderReportCartList;

    }

    /**
     * @param equipmentsClassification
     * @return 根据设备分类信息获取维修单位
     */
    public Units getDefaultUnitByEqClass(EquipmentsClassification equipmentsClassification) {
        List<Units> unitList = equipmentsClassification.getUnitSet();
        if (!unitList.isEmpty()) {
            return unitList.get(0);
        } else {
            return outsourcingUnitRepository.findAll().get(0);
        }
    }


    /**
     * @return 查询近期三个月的报修单数量
     */
    //  * @param location 查询当前用户的位置下的报修数量统计
    public List<VworkOrderNumReport> selectReportNumIn3Months() {
        return vworkOrderNumReportRepository.findAll();
    }

    /**
     * @return 查询近期三个月的报修单数量
     */
    //  * @param location 查询当前用户的位置下的报修数量统计
    public List<VworkOrderNumFinish> selectFinishNumIn3Months() {
        return vworkOrderNumFinishRepository.findAll();
    }


    public WorkOrderReportCart setDefaultUnit(WorkOrderReportCart workOrderReportCart) {
        EquipmentsClassification equipmentsClassification = workOrderReportCart.getEquipmentsClassification();

        if (equipmentsClassification != null) {
            workOrderReportCart.setUnit(getDefaultUnitByEqClass(equipmentsClassification));
        }
        return workOrderReportCart;
    }






    /**
     * @param searchPhrase   查询关键字
     * @param pageable 可分页
     * @return 根据条件查询
     */
    public Page<VworkOrderReportBill> findByConditions(String searchPhrase, int paramsSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vworkOrderReportBillRepository.findByOrderLineNoContainsAndOrderDescContainsAndLocationContainsAndEqClassContains(array[0], array[1], array[2], array[3], pageable);
    }


    /**
     * @param searchPhrase   查询关键字
     * @return 根据条件查询
     */
    public List<VworkOrderReportBill> findByConditions(String searchPhrase, int paramsSize) {

        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vworkOrderReportBillRepository.findByOrderLineNoContainsAndOrderDescContainsAndLocationContainsAndEqClassContains(array[0], array[1], array[2], array[3]);

    }
}
