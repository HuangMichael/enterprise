package com.novsky.dao.role;


import com.novsky.domain.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/1/8 0008.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * 查询所有角色
     */
    List<Role> findAll();

    /**
     * 根据状态查询所有角色
     */
    List<Role> findByStatus(String status);

    /**
     * 根据id查询
     */
    Role findById(long id);


    /**
     * @param roleDesc 角色描述
     * @param pageable
     * @return 根据角色描述模糊查询
     */
    Page<Role> findByRoleDescContains(String roleDesc, Pageable pageable);


    /**
     * @param roleName
     * @param roleDesc
     * @param pageable
     * @return
     */
    Page<Role> findByRoleNameContainsAndRoleDescContains(String roleName, String roleDesc, Pageable pageable);

    /**
     * @param roleName
     * @param roleDesc
     * @return
     */
    List<Role> findByRoleNameContainsAndRoleDescContains(String roleName, String roleDesc);


    /**
     * @param roleDesc 角色描述
     * @return 根据角色描述模糊查询
     */
    List<Role> findByRoleDescContains(String roleDesc);


    /**
     * 保存角色信息;
     */
    Role save(Role role);


    @Query("select e.id from Role e order by e.id asc")
    List<Long> findAllId();


}
