package com.novsky.service.commonData;

import com.novsky.dao.app.resource.ResourceRepository;
import com.novsky.dao.equipments.EquipmentsClassificationRepository;
import com.novsky.dao.equipments.EquipmentsRepository;
import com.novsky.dao.equipments.VeqClassRepository;
import com.novsky.dao.equipments.VequipmentsRepository;
import com.novsky.dao.line.LineRepository;
import com.novsky.dao.line.StationRepository;
import com.novsky.dao.locations.LocationsRepository;
import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.novsky.dao.person.PersonRepository;
import com.novsky.domain.app.resoure.Resource;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.equipments.VeqClass;
import com.novsky.domain.equipments.Vequipments;
import com.novsky.domain.line.Line;
import com.novsky.domain.line.Station;
import com.novsky.domain.locations.Locations;
import com.novsky.domain.locations.Vlocations;
import com.novsky.domain.person.Person;
import com.novsky.domain.units.Units;
import com.novsky.domain.user.User;
import com.novsky.domain.workOrder.WorkOrderReportCart;
import com.novsky.object.ListObject;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.BaseService;
import com.novsky.service.line.LineService;
import com.novsky.service.line.StationService;
import com.novsky.service.locations.LocationsService;
import com.novsky.service.workOrder.WorkOrderReportCartService;
import com.novsky.utils.CommonStatusType;
import com.novsky.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 */
@Service
@CacheConfig
public class CommonDataService extends BaseService {

    @Autowired
    LocationsRepository locationsRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;

    @Autowired
    VeqClassRepository veqClassRepository;

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    LineRepository lineRepository;

    @Autowired
    StationRepository stationRepository;


    @Autowired
    VequipmentsRepository vequipmentsRepository;

    @Autowired
    EquipmentsRepository equipmentsRepository;

    @Autowired
    LocationsService locationsService;

    @Autowired
    StationService stationService;

    @Autowired
    LineService lineService;

    @Autowired
    OutsourcingUnitRepository unitRepository;


    @Autowired
    WorkOrderReportCartService workOrderReportCartService;


