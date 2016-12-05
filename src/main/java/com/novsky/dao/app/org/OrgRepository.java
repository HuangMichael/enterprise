package com.novsky.dao.app.org;


import com.novsky.domain.app.org.SystemInfo;
import com.novsky.domain.app.org.SystemInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 查询系统信息
 */
public interface OrgRepository extends CrudRepository<SystemInfo, Long> {
    /**
     * 查询所有数据过滤器
     */
    List<SystemInfo> findAll();

    /**
     * 根据状态查询数据过滤器
     */
    List<SystemInfo> findByStatus(String status);

    /**
     * 根据id查询数据过滤器
     */
    SystemInfo findById(long id);


    /**
     * 系统名称
     *
     * @param sysName
     * @return 根据参数名称查询对应记录
     */
    List<SystemInfo> findBySysName(String sysName);

}
