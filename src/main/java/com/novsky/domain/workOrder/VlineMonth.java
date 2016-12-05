package com.novsky.domain.workOrder;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "V_Month_line")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VlineMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String line;
    private String reportMonth;
    private Long num;
    private String name;
}
