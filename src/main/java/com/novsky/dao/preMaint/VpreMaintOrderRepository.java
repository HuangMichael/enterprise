package com.novsky.dao.preMaint;


import com.novsky.domain.preMaint.VpreMaintOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * 预防性维修视图查询接口
 */
public interface VpreMaintOrderRepository extends PagingAndSortingRepository<VpreMaintOrder, Long> {


    /**
     * @param pageable 分页
     * @return
     */
    Page<VpreMaintOrder> findAll(Pageable pageable);


    /**
     * @param nodeState
     * @param orderDesc
     * @param pageable
     * @return 分页
     */
    Page<VpreMaintOrder> findByNodeStateAndOrderDescContainingOrderByOrderLineNoDesc(String nodeState, String orderDesc, Pageable pageable);


    /**
     * @param nodeState
     * @param orderDesc
     * @return 分页
     */
    List<VpreMaintOrder> findByOrderDescContainingAndLocationContainingAndNodeState(String orderDesc, String locName, String nodeState);

    /**
     * @param nodeState
     * @param orderDesc
     * @param pageable
     * @return 分页
     */
    Page<VpreMaintOrder> findByOrderDescContainingAndLocationContainingAndNodeState(String orderDesc, String locName, String nodeState, Pageable pageable);
}
