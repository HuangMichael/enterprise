package com.linkbit.beidou.controller.equipment;


import com.linkbit.beidou.controller.common.BaseController;
import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.dao.equipments.EquipmentsRepository;
import com.linkbit.beidou.dao.equipments.VequipmentsRepository;
import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.EqUpdateBill;
import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.equipments.VEqRecord;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.PageObject;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.equipments.EqUpdateBillService;
import com.linkbit.beidou.service.equipments.EquipmentAccountService;
import com.linkbit.beidou.service.equipments.EquipmentSearchService;
import com.linkbit.beidou.service.locations.LocationsService;
import com.linkbit.beidou.utils.*;
import com.linkbit.beidou.utils.export.docType.ExcelDoc;
import com.linkbit.beidou.utils.export.docType.PdfDoc;
import com.linkbit.beidou.utils.export.exporter.DataExport;
import com.linkbit.beidou.utils.export.exporter.ExcelDataExporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 设备台账控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/equipment")
public class EquipmentController extends BaseController implements LocationSeparatable {


    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    EquipmentsRepository equipmentsRepository;
    @Autowired
    EquipmentAccountService equipmentAccountService;

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;

    @Autowired
    LocationsService locationsService;

    @Autowired
    VlocationsRepository vlocationsRepository;
    @Autowired
    VequipmentsRepository vequipmentsRepository;

    @Autowired
    ResourceService resourceService;

    @Autowired
    EqUpdateBillService eqUpdateBillService;

    @Autowired
    EquipmentSearchService equipmentSearchService;
    @Autowired
    CommonDataService commonDataService;


    /**
     * 分页查询
     *
     * @param locationId
     * @return
     */
    @RequestMapping(value = "/loadByLocation/{locationId}", method = RequestMethod.GET)
    public String loadByLocationId(@PathVariable("locationId") Long locationId, ModelMap modelMap) {
        Locations locations = locationsService.findById(locationId);
        List<Vequipments> equipmentsList = vequipmentsRepository.findByLocationStartingWith(locations.getLocation());
        modelMap.put("equipmentsList", equipmentsList);
        return "/location/locEqList";
    }


    /**
     * 分页查询
     *
     * @param current      当前页
     * @param rowCount     每页条数
     * @param searchPhrase 查询关键字
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(HttpServletRequest request, @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(equipmentSearchService, searchPhrase, 4, current, rowCount, pageable);
    }


    /**
     * @param session 当前会话
     * @return 查询当前用户所在的位置下属的设备
     */
    @RequestMapping(value = "/findMyEqs")
    @ResponseBody
    public List<Vequipments> findMyEqs(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        String userLocation = user.getLocation();
        return vequipmentsRepository.findByLocationStartingWith(userLocation);
    }

    @RequestMapping(value = "/reload/{objId}")
    public String reload(@PathVariable("objId") long objId, ModelMap modelMap) {
        Equipments equipments = equipmentsRepository.findById(objId);
        modelMap.put("object", equipments);
        return "/equipment/table_1_2";
    }

    /**
     * 查询根节点
     */
    @RequestMapping(value = "/create")
    public String create(ModelMap modelMap) {
        //查询出所有的设备分类
        return "/equipment/create";
    }


