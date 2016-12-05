package com.novsky.domain.preMaint;

import com.novsky.domain.equipments.Equipments;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.locations.Locations;
import com.novsky.domain.locations.Vlocations;
import com.novsky.domain.units.Units;
import com.novsky.domain.equipments.Equipments;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.locations.Locations;
import com.novsky.domain.units.Units;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Pre_Maint_Work_Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 预防性维修工单
 * */
public class PreMaintWorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 20, unique = true, nullable = false)
    private String orderLineNo; //工单编号行号
    private String orderDesc;  //故障描述
    @OneToOne
    @JoinColumn(name = "locations_id")
    private Locations locations;
    @OneToOne
    @JoinColumn(name = "equipments_id")
    private Equipments equipments;
    @OneToOne
    @JoinColumn(name = "eqClass_id")
    private EquipmentsClassification equipmentsClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    Units unit;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_maint_id", referencedColumnName = "id") //维修计划信息
     PreMaint preMaint;

    @Column(length = 20)
    private String reporter; //报修人
    @Column(length = 20)
    private String creator; //录入人
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTime;  //故障描述
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastStatusTime;  //最后状态时间
    @Column(length = 20)
    private String location; //位置编码
    @Column(length = 1)
    private String reportType; //报修方式 S为设备 W位置

    @Column(length = 10)
    private String nodeState; //节点状态

    @Column(length = 20)
    private String fixDesc; //节点状态

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadLine;  //维修期限

    @Column(length = 1)
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlocations_id", referencedColumnName = "id")
    private Vlocations vlocations;  //所属位置
}
