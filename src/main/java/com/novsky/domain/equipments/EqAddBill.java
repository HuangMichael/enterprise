package com.novsky.domain.equipments;

import com.novsky.domain.locations.Locations;
import com.novsky.domain.locations.Locations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 设备新置申请单
 **/
@Entity
@Table(name = "T_EQ_RECORDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EqAddBill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id

    @Column(length = 20)
    private String applyDate;// 申购日期

    @Column(length = 50)
    private String applicant; //申请人

    @Column(length = 50)
    private String applyDep; //申请部门

    @Column(length = 50)
    private String purpose; //用途

    @Column(length = 10)
    private String approver; //批准人

    @Column(length = 10)
    private String handler; //经办人

    @Column(length = 10)
    private String receiver; //接收人

    @Column(length = 20)
    private String eqCode; //经办人

    @Column(length = 50)
    private String eqName; //接收人

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Locations location; //位置

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eq_class_id", referencedColumnName = "id")
    private EquipmentsClassification eqClass; //位置


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipments equipment; //设备

    @Column(length = 1, columnDefinition = "default '1'")
    private String dataType; //数据分类 1为采购  2为设备更新
}
