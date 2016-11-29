package com.linkbit.beidou.dao.preMaint;

import com.linkbit.beidou.domain.preMaint.PreMaintWorkOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/5/20.
 */
public interface PreMaintWorkOrderRepository extends CrudRepository<PreMaintWorkOrder, Long>, PagingAndSortingRepository<PreMaintWorkOrder, Long> {


    PreMaintWorkOrder save(PreMaintWorkOrder preMaintWorkOrder);


    List<PreMaintWorkOrder> findAll();


    /**
     * @param orderDesc
     * @return 根据工单描述
     */
    List<PreMaintWorkOrder> findByOrderDesc(String orderDesc);



    List<PreMaintWorkOrder> findByStatus(String status);

    /**
     * @param id
     * @return 根据id查询
     */
    PreMaintWorkOrder findById(Long id);


    void delete(Long id);


    /**
     * @return 返回工单跟踪号
     * 格式：年月+4位顺序号  16100001
     */
    @Query(nativeQuery = true, value = "select order_line_no from v_pre_maint_line_no_gen")
    List<Object> genPmOrderLineNo();


}
