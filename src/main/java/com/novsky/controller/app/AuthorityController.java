package com.novsky.controller.app;


import com.novsky.dao.app.resource.VRoleAuthViewRepository;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.app.resoure.Resource;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.role.Role;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.role.RoleService;
import com.novsky.service.user.UserService;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/authority")
public class AuthorityController  {
    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;
    @Autowired
    VRoleAuthViewRepository vRoleAuthViewRepository;

    @Autowired
    UserService userService;


    /**
     * 初始化展示授权列表
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap) {
        //加载角色列表 显示所有的角色
        List<Role> roleList = roleService.findByStatus("1");
        modelMap.put("roleList", roleList);

        Role role = roleService.findById(1l);
        List<VRoleAuthView> vRoleAuthViews = vRoleAuthViewRepository.findByRole(role);
        modelMap.put("vRoleAuthViews", vRoleAuthViews);
        return "/authority/list";
    }


    /**
     * 初始化展示授权列表
     *
     * @param roleId      角色ID
     * @param resourceIds 资源的ID字符串
     * @return
     */
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject grant(@RequestParam("roleId") Long roleId, @RequestParam("resourceIds") String resourceIds) {
        ReturnObject returnObject = new ReturnObject();
        if (roleId == null) {
            returnObject.setResult(false);
            returnObject.setResultDesc("应用授权失败,请选择要授权的角色!");
        } else if (resourceIds == null || resourceIds.equals("")) {
            returnObject.setResult(false);
            returnObject.setResultDesc("应用授权失败,请选择要授权的资源!");
        } else {
            Role role = roleService.findById(roleId);
            List<Resource> resourceList = role.getResourceList();
            List<Resource> resourceListInIdStr = resourceService.findResourceListInIdStr(resourceIds);
            for (Resource resource : resourceListInIdStr) {
                if (!resourceList.contains(resource))
                    resourceList.add(resource);
            }
            role.setResourceList(resourceList);
            roleService.save(role);
            returnObject.setResult(true);
            returnObject.setResultDesc("应用授权成功!");
        }
        return returnObject;
    }

    /**
     * @param roleId
     * @param modelMap
     * @return 根据角色ID载入用户权限预览
     */
    @RequestMapping(value = "/loadAuthView/{roleId}", method = RequestMethod.GET)
    public String loadAuthView(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        List<VRoleAuthView> vRoleAuthViews = vRoleAuthViewRepository.findByRole(role);
        modelMap.put("vRoleAuthViews", vRoleAuthViews);
        return "resource/authList";
    }


    /**
     * @param roleId
     * @param modelMap
     * @return 根据角色ID载入用户权限预览
     */
    @RequestMapping(value = "/loadByRole/{roleId}", method = RequestMethod.GET)
    public String loadByRole(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        List<VRoleAuthView> vRoleAuthViews = vRoleAuthViewRepository.findByRole(role);
        modelMap.put("vRoleAuthViews", vRoleAuthViews);
        return "resource/authList";
    }


    @RequestMapping(value = "/loadByRole/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public MyPage loadByRole(@RequestParam(value = "current", defaultValue = "0") int current,
                             @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount,
                             @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
                             @PathVariable(value = "roleId") Long roleId) {

        Role role = roleService.findById(roleId);
        Page<VRoleAuthView> page = vRoleAuthViewRepository.findByRoleAndResourceNameContaining(role, searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    /**
     * @param roleId
     * @param modelMap
     * @return 根据角色ID载入用户权限预览
     */
    @RequestMapping(value = "/loadAuth/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VRoleAuthView> loadAuth(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        List<VRoleAuthView> authViewList = vRoleAuthViewRepository.findByRole(role);
        return authViewList;
    }


    /**
     * @param userId
     * @return 根据用户ID载入用户权限预览
     */
    @RequestMapping(value = "/loadModule/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VRoleAuthView> loadAuthViewByUserId(@PathVariable("userId") Long userId) {
        List<VRoleAuthView> vRoleAuthViews = resourceService.findResourcesByUserIdAndResourceLevel(userId, 1l);
        return vRoleAuthViews;
    }


    /**
     * @param userId
     * @return 根据用户ID载入用户权限预览
     */


    @RequestMapping(value = "/loadApp/{moduleId}/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VRoleAuthView> loadAuthViewByUserIdAndModuleId(@PathVariable("moduleId") Long moduleId, @PathVariable("userId") Long userId) {
        List<VRoleAuthView> vRoleAuthViews = resourceService.findResourcesByUserIdAndResourceLevelAndParentId(userId, 2l, moduleId);
        return vRoleAuthViews;
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
        List<VRoleAuthView> dataList = resourceService.findByRoleAndResourceNameContaining(1l, param);
        resourceService.setDataList(dataList);
        resourceService.exportExcel(request, response, docName, titles, colNames);
    }

    /**
     * @param roleId     角色id
     * @param resourceId 资源id
     */
    @ResponseBody
    @RequestMapping(value = "/revoke", method = RequestMethod.POST)
    public void revoke(@RequestParam("roleId") Long roleId, @RequestParam("resourceId") Long resourceId) {
        resourceService.deleteRoleAuth(roleId, resourceId);
    }
}
