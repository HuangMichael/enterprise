package com.linkbit.beidou.dao.equipments;


import com.linkbit.beidou.domain.equipments.Vequipments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备信息查询接口
 */
public interface VequipmentsRepository extends CrudRepository<Vequipments, Long> {


    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */
    List<Vequipments> findByLocationStartingWith(String location);
    /**
     * @param location 位置编码
     * @return 按照位置模糊查询资产信息
     */

}
