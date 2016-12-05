package com.novsky.controller.role;


import com.novsky.controller.common.BaseController;
import com.novsky.dao.role.RoleRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.person.Person;
import com.novsky.domain.role.Role;
import com.novsky.domain.user.User;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.role.RoleSearchService;
import com.novsky.service.role.RoleService;
import com.novsky.utils.PageUtils;
import com.novsky.dao.role.RoleRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.role.Role;
import com.novsky.object.ReturnObject;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.role.RoleSearchService;
import com.novsky.service.role.RoleService;
import com.novsky.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleService roleService;

    @Autowired
    RoleSearchService roleSearchService;


    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonDataService commonDataService;


    /**
     * 分页查询
     *
     * @param current      当前页
     * @param rowCount     每页条数
     * @param searchPhrase 查询关键字
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(HttpServletRequest request, @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(roleSearchService, searchPhrase, 2, current, rowCount, pageable);
    }


    @RequestMapping(value = "/create")
    public String create() {
        return "/role/create";
    }


    /**
     * 保存角色信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject save(Role role) {
        role = roleService.save(role);
        return commonDataService.getReturnType(role != null, "角色信息保存成功!", "角色信息保存失败!");
    }


    /**
     * 载入明细信息
     */
    @RequestMapping(value = "/detail/{roleId}")
    public String detail(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleRepository.findById(roleId);
        modelMap.put("role", role);
        return "/role/detail";
    }


    /**
     * 保存角色信息
     *
     * @return 查询在用的角色
     */
    @RequestMapping(value = "/findActiveRole", method = {RequestMethod.GET})
    @ResponseBody
    public List<Role> findActiveRole() {
        return roleService.findActiveRole();

    }


    /**
     * @return 查询在用的角色
     */
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }


    /**
     * 载入明细信息
     */
    @RequestMapping(value = "/popUsers/{roleId}", method = RequestMethod.GET)
    public String popUsers(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        List<Object> usersNotInRole = roleService.findUsersNotInRole(roleId);
        modelMap.put("usersNotInRole", usersNotInRole);
        return "/role/popUsers";
    }


    /**
     * 载入明细信息
     */
    @RequestMapping(value = "/loadMyUsers/{roleId}", method = RequestMethod.GET)
    public String loadMyUsers(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        modelMap.put("role", role);
        return "/role/myUsers";
    }

    /**
     * @return 查询不在当前角色中的用户
     */
    @RequestMapping(value = "/findUsersNotInRole/{roleId}", method = {RequestMethod.GET})
    @ResponseBody
    public List<Object> findUsersNotInRole(@PathVariable("roleId") Long roleId) {
        return roleService.findUsersNotInRole(roleId);
    }


    /**
     * @return 查询不在当前角色中的用户
     */
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject addUsers(@RequestParam("roleId") Long roleId, @RequestParam("usersIdStr") String usersIdStr) {
        return roleService.addUsers(roleId, usersIdStr);
    }

    /**
     * @param roleId
     * @return 根据角色查询用户列表
     */
    @RequestMapping(value = "/findUsersOfRole/{roleId}", method = {RequestMethod.GET})
    @ResponseBody
    public List<User> findUsersOfRole(@PathVariable("roleId") Long roleId) {
        return roleService.findUsersOfRole(roleId);
    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = {RequestMethod.GET})
    @ResponseBody
    public List<Long> findAllIds() {
        return roleService.findAllIds();
    }


    /**
     * @return 查询不在当前角色中的用户
     */
    @RequestMapping(value = "/removeUser", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject removeUser(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {
        return roleService.removeUser(roleId, userId);
    }


    /**
     * @param request  请求
     * @param response 响应
     * @param param    查询关键字
     * @param docName  文档名称
     * @param titles   标题集合
     * @param colNames 列名称
     */
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("param") String param, @RequestParam("docName") String docName, @RequestParam("titles") String titles[], @RequestParam("colNames") String[] colNames) {
        List<Role> dataList = roleSearchService.findByConditions(param, 2);
        roleService.setDataList(dataList);
        roleService.exportExcel(request, response, docName, titles, colNames);
    }


}





