package com.novsky.dao.workOrder;

import com.novsky.domain.workOrder.VWorkOrderUnitsEfficiencyRank;
import com.novsky.domain.workOrder.VWorkOrderUnitsEfficiencyRank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/11/7.
 */
public interface VWorkOrderUnitsEfficiencyRankRepository extends CrudRepository<VWorkOrderUnitsEfficiencyRank, Long>, PagingAndSortingRepository<VWorkOrderUnitsEfficiencyRank, Long> {


    /**
     * @param reportYear
     * @return 按照维修年份查询
     */
    @Query(value = "select  v from VWorkOrderUnitsEfficiencyRank v where v.reportYear=:reportYear  order by v.percent  asc")
    List<VWorkOrderUnitsEfficiencyRank> findByReportYear(@Param("reportYear") String reportYear);

}
