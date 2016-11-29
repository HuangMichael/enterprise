package com.linkbit.beidou.dao.workOrder;

import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.workOrder.WorkOrderReportCart;
import com.linkbit.beidou.object.statistics.StatisticsDistributedObject;
import com.linkbit.beidou.object.statistics.StatisticsFinishedObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/5/20.
 */
public interface WorkOrderReportCartRepository extends CrudRepository<WorkOrderReportCart, Long>, PagingAndSortingRepository<WorkOrderReportCart, Long> {

    String sql = "select v.reportMonth,v.eqClass,v.cCount from v_month_eq_class_rank v where 1=1 ";


    WorkOrderReportCart save(WorkOrderReportCart workOrderReportCart);


    List<WorkOrderReportCart> findAll();


    List<WorkOrderReportCart> findByStatus(String status);

    /**
     * @param id
     * @return 根据id查询
     */
    WorkOrderReportCart findById(Long id);


    void delete(Long id);


    /**
     * @param personName
     * @return 查询我的报修车
     * @Version 0.2 将原来状态1修改为0 查询未提交的
     */

/*    @Query(nativeQuery = true,value ="SELECT v.loc_Name,ec.description FROM t_work_order_report_cart c LEFT JOIN v_locations v ON c.vlocations_id = v.id LEFT JOIN t_equipments_classification ec ON c.eq_class_id = ec.id where c.status =0 and c.reporter =:personName limit :n")
    List<Object> findMyCart(@Param("personName") String personName, @Param("n") Long n);*/
    @Query("select c from  WorkOrderReportCart c where c.status =0 and c.creator =:personName and c.nodeState='报修车'")
    List<WorkOrderReportCart> findMyCart(@Param("personName") String personName);


    @Query("select count(c) from  WorkOrderReportCart c where c.status =0 and c.creator =:personName and c.nodeState='报修车'")
    Long findMyCartSize(@Param("personName") String personName);


    /**
     * @param locationId
     * @param status     完工状态
     * @return 查询我的报修车
     */
    @Query("select c  from WorkOrderReportCart c where c.locations.id = :locationId and c.equipments.id is null  and c.status <>:status ")
    List<WorkOrderReportCart> findByNocompletedLocations(@Param("locationId") Long locationId, @Param("status") String status);


    /**
     * @param equipmentId 设备id
     * @param status      完工状态
     * @return 查询我的
     */

    @Query("select c  from WorkOrderReportCart c where c.equipments.id = :equipmentId and c.status <>:status ")
    List<WorkOrderReportCart> findByNocompletedEquipments(@Param("equipmentId") Long equipmentId, @Param("status") String status);


    /**
     * @param equipmentId 设备id
     * @param status      完工状态
     * @return 查询已报修未完工的设备信息
     *//*
    @Query(nativeQuery = true, value = SQL0 + SQL0WHERE + SQL1 + SQL1WHERE + SQL2 + SQL2WHERE)
    List<Object> findReportedEquipments(@Param("equipmentId") Long equipmentId, @Param("status") String status);*/


    /**
     * @param equipmentId 设备id
     * @return 查询已报修未完工的设备信息
     */
    @Query(nativeQuery = true, value = "SELECT v.equipments_id,v.order_desc,DATE_FORMAT(v.report_time,'%Y-%m-%d %H:%m:%s'),v.node_state from t_work_order_report_cart v where v.equipments_id=:equipmentId and v.status=:status")
    List<Object> findReportedEquipments(@Param("equipmentId") Long equipmentId, @Param("status") String status);


    List<WorkOrderReportCart> findByLocationStartingWithAndNodeStateNot(@Param("location") String location, @Param("nodeState") String nodeState);


    List<WorkOrderReportCart> findByEquipmentsAndNodeStateNot(@Param("equipment") Equipments equipment, @Param("nodeState") String nodeState);


/*    List<WorkOrderReportCart> findByLocationsAndStatus(Locations locations, String status);*/


    /**
     * @param location 位置信息
     * @param status   状态信息
     * @return
     */
    @OrderBy("reportTime desc")
    List<WorkOrderReportCart> findByLocationStartingWithAndStatus(String location, String status);


