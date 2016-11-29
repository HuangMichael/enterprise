package com.linkbit.beidou.domain.units;

import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 外委单位
 */

@Entity
@Table(name = "T_OUTSOURCING_UNIT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Units implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Fields
    @Column(length = 20, unique = true, nullable = false)
    private String unitNo; //编码

    @Column(length = 200)
    private String description;//外委单位名称

    @Column(length = 20)
    private String linkman;//责任人

    @Column(length = 20)
    private String telephone;//联系电话

    @Temporal(TemporalType.DATE)
    private Date beginDate; //合同开始日期

    @Temporal(TemporalType.DATE)
    private Date endDate;  //合同结束日期

    @Column(length = 1, columnDefinition = "default '1'")
    private String status;  //使用状态

    @Column(length = 1)
    private String workDays;  //工作制

    @ManyToMany
    @JoinTable(name = "t_unit_class", joinColumns = {@JoinColumn(name = "unit_id")}, inverseJoinColumns = {@JoinColumn(name = "class_id")})
    private Set<EquipmentsClassification> eqClassList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "unitSet")
    private List<EquipmentsClassification> classificationSet;

    @Column(length = 1, columnDefinition = "default 1")
    private String outFlag; //外部标识 默认为外部

}