    /**
     * @param equipments 保存设备信息
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(Equipments equipments) {
        Equipments eqObject = equipmentAccountService.save(equipments);
        return commonDataService.getReturnType(eqObject != null, "信息保存成功!", "信息保存失败");
    }


    /**
     * 检验设备编号是否唯一
     */
    @RequestMapping(value = "/checkExist/{eqCode}")
    @ResponseBody
    public boolean checkExist(@PathVariable("eqCode") String eqCode) {
        boolean exists = true;
        if (eqCode != null && !eqCode.equals("")) {

            exists = equipmentAccountService.checkExist(eqCode);
        }
        return exists;

    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public Equipments findById(@PathVariable("id") Long id) {
        return equipmentAccountService.findById(id);

    }


    /**
     * @param id 根据id删除设备信息
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        Equipments equipments = null;
        if (id != null) {
            equipments = equipmentAccountService.findById(id);
        }
        ReturnObject returnObject = new ReturnObject();
        returnObject.setResult(equipmentAccountService.delete(equipments));
        returnObject.setResultDesc(equipments.getId() + "");

        return returnObject;

    }


    /**
     * @param id 根据id删除设备信息
     */
    @RequestMapping(value = "/abandon/{id}")
    @ResponseBody
    public String abandon(@PathVariable("id") Long id) {
        Equipments equipments = null;
        String status = "";
        if (id != null) {
            equipments = equipmentAccountService.findById(id);
            status = equipmentAccountService.abandon(equipments);
        }
        return status;

    }


    /**
     * @param eid
     */
    @RequestMapping(value = "/findFixingStep/{eid}")
    @ResponseBody
    public List<Object> findFixingStep(@PathVariable("eid") Long eid) {
        return equipmentAccountService.findFixingStepByEid(eid);

    }

    /**
     * @param eid 设备编号
     * @return 根据设备id获取设备信息
     */
    @RequestMapping(value = "/findEquipment/{eid}")
    @ResponseBody
    public Equipments findEquipment(@PathVariable("eid") Long eid) {
        return equipmentAccountService.findById(eid);
    }

    /**
     * @param eid 设备编号
     * @return 根据设备id获取设备维修节点信息信息
     */
    @RequestMapping(value = "/getFixStepsByEid/{eid}")
    @ResponseBody
    public List<Object> getFixStepsByEid(@PathVariable("eid") Long eid) {
        return equipmentAccountService.findAllFixStepsByEid(eid);
    }


    /**
     * 查询设备对应的维修历史
     */
    @RequestMapping(value = "/loadHistory/{eid}")
    public String loadHistoryByEid(@PathVariable("eid") Long eid, ModelMap modelMap) {
        List<Object> historyList = equipmentAccountService.findFixHistoryByEid(eid);
        modelMap.put("historyList", historyList);
        return "/equipment/table_1_3";
    }


    /**
     * 查询设备对应的维修历史
     */
    @RequestMapping(value = "/loadLastHistory/{eid}")
    public String loadLastHistory(@PathVariable("eid") Long eid, ModelMap modelMap) {
        List<Object> historyList = equipmentAccountService.findLastFixHistoryByEid(eid);
        modelMap.put("historyList", historyList);
        return "/equipment/table_1_3";
    }

    /**
     * 查询设备对应的维修历史
     */
    @RequestMapping(value = "/loadFixHistory/{orderLineNo}")
    public String loadFixHistory(@PathVariable("orderLineNo") String orderLineNo, ModelMap modelMap) {
        List<Object> fixHistoryList = equipmentAccountService.findAllFixStepsByOrderLineNo(orderLineNo);
        modelMap.put("fixHistoryList", fixHistoryList);
        return "/equipment/table_1_4";
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Equipments add(
            @RequestParam("eqCode") String eqCode,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "manager", required = false) String manager,
            @RequestParam(value = "maintainer", required = false) String maintainer,
            @RequestParam(value = "productFactory", required = false) String productFactory,
            @RequestParam(value = "locations_id", required = false) Long locations_id,
            @RequestParam(value = "equipmentsClassification_id", required = false) Long equipmentsClassification_id,
            @RequestParam(value = "status", defaultValue = "1") String status
    ) {
        Equipments equipments = new Equipments();
        try {
            equipments.setEqCode(eqCode);
            equipments.setDescription(description);
            equipments.setManager(manager);
            equipments.setMaintainer(maintainer);
            equipments.setProductFactory(productFactory);
            equipments.setLocations(locationsService.findById(locations_id));
            equipments.setEquipmentsClassification(equipmentsClassificationRepository.findById(equipmentsClassification_id));
            equipments.setStatus(status);
            equipments.setLocation(equipments.getLocations().getLocation());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipmentAccountService.save(equipments);

    }

    /**
     * @param eqCode 设备编号
     * @return 查询设备编号是否存在
     */
    @RequestMapping(value = "/checkEqCodeExists/{eqCode}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkEqCodeExists(@PathVariable("eqCode") String eqCode) {
        return equipmentAccountService.eqCodeExists(eqCode);
    }


    /**
     * @param id 设备id
     * @return 根据设备id查询设备更新历史
     */
    @RequestMapping(value = "/getUpdateHistoryById/{id}", method = RequestMethod.GET)
    @ResponseBody
    List<EqUpdateBill> getUpdateHistoryById(@PathVariable("id") Long id) {
        return eqUpdateBillService.getUpdateHistoryById(id);
    }


    /**
     * @param id 设备id
     * @return 根据设备id查询设备履历
     */
    @RequestMapping(value = "/getRecordsById/{id}", method = RequestMethod.GET)
    @ResponseBody
    List<VEqRecord> getRecordsById(@PathVariable("id") Long id) {
        return eqUpdateBillService.getEqRecordsByEid(id);
    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    List<Long> findAllId() {
        return equipmentAccountService.selectAllId();
    }

    /**
     * @param request  请求
     * @param response 响应
     * @param param    查询关键字
     * @param docName  文档名称
     * @param titles   标题集合
     * @param colNames 列名称
     */
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("param") String param, @RequestParam("docName") String docName, @RequestParam("titles") String titles[], @RequestParam("colNames") String[] colNames) {
        List<Vequipments> dataList = equipmentSearchService.findByConditions(param, 4);
        equipmentSearchService.setDataList(dataList);
        equipmentSearchService.exportExcel(request, response, docName, titles, colNames);
    }
}
