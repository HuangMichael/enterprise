package com.novsky.dao.app.resource;

import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.role.Role;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.role.Role;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/8/16.
 */
public interface VRoleAuthViewRepository extends CrudRepository<VRoleAuthView, Long>, PagingAndSortingRepository<VRoleAuthView, Long> {


    /**
     * @param role
     * @return 根据角色查询角色权限预览视图
     */
    List<VRoleAuthView> findByRole(Role role);


    /**
     * @param role
     * @param resourceName
     * @param pageable
     * @return 根据角色查询角色权限预览视图
     */
    Page<VRoleAuthView> findByRoleAndResourceNameContaining(Role role, String resourceName, Pageable pageable);


    /**
     * @param role
     * @param resourceName
     * @return 根据角色查询角色权限预览视图
     */
    List<VRoleAuthView> findByRoleAndResourceNameContaining(Role role, String resourceName);

    /**
     * @param resourceLevel
     * @return 根据角色和资源级别查询
     */
    @Query("select distinct v from VRoleAuthView v where v.role.id in :roleIdList and  v.resourceLevel =:resourceLevel order by v.sortNo asc ")
    List<VRoleAuthView> findByRoleListAndResourceLevel(@Param("roleIdList") List<Long> roleIdList, @Param("resourceLevel") Long resourceLevel);

    /**
     * @param resourceLevel
     * @param parentId
     * @return 根据角色和资源级别查询
     */
    @Query("select  distinct v  from VRoleAuthView v where 1=1 and v.role.id in :idList and v.resourceLevel =:resourceLevel and v.parentId =:parentId order by v.sortNo asc")
    List<VRoleAuthView> findByRoleListAndResourceLevelAndParentId(@Param(value = "idList") List<Long> idList, @Param("resourceLevel") Long resourceLevel, @Param("parentId") Long parentId);


    /**
     * 根据应用名称查询菜单
     */
    @Query("select  distinct v  from VRoleAuthView v where  v.role in :roleList and v.appName =:appName order by v.sortNo asc ")
    List<VRoleAuthView> findByRoleListAppName(@Param("roleList") List<Role> roleList, @Param("appName") String appName);


    /**
     * @param roleId 角色id
     * @param authId 资源ID
     * @return
     */
    @Query(nativeQuery = true, value = "delete from t_role_resource r where 1=1  and r.resource_id = :authId and r.role_id =:roleId ")
    void deleteRoleAuth(@Param("roleId") Long roleId, @Param("authId") Long authId);
}
