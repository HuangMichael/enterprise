package com.novsky.controller.workOrder;


import com.novsky.controller.common.BaseController;
import com.novsky.dao.workOrder.VworkOrderReportBillRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.equipments.VEqUpdateBill;
import com.novsky.domain.equipments.Vequipments;
import com.novsky.domain.user.User;
import com.novsky.domain.workOrder.VworkOrderReportBill;
import com.novsky.domain.workOrder.WorkOrderReportCart;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.locations.LocationsService;
import com.novsky.service.workOrder.WorkOrderReportCartService;
import com.novsky.service.workOrder.WorkOrderReportService;
import com.novsky.utils.DateUtils;
import com.novsky.utils.PageUtils;
import com.novsky.utils.SessionUtil;
import com.novsky.utils.StringUtils;
import com.novsky.utils.export.docType.ExcelDoc;
import com.novsky.utils.export.exporter.DataExport;
import com.novsky.utils.export.exporter.ExcelDataExporter;
import com.novsky.dao.workOrder.VworkOrderReportBillRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.user.User;
import com.novsky.domain.workOrder.VworkOrderReportBill;
import com.novsky.domain.workOrder.WorkOrderReportCart;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.locations.LocationsService;
import com.novsky.service.workOrder.WorkOrderReportCartService;
import com.novsky.service.workOrder.WorkOrderReportService;
import com.novsky.utils.DateUtils;
import com.novsky.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/4/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/workOrderReportCart")
public class WorkOrderReportCartController extends BaseController {

    @Autowired
    WorkOrderReportCartService workOrderReportCartService;
    @Autowired
    LocationsService locationsService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    WorkOrderReportService workOrderReportService;
    @Autowired
    VworkOrderReportBillRepository vworkOrderReportBillRepository;


    /**
     * 显示所有的报修车列表信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpSession httpSession) {

        super.list(httpSession, modelMap);


        User user = SessionUtil.getCurrentUserBySession(httpSession);
        String userLocation = user.getLocation();
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.findByLocationStartingWithAndNodeState(userLocation, "报修车");
        modelMap.put("workOrderReportCartList", workOrderReportCartList);
        return "/workOrderReportCart/list";
    }


    /**
     * 显示所有的报修车列表信息
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage list2(@RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount) {
        long reportCartListSize = workOrderReportCartService.selectCount();
        Page<VworkOrderReportBill> page = workOrderReportCartService.findAll(new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(reportCartListSize);
        return myPage;
    }


    /**
     * @param equipmentId 设备id
     * @return 在加入报修车之前检查是否已有该设备报修未完成的维修任务
     */
    @RequestMapping(value = "/checkEq/{equipmentId}", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkOrderReportCart> checkBeforeAdd2Cart(@PathVariable("equipmentId") Long equipmentId) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.checkEquipmentBeforeAdd2Cart(equipmentId);
        return workOrderReportCartList;
    }

    /**
     * @param equipmentId 设备id
     * @return 在加入报修车之前检查是否已有该设备报修未完成的维修任务 objectList
     */
    @RequestMapping(value = "/checkEqs/{equipmentId}", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkOrderReportCart> checkEqBeforeAdd2Cart(@PathVariable("equipmentId") Long equipmentId) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.checkEqsBeforeAdd2Cart(equipmentId);
        return workOrderReportCartList;
    }


    /**
     * @param equipmentId 设备id
     * @return 在加入报修车之前检查是否已有该设备报修未完成的维修任务 objectList
     */
    @RequestMapping(value = "/loadReportedEqPage/{equipmentId}", method = RequestMethod.GET)
    public String loadReportEqPage(@PathVariable("equipmentId") Long equipmentId, ModelMap modelMap) {
        List<WorkOrderReportCart> reportedEqList = workOrderReportCartService.checkEqsBeforeAdd2Cart(equipmentId);
        modelMap.put("reportedEqList", reportedEqList);
        return "/location/eqList";
    }


    /**
     * @param location 位置编号
     * @return 在加入报修车之前检查是否已有该设备报修未完成的维修任务 objectList
     */
    @RequestMapping(value = "/loadReportedLocPage/{location}", method = RequestMethod.GET)
    public String loadReportLocPage(@PathVariable("location") String location, ModelMap modelMap) {
        List<WorkOrderReportCart> reportedList = workOrderReportCartService.checkLocsBeforeAdd2Cart(location);
        modelMap.put("reportedList", reportedList);
        return "/location/locList";
    }

    /**
     * @param locationId 位置id
     * @return 在加入报修车之前检查是否已有该位置报修未完成的维修任务
     */
    @RequestMapping(value = "/checkLoc/{locationId}", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkOrderReportCart> checkLoc(@PathVariable("locationId") Long locationId) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.checkLocationBeforeAdd2Cart(locationId);
        return workOrderReportCartList;
    }


    /**
     * 设备报修添加到报修车
     *
     * @param equipmentId 设备id
     * @param httpSession 当前会话
     * @return
     */
    @RequestMapping(value = "/add2Cart", method = RequestMethod.POST)
    @ResponseBody
    public WorkOrderReportCart add2Cart(@RequestParam("equipmentId") Long equipmentId, HttpSession httpSession) {
        String personName = (String) httpSession.getAttribute("personName");
        WorkOrderReportCart workOrderReportCart = workOrderReportCartService.add2Cart(equipmentId, personName);
        return workOrderReportCart;
    }


