package com.linkbit.beidou.dao.equipments;

import com.linkbit.beidou.domain.equipments.VEqRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 设备履历数据接口
 *
 * @Date 2016年9月23日10:46:47
 **/
public interface VEqRecordRepository extends CrudRepository<VEqRecord, Long> {
    /**
     * @param id 根据id查询
     * @return
     */
    VEqRecord findById(Long id);

    /**
     * @param id
     * @return 根据设备id查询
     */
    @Query("select v from VEqRecord v where v.equipment.id  =:id")
    List<VEqRecord> findByEquipmentId(@Param("id") Long id);

    /**
     * @return查询所有的新置的id
     */
    @Query("select v.id from VEqRecord v where v.dataType ='1'")
    List<Long> findAllNewIds();

    /**
     * @return 查询所有更新的id
     */
    @Query("select v.id from VEqRecord v where v.dataType ='2'")
    List<Long> findAllUpdateIds();
}
