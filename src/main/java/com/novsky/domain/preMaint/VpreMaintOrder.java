package com.novsky.domain.preMaint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 预防性维修单对象视图
 *
 * @author
 * @create 2016-10-09 14:08
 **/
@Entity
@Table(name = "V_PRE_MAINT_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VpreMaintOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;  //id
    private String orderLineNo;
    private String eqCode;
    private String eqName;
    private String locName;
    private String location;
    private String eqClass;
    private String orderDesc;
    private String reporter;
    private String reportTime;
    private String nodeState;
}
