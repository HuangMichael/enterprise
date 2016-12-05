package com.novsky.controller.preMaint;


import com.novsky.controller.common.BaseController;
import com.novsky.dao.preMaint.PreMaintWorkOrderRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.equipments.Vequipments;
import com.novsky.domain.preMaint.PreMaint;
import com.novsky.domain.preMaint.PreMaintWorkOrder;
import com.novsky.domain.preMaint.VpreMaint;
import com.novsky.domain.preMaint.VpreMaintOrder;
import com.novsky.domain.workOrder.WorkOrderHistory;
import com.novsky.domain.workOrder.WorkOrderReportCart;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.preMaint.PreMaintOrderSearchService;
import com.novsky.service.preMaint.PreMaintSearchService;
import com.novsky.service.preMaint.PreMaintService;
import com.novsky.service.workOrder.WorkOrderReportCartService;
import com.novsky.utils.PageUtils;
import com.novsky.dao.preMaint.PreMaintWorkOrderRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.preMaint.PreMaint;
import com.novsky.domain.preMaint.VpreMaintOrder;
import com.novsky.object.ReturnObject;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.preMaint.PreMaintOrderSearchService;
import com.novsky.service.preMaint.PreMaintService;
import com.novsky.utils.PageUtils;
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
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2016-10-21
 * PM工单调度台
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/preMaintDispatcher")
public class PreMaintDispatcherController extends BaseController {
    @Autowired
    PreMaintOrderSearchService preMaintOrderSearchService;
    @Autowired
    PreMaintService preMaintService;
    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonDataService commonDataService;

    @Autowired
    PreMaintWorkOrderRepository preMaintWorkOrderRepository;

    /**
     * 分页查询
     *
     * @param current      当前页
     * @param rowCount     每页条数
     * @param searchPhrase 查询关键字
     * @return
     */
    @RequestMapping(value = "/data/{ns}", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(HttpServletRequest request, @PathVariable("ns") int nodeState, @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        String nodeStateArray[] = {"已派工", "已完工", "已暂停", "已取消"};
        String nodeStateStr;
        if (nodeState >= 0) {
            nodeStateStr = nodeStateArray[nodeState];
            searchPhrase += nodeStateStr + ",";
        }

        System.out.println("searchPhrase--------------" + searchPhrase);
        Map<String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(preMaintOrderSearchService, searchPhrase, 3, current, rowCount, pageable);
    }


    /**
     * 根据id查询
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public PreMaint findById(@PathVariable("id") Long id) {


        return preMaintService.findById(id);
    }


    /**
     * 根据id查询
     */
    @RequestMapping(value = "/selectAllId")
    @ResponseBody
    public List<Long> selectAllId() {
        String location = this.getUserLocation();
        return preMaintService.selectAllId(location);
    }


    /**
     * 保存信息
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ReturnObject save(PreMaint preMaint) {
        preMaint = preMaintService.save(preMaint);
        return commonDataService.getReturnType(preMaint != null, "预防性维修信息保存成功", "预防性维修信息保存失败");
    }


    /**
     * 保存信息
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        Boolean result = preMaintService.delete(id);
        return commonDataService.getReturnType(result, "预防性维修信息保存成功", "预防性维修信息保存失败");
    }


    /**
     * 生成预防性维修工单
     *
     * @param pmId
     * @param deadLine
     * @return
     */
    @RequestMapping(value = "/genPmOrder")
    @ResponseBody
    public ReturnObject generatePmOrder(@RequestParam("pmId") Long pmId, @RequestParam("deadLine") String deadLine) {
        List<PreMaintWorkOrder> preMaintList = preMaintService.generatePmOrder(pmId, deadLine);
        return commonDataService.getReturnType(!preMaintList.isEmpty(), "预防性维修信息生成成功", "预防性维修信息生成失败");
    }


    /**
     * @param fixId
     * @return 完成单个维修工单
     */
    @RequestMapping(value = "/finishDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject finishDetail(@RequestParam Long fixId, @RequestParam String fixDesc) {
        PreMaintWorkOrder preMaintWorkOrder = preMaintWorkOrderRepository.findById(fixId);
        preMaintWorkOrder = preMaintService.handleWorkOrder(preMaintWorkOrder, fixDesc, "已完工");
        return commonDataService.getReturnType(preMaintWorkOrder != null, "预防性维修单完工成功!", "预防性维修单完工失败!");
    }


    /**
     * @param fixId   预防性维修单id
     * @param fixDesc 维修描述
     * @return 暂停单个维修工单
     */
    @RequestMapping(value = "/pauseDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject pauseDetail(@RequestParam Long fixId, @RequestParam String fixDesc) {
        PreMaintWorkOrder preMaintWorkOrder = preMaintWorkOrderRepository.findById(fixId);
        preMaintWorkOrder = preMaintService.handleWorkOrder(preMaintWorkOrder, fixDesc, "已暂停");
        return commonDataService.getReturnType(preMaintWorkOrder != null, "预防性维修单暂停成功!", "预防性维修单暂停失败!");
    }

    /**
     * @param fixId
     * @param fixDesc
     * @return 取消单个维修工单
     */
    @RequestMapping(value = "/abortDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject abortDetail(@RequestParam Long fixId, @RequestParam String fixDesc) {
        PreMaintWorkOrder preMaintWorkOrder = preMaintWorkOrderRepository.findById(fixId);
        preMaintWorkOrder = preMaintService.handleWorkOrder(preMaintWorkOrder, fixDesc, "已取消");
        return commonDataService.getReturnType(preMaintWorkOrder != null, "预防性维修单取消成功!", "预防性维修单取消失败!");
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
        List<VpreMaintOrder> dataList = preMaintOrderSearchService.findByConditions(param, 3);
        preMaintService.setDataList(dataList);
        preMaintService.exportExcel(request, response, docName, titles, colNames);
    }
}
