package com.novsky.object.eq;/**
 * Created by Administrator on 2016/9/6.
 */

import com.novsky.object.SearchObject;
import com.novsky.object.SearchObject;
import lombok.*;

/**
 * 设备查询模型
 *
 * @author
 * @create 2016-09-06 10:54
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EqSearchObject extends SearchObject {

    private String eqName;
    private String locName;
    private String eqClass;
}
