package com.linkbit.beidou.dao.preMaint;


import com.linkbit.beidou.domain.preMaint.PreMaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 设备信息查询接口
 */
public interface PreMaintRepository extends CrudRepository<PreMaint, Long>, PagingAndSortingRepository<PreMaint, Long> {


    /**
     * @param pageable 分页
     * @return
     */
    Page<PreMaint> findAll(Pageable pageable);


    @Query("select v.id from PreMaint v where v.equipment.locations.location like :location")
    List<Long> selectAllId(@Param("location") String location);


    /**
     * @return 查询所有的id
     */
    @Query("select v.id from PreMaint v ")
    List<Long> selectAllId();
}
