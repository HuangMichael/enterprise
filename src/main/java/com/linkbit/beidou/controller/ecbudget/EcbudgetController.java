package com.linkbit.beidou.controller.ecbudget;


import com.linkbit.beidou.controller.common.BaseController;
import com.linkbit.beidou.domain.EcBudget.EcBudgetBill;
import com.linkbit.beidou.domain.EcBudget.VEcBudgetBill;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.domain.matCost.MatCost;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.budge.EcBudgeSearchService;
import com.linkbit.beidou.service.budge.EcBudgeService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.utils.PageUtils;
import com.linkbit.beidou.utils.StringUtils;
import com.linkbit.beidou.utils.export.docType.ExcelDoc;
import com.linkbit.beidou.utils.export.exporter.DataExport;
import com.linkbit.beidou.utils.export.exporter.ExcelDataExporter;
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
 * 低值易耗品采购申请
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/ecbudget")
public class EcbudgetController extends BaseController {

    @Autowired
    EcBudgeService ecBudgeService;

    @Autowired
    EcBudgeSearchService ecBudgeSearchService;

    @Autowired
    ResourceService resourceService;
    @Autowired
    CommonDataService commonDataService;

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
        return new PageUtils().searchBySortService(ecBudgeSearchService, searchPhrase, 4, current, rowCount, pageable);
    }


    /**
     * @param id 根据id查询
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EcBudgetBill findById(@PathVariable("id") Long id) {
        return ecBudgeService.findById(id);
    }


    /**
     * @param budgetBill 保存或者更新低值易耗品采购单
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(EcBudgetBill budgetBill) {
        EcBudgetBill budgetObj;
        String operation = "保存";
        if (budgetBill.getId() != null) {
            operation = "更新";
        }
        budgetObj = ecBudgeService.save(budgetBill);
        return commonDataService.getReturnType(budgetObj != null, "低值易耗品采购申请单" + operation + "成功!", "低值易耗品采购申请单" + operation + "失败!");
    }


    /**
     * @param id 根据id查询
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return ecBudgeService.delete(id);
    }


    /**
     * @return 查询所有的id集合
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllIds() {
        return ecBudgeService.findAllIds();
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
        List<VEcBudgetBill> dataList = ecBudgeSearchService.findByConditions(param, 4);
        ecBudgeService.setDataList(dataList);
        ecBudgeService.exportExcel(request, response, docName, titles, colNames);
    }

}
