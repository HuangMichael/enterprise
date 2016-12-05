package com.novsky.tasks;

import com.novsky.domain.app.org.SystemInfo;
import com.novsky.service.org.SysInfoService;
import com.novsky.service.preMaint.PreMaintService;
import com.novsky.utils.DateUtils;
import com.novsky.domain.app.org.SystemInfo;
import com.novsky.service.org.SysInfoService;
import com.novsky.service.preMaint.PreMaintService;
import com.novsky.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 维修任务状态定时更新
 *
 * @author huangbin
 * @create 2016-09-05 14:08
 **/
@Component
public class BatchGeneratePmOrderTask {

    private static final Logger log = LoggerFactory.getLogger(BatchGeneratePmOrderTask.class);
    @Autowired
    PreMaintService preMaintService;


    @Autowired
    SysInfoService sysInfoService;

    @Scheduled(cron = "0 0 1 * * *")
    public void updateFixTaskStatus() {
        SystemInfo systemInfo = sysInfoService.findBySysName("pre_maint_auto_schedule");
        if (systemInfo != null && systemInfo.getStatus().equals("1")) {
            List<Long> idList = preMaintService.selectAllId("BJ");
            String deadLine = DateUtils.convertDate2Str(new Date(), "yyyy-MM-dd");
            preMaintService.generatePmOrderBatch(idList, deadLine);
            log.info("预防性维修计划生成任务已开启调度并执行------------------------");
        } else {
            log.info("预防性维修计划生成任务已关闭调度未执行------------------------");
        }


    }
}
