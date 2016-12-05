package com.novsky.dao.workOrder;

import com.novsky.domain.workOrder.VWorkOrderUnitsFixedRank;
import com.novsky.domain.workOrder.VlineMonth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * 工单按照外委单位维修数量排名
 */
public interface VWorkOrderUnitsFixedRankRepository extends CrudRepository<VWorkOrderUnitsFixedRank, Long>, PagingAndSortingRepository<VWorkOrderUnitsFixedRank, Long> {

    /**
     * @param reportYear
     * @return 按照维修年份查询
     */
    @Query(value = "select  v from VWorkOrderUnitsFixedRank v where v.reportYear=:reportYear  order by v.fixNum  desc")
    List<VWorkOrderUnitsFixedRank> findByReportYear(@Param("reportYear") String reportYear);

}
