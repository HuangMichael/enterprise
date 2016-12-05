package com.novsky.dao.workOrder;

import com.novsky.domain.workOrder.VlineMonth;
import com.novsky.domain.workOrder.VlineMonth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 按照路线统计
 * 2016年10月13日
 */
public interface VlineMonthRepository extends CrudRepository<VlineMonth, Long> {
    /**
     * @param reportMonth 月份
     * @param name        工单状态
     * @return
     */
    List<VlineMonth> findByReportMonthAndName(String reportMonth, String name);
}
