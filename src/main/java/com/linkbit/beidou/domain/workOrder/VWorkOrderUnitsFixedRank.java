package com.linkbit.beidou.domain.workOrder;/**
 * Created by Administrator on 2016/10/12.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 工单维修 按照外委单位排名
 *
 * @author
 * @create 2016-10-12 16:19
 **/


@Entity
@Table(name = "v_work_order_units_rank")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VWorkOrderUnitsFixedRank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reportYear;
    private String unitName;
    private Long fixNum;

}
