package com.linkbit.beidou.domain.workOrder;/**
 * Created by Administrator on 2016/10/12.
 */

import lombok.Data;

/**
 * 月分类排序实体类
 *
 * @author
 * @create 2016-10-12 16:19
 **/
@Data
public class MonthEqClassRank {


    private String reportMonth;
    private String eqClass;
    private int cCount;
}
