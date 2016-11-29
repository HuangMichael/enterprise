package com.linkbit.beidou.domain.workOrder;/**
 * Created by Administrator on 2016/10/12.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 外委单位工单维修及时率排名
 */
@Entity
@Table(name = "v_work_order_units_efficiency_rank")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VWorkOrderUnitsEfficiencyRank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reportYear;
    private String unitName;
    private String fixNum;
    private String totalNum;
    private Double percent;
}
