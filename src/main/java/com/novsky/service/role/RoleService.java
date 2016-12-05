package com.novsky.service.role;

import com.novsky.dao.role.RoleRepository;
import com.novsky.dao.user.UserRepository;
import com.novsky.domain.role.Role;
import com.novsky.domain.user.User;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.BaseService;
import com.novsky.utils.CommonStatusType;
import com.novsky.utils.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色业务类
 */
@Service
public class RoleService extends BaseService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    /**
     * 根据状态查询用户
     */
    public List<Role> findByStatus(String status) {
        return roleRepository.findByStatus(status);
    }


    /**
     * 根据ID查询角色
     */
    public Role findById(Long id) {
        return roleRepository.findById(id);
    }


    /**
     * @param roleDesc 角色描述
     * @return 根据角色描述关键字进行查询
     */
    public Page<Role> findByRoleDescContains(String roleDesc, Pageable pageable) {
        return roleRepository.findByRoleDescContains(roleDesc, pageable);
    }


    /**
     * @param role
     * @return 保存角色信息
     */
    public Role save(Role role) {

        if (role.getStatus() == null) {
            role.setStatus(CommonStatusType.STATUS_YES);
        }
        return roleRepository.save(role);
    }


    /**
     * @return 查询在用角色
     */
    public List<Role> findActiveRole() {
        return roleRepository.findByStatus(CommonStatusType.STATUS_YES);
    }


    /**
     * 查询出不在当前角色中的人员
     */

    public List<Object> findUsersNotInRole(Long roleId) {
        return userRepository.findUsersNotInRole(roleId);
    }

    /**
     * @param roleId
     * @param usersIdStr
     * @return 添加用户
     */

    public ReturnObject addUsers(Long roleId, String usersIdStr) {
        ReturnObject returnObject = new ReturnObject();
        Role role = roleRepository.findById(roleId);
        if (usersIdStr != null && !usersIdStr.equals("")) {
            String[] ids = usersIdStr.split(",");
            List<User> userList = role.getUserList();
            for (String id : ids) {
                User user = userRepository.findById(Long.parseLong(id));
                if (!userList.contains(user)) {
                    userList.add(user);
                }
            }
            role.setUserList(userList);
            roleRepository.save(role);
        }
        returnObject.setResult(true);
        returnObject.setResultDesc("角色添加用户成功");

        return returnObject;
    }


    /**
     * @param roleId
     * @return
     */
    public List<User> findUsersOfRole(Long roleId) {
        List<User> userList = new ArrayList<User>();
        if (roleId != null) {
            userList = userRepository.findUserListByRoleId(roleId);
        }
        return userList;
    }


    /**
     * @return 查询所有的id集合
     */
    public List<Long> findAllIds() {
        return roleRepository.findAllId();
    }


    /**
     * @param roleId 角色id
     * @param userId 用户id
     * @return 移除用户
     */
    public ReturnObject removeUser(Long roleId, Long userId) {
        ReturnObject returnObject = new ReturnObject();
        Role role = roleRepository.findById(roleId);
        List<User> usersOfRole = role.getUserList();
        User user = userRepository.findById(userId);
        if (usersOfRole.contains(user)) {
            usersOfRole.remove(user);
            role.setUserList(usersOfRole);
            roleRepository.save(role);
            returnObject.setResult(true);
            returnObject.setResultDesc("角色移除用户成功!");
        } else {
            returnObject.setResult(false);
            returnObject.setResultDesc("角色移除用户失败!");
        }
        return returnObject;
    }


}
