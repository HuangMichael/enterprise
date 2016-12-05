package com.novsky.object.statistics;/**
 * Created by Administrator on 2016/11/7.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 统计结果
 *
 * @author
 * @create 2016-11-07 10:20
 **/
@Entity
@Table(name = "v_work_order_units_order_finished_count")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsFinishedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long unitId;
    private Long reportYear;
    private Long reportMonth;
    private Long reportNum;
}