    /**
     * 位置报修添加到报修车
     *
     * @param locationId  位置id
     * @param orderDesc   报修描述
     * @param httpSession 当前会话
     * @return
     */
    @RequestMapping(value = "/add2LocCart", method = RequestMethod.POST)
    @ResponseBody
    public WorkOrderReportCart add2LocCart(@RequestParam("locationId") Long locationId, @RequestParam("orderDesc") String orderDesc, @RequestParam("reporter") String reporter, @RequestParam("eqClassId") Long eqClassId, HttpSession httpSession) {
        String creator = (String) httpSession.getAttribute("personName");
        WorkOrderReportCart workOrderReportCart = workOrderReportCartService.add2LocCart(locationId, orderDesc, creator, reporter, eqClassId);
        return workOrderReportCart;
    }

    /**
     * 显示所有的报修车列表信息
     */
    @RequestMapping(value = "/findMyCart/{n}", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkOrderReportCart> findMyCart(@PathVariable("n") Long n, HttpSession httpSession) {
        String personName = (String) httpSession.getAttribute("personName");
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.findMyCart(personName);
        return workOrderReportCartList;
    }


    /**
     * 显示所有的报修车列表信息
     */
    @RequestMapping(value = "/findMyCartSize", method = RequestMethod.GET)
    @ResponseBody
    public Long findMyCartSize(HttpSession httpSession) {
        String personName = (String) httpSession.getAttribute("personName");
        Long myCartSize = workOrderReportCartService.findMyCartSize(personName);
        return myCartSize;
    }


    /**
     * 加载将要提交的报修车信息
     */
    @RequestMapping(value = "/loadDetailList", method = RequestMethod.GET)
    public String loadDetailList(ModelMap modelMap) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.findByStatus("1");
        modelMap.put("workOrderReportCartList", workOrderReportCartList);
        return "/workOrderReportCart/resultList";
    }


    /**
     * 移除购物车信息
     */
    @RequestMapping(value = "/delCart", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject delCart(@RequestParam Long id) {
        ReturnObject returnObject = new ReturnObject();
        WorkOrderReportCart workOrderReportCart = workOrderReportCartService.delCart(id);
        returnObject.setResult(workOrderReportCart == null);
        String resultDesc = "报修信息移报修车";
        resultDesc += returnObject.getResult() ? "成功！" : "失败";
        returnObject.setResultDesc(resultDesc);
        return returnObject;
    }


    /**
     * 加载将要提交的报修车信息
     */
    @RequestMapping(value = "/loadOrderDesc", method = RequestMethod.POST)
    public String loadOrderDesc(@RequestParam("ids") String ids, ModelMap modelMap) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.findWorkOrderReportCartByIds(ids);
        modelMap.put("workOrderReportCartList", workOrderReportCartList);
        return "/workOrderReportCart/orderDescList";
    }


    /**
     * 加载将要提交的报修车确认信息
     */
    @RequestMapping(value = "/loadCommitPage", method = RequestMethod.POST)
    public String loadCommitPage(@RequestParam("ids") String ids, ModelMap modelMap) {
        List<WorkOrderReportCart> workOrderReportCartList = workOrderReportCartService.findWorkOrderReportCartByIds(ids);
        modelMap.put("workOrderReportCartList", workOrderReportCartList);
        return "/workOrderReportCart/orderCommitList";
    }


    /**
     * 更新维修描述
     */
    @RequestMapping(value = "/updateOrderDesc", method = RequestMethod.POST)
    @ResponseBody
    public WorkOrderReportCart updateOrderDesc(@RequestParam("id") Long id, @RequestParam("orderDesc") String orderDesc) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartService.updateOrderDesc(id, orderDesc);
        return workOrderReportCart;
    }


    /**
     * 更新报修人
     *
     * @param id
     * @param reporter
     * @return
     */
    @RequestMapping(value = "/updateReporter", method = RequestMethod.POST)
    @ResponseBody
    public WorkOrderReportCart updateReporter(@RequestParam("id") Long id, @RequestParam("reporter") String reporter) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartService.updateReporter(id, reporter);
        return workOrderReportCart;
    }


    /**
     * 更新报修时间
     */
    @RequestMapping(value = "/updateReportTime", method = RequestMethod.POST)
    @ResponseBody
    public WorkOrderReportCart updateReportTime(@RequestParam("id") Long id, @RequestParam("reportTime") String reportTime) {
        WorkOrderReportCart workOrderReportCart = null;
        try {
            Date date = DateUtils.convertStr2Date(reportTime, "yyyy-MM-dd");
            workOrderReportCart = workOrderReportCartService.updateReportTime(id, date);
        } catch (Exception e) {

        }
        return workOrderReportCart;
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
        List<VworkOrderReportBill> dataList = workOrderReportCartService.findAll();
        workOrderReportCartService.setDataList(dataList);
        workOrderReportCartService.exportExcel(request, response, docName, titles, colNames);
    }


}
