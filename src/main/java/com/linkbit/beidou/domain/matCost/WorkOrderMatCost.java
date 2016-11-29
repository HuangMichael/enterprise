package com.linkbit.beidou.domain.matCost;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangbin
 * 2016年10月14日09:26:04
 * 工单物料消耗信息
 */
@Entity
@Table(name = "T_WORK_ORDER_MAT_COST")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderMatCost implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 20, unique = true)
    private String orderLineNo; //维修单号
    @Column(length = 50)
    private String matName; //物资名称
    @Column(length = 30)
    private String matModel; //物资型号
    @Column(length = 20)
    private Long matAmount; //数量
    @Column(length = 10)
    private Double matPrice; //单价
    @Column(length = 1, columnDefinition = "default 0")
    private String dataType; //数据来源  0导入 默认导入
    @Column(length = 100)
    private String fileName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date importTime; //数据来源  0导入 默认导入

}