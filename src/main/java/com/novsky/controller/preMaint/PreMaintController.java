package com.novsky.controller.preMaint;


import com.novsky.controller.common.BaseController;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.app.org.SystemInfo;
import com.novsky.domain.preMaint.PreMaint;
import com.novsky.domain.preMaint.PreMaintWorkOrder;
import com.novsky.domain.preMaint.VpreMaint;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.org.SysInfoService;
import com.novsky.service.preMaint.PreMaintSearchService;
import com.novsky.service.preMaint.PreMaintService;
import com.novsky.utils.PageUtils;
import com.novsky.utils.SessionUtil;
import com.novsky.domain.preMaint.VpreMaint;
import com.novsky.service.commonData.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 预防性维修控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/preMaint")
public class PreMaintController extends BaseController {
    @Autowired
    PreMaintService preMaintService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    CommonDataService commonDataService;
    @Autowired
    SysInfoService sysInfoService;
    @Autowired
    PreMaintSearchService preMaintSearchService;

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
        return new PageUtils().searchBySortService(preMaintSearchService, searchPhrase, 2, current, rowCount, pageable);
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
    @RequestMapping(value = "/findAllIds")
    @ResponseBody
    public List<Long> selectAllId() {
        String location = getUserLocation();
        List<Long> idList = preMaintService.selectAllId(location);
        return idList;
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
        return commonDataService.getReturnType(preMaintList != null, "预防性维修信息生成成功", "预防性维修信息生成失败");
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
        List<VpreMaint> dataList = preMaintSearchService.findByConditions(param, 2);
        preMaintService.setDataList(dataList);
        preMaintService.exportExcel(request, response, docName, titles, colNames);
    }


    /**
     * 执行生成调度信息
     */
    @RequestMapping(value = "/executeGenerate")
    @ResponseBody
    public ReturnObject executeGenerating(@RequestParam("ids") String ids, @RequestParam("deadLine") String deadLine) {
        if (!ids.contains(",")) {
            ids += ",";
        }
        String idArray[] = ids.split(",");
        Long id;
        List<PreMaintWorkOrder> workOrderList = null;
        //遍历选中的计划
        for (String idStr : idArray) {
            id = Long.parseLong(idStr);
            workOrderList = preMaintService.generatePmOrder(id, deadLine);
        }
        return commonDataService.getReturnType(!workOrderList.isEmpty(), "预防性维修信息生成成功!", "预防性维修信息生成失败!");
    }


    /**
     * 返回true 调度已启用
     *
     * @return
     */
    @RequestMapping(value = "/autoScheduled")
    @ResponseBody
    public Boolean autoScheduled() {
        //首先检查调度是否启用
        SystemInfo systemInfo = sysInfoService.findBySysName("pre_maint_auto_schedule");
        return (systemInfo != null && systemInfo.getStatus().equals("1"));

    }
}
