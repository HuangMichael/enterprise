package com.linkbit.beidou.dao.budget;

import com.linkbit.beidou.domain.budget.VbudgetBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * 采购申请单
 *
 * @author
 * @create 2016-09-09 11:16
 **/
public interface VbudgetBillRepository extends PagingAndSortingRepository<VbudgetBill, Long> {


    /**
     * @param pageable
     * @return
     */
    Page<VbudgetBill> findAll(Pageable pageable);


    /**
     * @param accessoryName 易耗品名称 模糊查询
     * @param pageable      设置可分页
     * @return 返回易耗品名称模糊查询分页对象
     */
    Page<VbudgetBill> findByAccessoryNameContaining(String accessoryName, Pageable pageable);


    /**
     * @param accessoryName 易耗品名称 模糊查询
     * @return 返回易耗品名称模糊查询
     */
    List<VbudgetBill> findByAccessoryNameContaining(String accessoryName);

    /**
     * @return
     */
    List<VbudgetBill> findAll();


    //根据采购日期 配件名称 位置 查询


    /**
     * @param beginDate     开始日期
     * @param endDate       截止日期
     * @param accessoryName 配件名称
     * @param applyDep      申请单位
     * @return
     */
    List<VbudgetBill> findByApplyDateBetweenAndAccessoryNameContainsAndApplyDepContains(Date beginDate, Date endDate, String accessoryName, String applyDep);


    /**
     * @param beginDate     开始日期
     * @param endDate       截止日期
     * @param accessoryName 配件名称
     * @param applyDep      申请单位
     * @param pageable      可分页
     * @return
     */
    Page<VbudgetBill> findByApplyDateBetweenAndAccessoryNameContainsAndApplyDepContains(Date beginDate, Date endDate, String accessoryName, String applyDep, Pageable pageable);

}
