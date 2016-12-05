package com.novsky.domain.workOrder;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "v_work_order_fix_bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 维修单视图实体类
 * */
public class VworkOrderFixBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String orderLineNo; //维修行号
    private String orderDesc;//报修单描述
    private String eqName;//设备名称
    private String locName;//位置名称
    private String eqClass;//设备分类
    private String unitName;//外委单位名称
    private String nodeTime;//节点时间
    private String deadLine;//截止时间
    private String nodeState;//节点状态
    private String location;//位置编号
    private String expired;//过期标识 1过期 0未过期
}
