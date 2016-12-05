package com.novsky.dao.workOrder;

import com.novsky.domain.workOrder.VworkOrderNumFinish;
import com.novsky.domain.workOrder.VworkOrderNumReport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/7/24.
 */
public interface VworkOrderNumReportRepository extends CrudRepository<VworkOrderNumReport, Long> {


    List<VworkOrderNumReport> findAll();


    /**
     * @param reportMonth 根据月份查询
     * @return
     */
    List<VworkOrderNumReport> findByReportMonth(String reportMonth);

}
