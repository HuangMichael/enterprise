package com.novsky.domain.equipments;


import lombok.*;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/03/14 0023.
 * 设备分类视图信息
 */
@Entity
@Table(name = "V_EQ_CLASS")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VeqClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 50)
    private String cname;
}