    /**
     * @param location 位置编号
     * @return 查询我的下属位置信息
     * 先从session中找  如果失败再做查询
     */
    public List<Locations> findMyLocation(String location, HttpSession httpSession) {
        List<Locations> locationsList = null;
        Object object = httpSession.getAttribute("locationsList");
        if (object != null) {
            locationsList = (ArrayList<Locations>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
        } else {
            if (location != null && !location.equals("")) {
                locationsList = locationsRepository.findByLocationStartingWith(location);
                log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
            }
        }
        return locationsList;
    }


    /**
     * @param location 位置编号
     * @return 查询我的下属位置信息
     * 先从session中找  如果失败再做查询
     */
    public List<Vlocations> findMyVLocation(String location) {
        List<Vlocations> locationsList = vlocationsRepository.findByLocationStartingWith(location);
        return locationsList;
    }


    /**
     * @param location    位置编号
     * @param httpSession 查询位置我的视图信息
     * @return 查询我的下属位置设备信息
     * 先从session中找  如果失败再做查询
     */
    public List<Vequipments> findMyVeqs(String location, HttpSession httpSession) {
        List<Vequipments> vequipmentsList = null;
        Object object = httpSession.getAttribute("vequipmentsList");
        if (object != null) {
            vequipmentsList = (ArrayList<Vequipments>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
        } else {
            if (location != null && !location.equals("")) {
                vequipmentsList = vequipmentsRepository.findByLocationStartingWith(location);
                log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
            }
        }
        return vequipmentsList;
    }


    /**
     * @return 查询设备种类信息
     */
    @Cacheable(key = "'myEqClasses'", value = "myEqClasses")
    public List<EquipmentsClassification> findEquipmentsClassification() {
        List<EquipmentsClassification> equipmentsClassificationList = equipmentsClassificationRepository.findAll();
        return equipmentsClassificationList;


    }


    /**
     * @return 查询设备种类信息
     */
    @Cacheable(key = "'myVEqClasses'", value = "myVEqClasses")
    public List<VeqClass> findVeqClass() {
        List<VeqClass> eqClassList = veqClassRepository.findAll();
        log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备种类视图");
        return eqClassList;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Resource> findMenus(HttpSession httpSession) {
        List<Resource> menusList = null;
        Object object = httpSession.getAttribute("menusList");
        if (object != null) {
            menusList = (ArrayList<Resource>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询菜单");
        } else {
            menusList = resourceRepository.findByResourceLevel(1L);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询菜单");
            httpSession.setAttribute("menusList", menusList);
            log.info(this.getClass().getCanonicalName() + "------------将菜单放入缓存");
        }
        return menusList;


    }


    /**
     * @param httpSession
     * @return (0:停用 1:投用 2报废)
     */
    public List<ListObject> getEqStatus(HttpSession httpSession) {
        List<ListObject> eqStatusList = new ArrayList<ListObject>();
        Object object = httpSession.getAttribute("eqStatusList");
        if (object != null) {
            eqStatusList = (ArrayList<ListObject>) httpSession.getAttribute("eqStatusList");
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询设备状态");
        } else {
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备状态");
            eqStatusList.add(new ListObject("0", "维修"));
            eqStatusList.add(new ListObject("1", "投用"));
            eqStatusList.add(new ListObject("2", "报废"));
            log.info(this.getClass().getCanonicalName() + "------------设备状态放入缓存");
            httpSession.setAttribute("eqStatusList", eqStatusList);
        }
        return eqStatusList;

    }


    /**
     * @param httpSession
     * @return 获取运行状态 (0:停止 1:运行)
     */
    public List<ListObject> getRunningStatus(HttpSession httpSession) {
        List<ListObject> eqRunStatusList = new ArrayList<ListObject>();
        Object object = httpSession.getAttribute("eqRunStatusList");
        if (object != null) {
            eqRunStatusList = (ArrayList<ListObject>) httpSession.getAttribute("eqRunStatusList");
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询设备运行状态");
        } else {
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备运行状态");
            eqRunStatusList.add(new ListObject("0", "停止"));
            eqRunStatusList.add(new ListObject("1", "运行"));
            log.info(this.getClass().getCanonicalName() + "------------设备运行状态放入缓存");
            httpSession.setAttribute("eqRunStatusList", eqRunStatusList);
        }
        return eqRunStatusList;

    }


    /**
     * @return 查询设备种类信息
     */
    @Cacheable(value = "person", key = "'person'")
    public List<Person> findActivePerson() {
        List<Person> activePerson = personRepository.findByStatus(CommonStatusType.STATUS_YES);
        log.info(this.getClass().getCanonicalName() + "------------从数据库中查询人员");
        return activePerson;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Station> findStations(HttpSession httpSession) {
        List<Station> stationList = null;
        Object object = httpSession.getAttribute("stationList");
        if (object != null) {
            stationList = (ArrayList<Station>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询车站");
        } else {
            stationList = stationRepository.findByStatus(CommonStatusType.STATUS_YES);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询车站");
            httpSession.setAttribute("stationList", stationList);
            log.info(this.getClass().getCanonicalName() + "------------将车站放入缓存");
        }
        return stationList;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Units> findUnits(HttpSession httpSession) {
        List<Units> unitsList = null;
        Object object = httpSession.getAttribute("unitsList");
        if (object != null) {
            unitsList = (ArrayList<Units>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询外委单位");
        } else {
            unitsList = unitRepository.findByStatus(CommonStatusType.STATUS_YES);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询外委单位");
            httpSession.setAttribute("unitsList", unitsList);
            log.info(this.getClass().getCanonicalName() + "------------将外委单位放入缓存");
        }
        return unitsList;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Line> findLines(HttpSession httpSession) {
        List<Line> lineList = null;
        Object object = httpSession.getAttribute("lineList");
        if (object != null) {
            lineList = (ArrayList<Line>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询线路");
        } else {
            lineList = lineRepository.findByStatus(CommonStatusType.STATUS_YES);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询线路");
            httpSession.setAttribute("lineList", lineList);
            log.info(this.getClass().getCanonicalName() + "------------将线路放入缓存");
        }
        return lineList;
    }

    /**
     * @return 获取服务器当前时间
     */
    public String getServerDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }


    /**
     * @param result      返回结果
     * @param successDesc 执行成功后描述
     * @param failureDesc 执行失败时描述
     * @return
     */
    public ReturnObject getReturnType(Boolean result, String successDesc, String failureDesc) {
        ReturnObject returnObject = new ReturnObject();
        String resultDesc = result ? successDesc : failureDesc;
        returnObject.setResult(result);
        returnObject.setResultDesc(resultDesc);
        return returnObject;
    }


    /**
     * @param id 位置编号
     * @return 查询位置下对应的设备分类
     */
    public List<VeqClass> findEqClassesByLocationId(Long id) {
        return equipmentsRepository.findEqClassesByLocationId(id);
    }


    /**
     * @param lid
     * @param cid
     * @return 查询位置下对应的设备分类
     */
    public List<Vequipments> findEqByLocIdAndEqcId(Long lid, Long cid) {
        return equipmentsRepository.findEqByLocIdAndEqcId(lid, cid);
    }


    /**
     * @param session 会话
     * @return 重载session值
     */
    public Boolean reload(User currentUser, HttpSession session) {

        List<Line> lineList = lineService.findByStatus("1");
        List<Station> stationList = stationService.findByStatus("1");
        List<Vlocations> locationsList = locationsService.findByLocationStartingWithAndStatus(currentUser.getLocation());
        List<Locations> locList = locationsService.findByLocationStartingWithAndStatus(currentUser.getLocation(), "1");
        List<VeqClass> veqClassList = veqClassRepository.findAll();
        List<Resource> menusList = findMenus(session);
        session.setAttribute("locationsList", locationsList);
        session.setAttribute("locList", locList);
        session.setAttribute("veqClassList", veqClassList);
        session.setAttribute("lineList", lineList);
        session.setAttribute("stationList", stationList);
        session.setAttribute("menusList", menusList);

        return true;
    }


    /**
     * @return 根据序号生成
     */
    public String genWorkOrderLineNo() {
        //先查询该月工单数量
        String workOrderLineNo;
        String startNo = DateUtils.convertDate2Str(new Date(), "yyMM");
        //工单模糊查询数量
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.findByOrderLineNoContaining(startNo);
        if (workOrderReportCartList.size() > 0) {
            // 如果本月存在工单  取单号最大的
            workOrderLineNo = workOrderReportCartList.get(workOrderReportCartList.size() - 1).getOrderLineNo();
            int len = 8;
            String endNo = workOrderLineNo.substring(len - 4);
            long index = Long.parseLong(endNo) + 1;
            String endStr = index + "";
            for (int i = endStr.length(); i < 4; i++) {
                endStr = "0" + endStr;
            }
            workOrderLineNo = startNo + endStr;
        } else {
            //如果不存在工单，从startNo+0001开始
            workOrderLineNo = startNo + "0001";
        }
        return workOrderLineNo;

    }
}