    @Query(nativeQuery = true, value = "SELECT b.eqclass,b.y AS y FROM(select v.eqclass,v.ccount AS y FROM v_top5_reportcart_eqclass v LIMIT 5) b UNION SELECT d.eqclass,sum(d.y) AS q5 FROM (SELECT '其它' AS eqclass,v.ccount AS y FROM v_top5_reportcart_eqclass v LIMIT 5,1000) d")
    List<Object> findTopNReportCartByEqClass(int offset);


    @Query(nativeQuery = true, value = sql + " and reportMonth = :reportMonth order by ccount desc")
    List<Object> findTopNReportByEqClass(@Param("reportMonth") String reportMonth);


    /**
     * @param location
     * @param nodeState
     * @return 根据位置和节点的状态查询
     */
    @OrderBy("id desc")
    List<WorkOrderReportCart> findByLocationStartingWithAndNodeState(String location, String nodeState);


    /**
     * @param orderDesc
     * @param reportType
     * @param pageable   可分页
     * @return 根据位置和节点的状态查询
     */
    @OrderBy("id desc")
    Page<WorkOrderReportCart> findByOrderDescContainingAndReportType(String orderDesc, String reportType, Pageable pageable);


    /**
     * @param location
     * @return 根据位置和节点的状态查询
     */
    List<WorkOrderReportCart> findByLocationStartingWith(String location);

    @Query("SELECT r from WorkOrderReportCart r where r.id in :ids ")
    List<WorkOrderReportCart> findWorkOrderReportDetailByIds(@Param("ids") List<Long> ids);


    /**
     * @param
     * @return 根据位置和节点的状态查询
     */
    List<WorkOrderReportCart> findByNodeStateAndDeadLineLessThan(String nodeState, Date now);


    /**
     * @return 返回工单跟踪号
     * 格式：年月+4位顺序号  16100001
     */
    @Query(nativeQuery = true, value = "select order_line_no from v_work_order_line_no_gen")
    List<Object> genOrderLineNo();


    List<WorkOrderReportCart> findByOrderLineNoContaining(String dateStr);


    ///////////////////////////////////////////外委单位统计分析 begin///////////////////////////////////////////////////////////////
    //按年查询有工单的年月


    /**
     * @param year 按照年查询有报修数据的月份
     * @return
     */
    @Query(nativeQuery = true, value = " select v.report_month from v_work_order_data_month v  where  v.report_year = :year ")
    List<String> getDataMonthByYear(@Param("year") Long year);


    /**
     * @return 查询有数据的年份
     */
    @Query(nativeQuery = true, value = " select v.report_year from v_work_order_data_year v ")
    List<String> getDataYear();


    /**
     * @param year   年
     * @param unitId 外委单位ID
     * @return 查询该年的分配工单的统计数据
     */
    @Query(value = "SELECT c FROM  StatisticsDistributedObject c WHERE c.unitId = :unitId AND c.reportYear = :year order by c.reportMonth asc")
    List<StatisticsDistributedObject> getDistributedOrderCountByYearAndUnit(@Param("year") Long year, @Param("unitId") Long unitId);


    /**
     * @param year   年
     * @param unitId 外委单位ID
     * @return 查询该年的完工工单的统计数据
     */
    @Query(value = "SELECT  c FROM  StatisticsFinishedObject c WHERE c.unitId = :unitId AND c.reportYear = :year order by c.reportMonth asc ")
    List<StatisticsFinishedObject> getFinishedOrderCountByYearAndUnit(@Param("year") Long year, @Param("unitId") Long unitId);

    ///////////////////////////////////////////外委单位统计分析 end///////////////////////////////////////////////////////////////


    ///////////////////////////////////////////外委单位统计分析 end///////////////////////////////////////////////////////////////

    /**
     * @param expired 0为未过期 1为过期
     * @param status  1为正式工单  0 为报修车中的工单
     * @param nodeState
     * @return
     */
    List<WorkOrderReportCart> findByExpiredAndStatusAndNodeState(boolean expired, String status,String nodeState);


    ///////////////////////////////////////////外委单位统计分析 end///////////////////////////////////////////////////////////////
}
