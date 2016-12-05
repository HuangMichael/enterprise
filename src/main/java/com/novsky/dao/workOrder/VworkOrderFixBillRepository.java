package com.novsky.dao.workOrder;

import com.novsky.domain.workOrder.VworkOrderFixBill;
import com.novsky.domain.workOrder.VworkOrderReportBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by huangbin on 2016/9/2.
 */
public interface VworkOrderFixBillRepository extends PagingAndSortingRepository<VworkOrderFixBill, Long>, JpaSpecificationExecutor<VworkOrderFixBill> {

    List<VworkOrderFixBill> findAll();

    /**
     * @param location  位置编号
     * @param nodeState 节点状态
     * @return 根据用户位置和节点状态查询
     */
    @OrderBy("nodeTime desc,dealLine desc,id desc")
    List<VworkOrderFixBill> findByLocationStartingWithAndNodeState(String location, String nodeState);

    /**
     * @param location  位置编号
     * @param nodeState 节点状态
     * @param orderDesc
     * @param pageable  可分页
     * @return
     */
    Page<VworkOrderFixBill> findByLocationStartingWithAndNodeStateAndOrderDescContainingOrderByNodeTimeDesc(String location, String nodeState, String orderDesc, Pageable pageable);


    /**
     * @param location  位置编号
     * @param nodeState 节点状态
     * @param orderDesc 不分页
     * @return
     */
    List<VworkOrderFixBill> findByLocationStartingWithAndNodeStateAndOrderDescContainingOrderByNodeTimeDesc(String location, String nodeState, String orderDesc);




    /**
     * @param orderLineNo
     * @param orderDesc 报修描述
     * @param location
     * @param eqClass
     * @param pageable
     * @return 模糊查询
     */
    Page<VworkOrderFixBill> findByOrderLineNoContainsAndOrderDescContainsAndLocationContainsAndEqClassContains(String orderLineNo, String orderDesc, String location, String eqClass, Pageable pageable);


    /**
     * @param orderLineNo
     * @param orderDesc 报修描述
     * @param location
     * @param eqClass
     * @return 模糊查询
     */
    List<VworkOrderFixBill> findByOrderLineNoContainsAndOrderDescContainsAndLocationContainsAndEqClassContains(String orderLineNo, String orderDesc, String location, String eqClass);
}
