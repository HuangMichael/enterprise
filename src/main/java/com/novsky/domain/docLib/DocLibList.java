package com.novsky.domain.docLib;/**
 * Created by Administrator on 2016/12/8.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.novsky.domain.locations.Locations;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 文档库列表信息
 *
 * @author
 * @create 2016-12-08 15:08
 **/
@Entity
@Table(name = "T_DOC_LIB_LIST")
@Data
public class DocLibList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String docNo;//文档编号
    @Column(length = 100, nullable = false)
    private String docName;//文档描述
    @Column(length = 200, nullable = false)
    private String docPath;//文档描述
    @Column(length = 1)
    private Long docLevel;//资源级别
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    DocLib parent;
    @Column(length = 50)
    private String tableName;
    @Column(precision = 10)
    private Long recordId;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    Locations location;
    @Column(length = 20, nullable = false)
    private String uploadedBy;
    @Column(length = 20, nullable = false)
    private Date uploadedTime;
    @Column(length = 1)
    private String status;
}
