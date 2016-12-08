package com.novsky.domain.docLib;/**
 * Created by Administrator on 2016/12/8.
 */

import lombok.Data;

import javax.persistence.*;

/**
 * 文档库信息
 *
 * @author
 * @create 2016-12-08 15:08
 **/
@Table(name = "T_DOC_LIB")
@Data
public class DocLib {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String description;//资源描述
    @Column(length = 1)
    private Long docLevel;//资源级别
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    DocLib parent;
    @Column(length = 1)
    private String status;
    @Column(precision = 2)
    private Long sortNo; //排序

}
