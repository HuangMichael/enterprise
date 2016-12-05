package com.novsky.service.budge;

import com.novsky.dao.budget.BudgetBillRepository;
import com.novsky.dao.budget.VbudgetBillRepository;
import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.domain.budget.BudgetBill;
import com.novsky.domain.budget.VbudgetBill;
import com.novsky.service.app.BaseService;
import com.novsky.utils.export.docType.ExcelDoc;
import com.novsky.utils.export.exporter.DataExport;
import com.novsky.utils.export.exporter.ExcelDataExporter;
import com.novsky.utils.export.tool.Exportable;
import com.novsky.dao.budget.BudgetBillRepository;
import com.novsky.dao.budget.VbudgetBillRepository;
import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.domain.budget.BudgetBill;
import com.novsky.domain.budget.VbudgetBill;
import com.novsky.service.app.BaseService;
import com.novsky.utils.export.docType.ExcelDoc;
import com.novsky.utils.export.exporter.DataExport;
import com.novsky.utils.export.exporter.ExcelDataExporter;
import com.novsky.utils.export.tool.Exportable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangbin on 2016/5/4.
 * 设备台账业务类
 */
@Service
public class BudgeService extends BaseService implements Exportable {


    @Autowired
    BudgetBillRepository budgetBillRepository;


    @Autowired
    VbudgetBillRepository vbudgetBillRepository;


    @Autowired
    VlocationsRepository vlocationsRepository;


    /**
     * @param pageable
     * @return 分页查询
     */
    public Page<VbudgetBill> findAllV(Pageable pageable) {

        return vbudgetBillRepository.findAll(pageable);
    }


    /**
     * @param accessoryName 配件名称
     * @param pageable      分页
     * @return 按照配件名称模糊查询分页查询
     */
    public Page<VbudgetBill> findByAccessoryNameContains(String accessoryName, Pageable pageable) {
        return vbudgetBillRepository.findByAccessoryNameContaining(accessoryName, pageable);
    }


    /**
     * @param accessoryName 配件名称
     * @return 按照配件名称模糊查询分页查询
     */
    public List<VbudgetBill> findByAccessoryNameContains(String accessoryName) {
        return vbudgetBillRepository.findByAccessoryNameContaining(accessoryName);
    }


    /**
     * @param pageable
     * @return 分页查询
     */
    public Page<BudgetBill> findAll(Pageable pageable) {
        return budgetBillRepository.findAll(pageable);
    }

    /**
     * @return 查询所有
     */
    public List<BudgetBill> findAll() {
        return budgetBillRepository.findAll();
    }


    /**
     * @return 查询所有
     */
    public BudgetBill findById(Long id) {
        return budgetBillRepository.findById(id);
    }


    /**
     * @return 查询所有
     */
    public List<VbudgetBill> findAllV() {
        return vbudgetBillRepository.findAll();
    }


    /**
     * @return 查询所有
     */
    public BudgetBill save(BudgetBill budgetBill) {
        return budgetBillRepository.save(budgetBill);
    }


    /**
     * @param id 根据id删除 删除成功返回true
     * @return
     */
    public boolean delete(Long id) {
        if (id != null) {
            budgetBillRepository.delete(id);
        }
        return budgetBillRepository.findById(id) == null;
    }


    /**
     * @return 查询所有的id
     */
    public List<Long> findAllIds() {
        List<Long> ids = budgetBillRepository.findAllIds();
        return ids;
    }


    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String docName, String[] titles, String[] colNames, List dataList) {
        DataExport dataExport = new ExcelDataExporter();
        try {
            dataExport.export(new ExcelDoc(), request, response, titles, colNames, dataList, docName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
