package com.novsky.service.portal;

/**
 * Created by Administrator on 2016/7/24.
 */

import com.novsky.dao.workOrder.VlineMonthRepository;
import com.novsky.dao.workOrder.WorkOrderReportCartRepository;
import com.novsky.domain.workOrder.VlineMonth;
import com.novsky.service.app.BaseService;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2016/7/24.
 */
@Service
public class PortalService extends BaseService {


    @Autowired
    VlineMonthRepository vlineMonthRepository;
    @Autowired
    WorkOrderReportCartRepository workOrderReportCartRepository;

    /**
     * @param reportMonth 月份
     * @param name        工单状态
     * @return 获取线路统计汇总
     */
    public List<VlineMonth> getLineReportNumReportMonth(String reportMonth, String name) {

        return vlineMonthRepository.findByReportMonthAndName(reportMonth, name);
    }

    /**
     * @param reportMonth
     * @return 设备分类按照数量排序
     */
    public List<Object> findTopNReportByEqClass(String reportMonth) {
        List<Object> dataList = workOrderReportCartRepository.findTopNReportByEqClass(reportMonth);
        return dataList;

    }
}
