package com.novsky.dao.macCost;

import com.novsky.domain.matCost.MatCost;
import com.novsky.domain.matCost.WorkOrderMatCost;
import com.novsky.domain.matCost.WorkOrderMatCost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 工单物料消耗数据接口
 **/
public interface WorkOrderMatCostRepository extends PagingAndSortingRepository<WorkOrderMatCost, Long>, CrudRepository<WorkOrderMatCost, Long> {


    /**
     * @param pageable
     * @return
     */
    Page<WorkOrderMatCost> findAll(Pageable pageable);

    /**
     * @return
     */
    List<WorkOrderMatCost> findAll();


    /**
     * @param
     * @return 批量保存物料消耗数据
     */
    WorkOrderMatCost save(WorkOrderMatCost workOrderMatCost);

    /**
     * @param id 根据id查询
     * @return
     */
    WorkOrderMatCost findById(Long id);


    /**
     * @return查询所有的id
     */
    @Query("select v.id from WorkOrderMatCost v")
    List<Long> findAllIds();


    /**
     * @param orderLineNo 工单编号
     * @param matName     物资名称
     * @param matModel    物资型号
     * @param pageable
     * @return
     */
    Page<WorkOrderMatCost> findByOrderLineNoContainsAndMatNameContainsAndMatModelContains(String orderLineNo, String matName, String matModel, Pageable pageable);


    /**
     * @param orderLineNo 工单编号
     * @param matName     物资名称
     * @param matModel    物资型号
     * @return
     */
    List<WorkOrderMatCost> findByOrderLineNoContainsAndMatNameContainsAndMatModelContains(String orderLineNo, String matName, String matModel);

}
