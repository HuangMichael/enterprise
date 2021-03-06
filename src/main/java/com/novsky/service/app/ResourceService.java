package com.novsky.service.app;

import com.novsky.dao.app.resource.ResourceRepository;
import com.novsky.dao.app.resource.VRoleAuthViewRepository;
import com.novsky.domain.app.resoure.Resource;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.role.Role;
import com.novsky.domain.user.User;
import com.novsky.service.role.RoleService;
import com.novsky.service.user.UserService;
import com.novsky.utils.SessionUtil;
import com.novsky.utils.StringUtils;
import com.novsky.dao.app.resource.ResourceRepository;
import com.novsky.dao.app.resource.VRoleAuthViewRepository;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.role.Role;
import com.novsky.domain.user.User;
import com.novsky.service.role.RoleService;
import com.novsky.service.user.UserService;
import com.novsky.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * <p/>
 * 数据资源业务类
 */
@Service
public class ResourceService extends BaseService {

    @Autowired
    public ResourceRepository resourceRepository;

    @Autowired
    VRoleAuthViewRepository vRoleAuthViewRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    /**
     * 查询所有数据资源
     */
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }


    /**
     * 查询所有数据资源
     */
    public List<Resource> findApps() {
        return resourceRepository.findByResourceLevel(1l);
    }

    /**
     * 根据状态查询数据资源
     */
    public List<Resource> findByStatus(String status) {

        return resourceRepository.findByStatus(status);

    }

    /**
     * 根据id查询数据资源
     */
    public Resource findById(long id) {

        return resourceRepository.findById(id);

    }

    /**
     * 保存数据资源
     */
    public Resource save(Resource resource) {

        return resourceRepository.save(resource);
    }

    /**
     * 保存数据资源
     */
    public void delete(Resource resource) {

        resourceRepository.delete(resource);
    }


    /**
     * 根据id删除数据资源
     */
    public void delete(Long pid) {
        resourceRepository.delete(pid);
    }

    /**
     * 检查是否有子节点
     */
    public boolean hasChildren(Long pid) {

        return resourceRepository.hasChildren(pid);
    }


    /**
     * 根据状态查询数据资源
     */
    public List<Resource> findByParent(Resource parent) {

        return resourceRepository.findByParent(parent);

    }


    /**
     * @param idStr
     * @return 根据ID列表查询对应的资源集合
     */
    public List<Resource> findResourceListInIdStr(String idStr) {
        List<Long> idList = StringUtils.str2List(idStr, ",");
        List<Resource> resourceList = resourceRepository.findResourceIdInIdList(idList);
        return resourceList;
    }


    /**
     * @param roleId
     * @param resourceName
     * @return
     */
    @CacheEvict(value = "roleAuth", key = "'roleAuth'")
    public List<VRoleAuthView> findByRoleAndResourceNameContaining(Long roleId, String resourceName) {
        Role role = roleService.findById(roleId);
        return vRoleAuthViewRepository.findByRoleAndResourceNameContaining(role, resourceName);
    }

    /**
     * @param userId 用户名
     * @return 根据userId和资源级别查询资源
     */
    @CacheEvict(value = "roleAuthLevel", key = "'roleAuthLevel'")
    public List<VRoleAuthView> findResourcesByUserIdAndResourceLevel(Long userId, Long resourceLevel) {
        //首先根据用户id查询出用户对应的角色
        List<VRoleAuthView> vRoleAuthViewList = null;
        User user = userService.findById(userId);
        List<Long> idList = new ArrayList<Long>();
        if (user != null) {
            List<Role> roleList = user.getRoleList();
            for (Role r : roleList) {
                idList.add(r.getId());
            }
            vRoleAuthViewList = vRoleAuthViewRepository.findByRoleListAndResourceLevel(idList, resourceLevel);
        }
        return vRoleAuthViewList;
    }

    /**
     * @param userId 用户名
     * @return 根据userId和资源级别查询资源
     */
    public List<VRoleAuthView> findResourcesByUserIdAndResourceLevelAndParentId(Long userId, Long resourceLevel, Long parentId) {
        //首先根据用户id查询出用户对应的角色
        List<VRoleAuthView> vRoleAuthViewList = null;
        User user = userService.findById(userId);
        List<Long> idList = new ArrayList<Long>();
        if (user != null) {
            List<Role> roleList = user.getRoleList();
            for (Role r : roleList) {
                idList.add(r.getId());
            }
            vRoleAuthViewList = vRoleAuthViewRepository.findByRoleListAndResourceLevelAndParentId(idList, resourceLevel, parentId);
        }
        return vRoleAuthViewList;
    }


    /**
     * 根据AppName查询应用菜单
     *
     * @param httpSession
     * @param controllerName
     * @return
     */
    public List<VRoleAuthView> findAppMenusByController(HttpSession httpSession, String controllerName) {
        User user = SessionUtil.getCurrentUserBySession(httpSession);
        List<Role> roleList = user.getRoleList();
        String appName[] = controllerName.split("Controller");
        return vRoleAuthViewRepository.findByRoleListAppName(roleList, appName[0].toUpperCase());
    }


    /**
     * @param url
     * @return 根据url查询资源集合
     */
    public Boolean exists(String url, String staticFlag) {
        List<Resource> resourceList = resourceRepository.findByResourceUrlStartingWithAndStaticFlag(url, staticFlag);
        return !resourceList.isEmpty();
    }


    /**
     * @param url
     * @return 根据url查询资源集合
     */
    public Resource register(String url) {
        Resource resource = null;
        if (url != null && !url.equals("")) {
            resource = new Resource();
            resource.setResourceUrl(url);
            resource.setResourceLevel(-1l);
            resource.setResourceName("静态资源");
            resource.setAppName("无");
            resource.setIconClass("");
            resource.setResourceCode(new Date().getTime() + "");
            resource.setSortNo(0l);
            resource.setStatus("1");
            resourceRepository.save(resource);
        }


        return resource;
    }


    /**
     * @param roleId     角色id
     * @param resourceId 资源id
     */
    public void deleteRoleAuth(Long roleId, Long resourceId) {
        List<Resource> newResourceList = new ArrayList<Resource>();
        Role role = roleService.findById(roleId);
        List<Resource> resourceList = role.getResourceList();
        for (Resource resource : resourceList) {
            if (resource.getId() != null && resource.getId() != resourceId) {
                newResourceList.add(resource);
            }
        }
        role.setResourceList(newResourceList);
        roleService.save(role);
    }
}
