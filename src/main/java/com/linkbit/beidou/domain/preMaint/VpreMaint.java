package com.linkbit.beidou.domain.preMaint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 预防性维修值对象视图
 *
 * @author
 * @create 2016-10-09 14:08
 **/
@Entity
@Table(name = "V_PRE_MAINT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VpreMaint  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;  //id
    private String pmCode; //编号
    private String pmDesc; //描述
    private Long frequency;//频率
    private String unit;//单位
    private String locName;//位置
    private String eqName;//设备名称
    private String eqClass;//设备分类
    private String outUnit; //外委单位
    private String createBy; //创建人
    private Date createTime; //创建时间
    private String location; //位置编号
}
