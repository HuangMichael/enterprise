package com.novsky.domain.app;/**
 * Created by Administrator on 2016/9/2.
 */

import lombok.*;

import java.util.List;

/**
 * 自定义的分页对象
 *
 * @author
 * @create 2016-09-02 15:01
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {
    private int current; //当前页
    private Long rowCount; //每页行数
    private List rows; //当前页记录集合
    private long total;//记录总数
    private String[] sort; //排序
}
