package com.novsky.controller.resource;

import com.novsky.controller.common.BaseController;
import com.novsky.domain.app.resoure.Resource;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.object.ReturnObject;
import com.novsky.service.commonData.CommonDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 资源控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonDataService commonDataService;

    /**
     * 查询所有的节点
     */

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> findAll() {
        List<Resource> resourceList = resourceService.findAll(); //查询二级模块
        return resourceList;
    }

    /**
     * 查询所有的节点
     */

    @RequestMapping(value = "/findApps", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> findApps() {
        List<Resource> resourceList = resourceService.findAll(); //查询二级模块
        return resourceList;
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/create/{id}")
    public String create(@PathVariable("id") Long id, ModelMap modelMap) {
        Resource parent = resourceService.findById(id);
        Resource newObj = new Resource();
        newObj.setParent(parent);
        Long level = (parent.getResourceLevel() != null) ? parent.getResourceLevel() + 1 : 0l;
        newObj.setResourceLevel(level);
        //加载上级列表
        List<Resource> resourceList = resourceService.findAll();
        modelMap.put("resourceList", resourceList);
        modelMap.put("resource", newObj);
        modelMap.put("parent", parent);
        //查询出所有的设备分类
        return "/resource/create";
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap modelMap) {
        String url = "/resource";
        if (id != null) {
            url += "/detail";
        } else {
            url += "/list/";
        }
        Resource resource = resourceService.findById(id);
        List<Resource> resourceList = resourceService.findByParent(resource);
        modelMap.put("resource", resource);
        modelMap.put("resourceList", resourceList);
        return url;
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resource findById(@PathVariable("id") Long id) {
        Resource resource = resourceService.findById(id);
        return resource;
    }

    /**
     * 保存资源信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(Resource resource) {
        resource = resourceService.save(resource);
        return commonDataService.getReturnType(resource != null, "资源信息保存成功", "资源信息保存失败");
    }


    /**
     * 删除位置信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        boolean hasChildren = resourceService.hasChildren(id);
        Resource resource = null;
        if (!hasChildren) {
            resourceService.delete(id);
            resource = resourceService.findById(id);
        }
        return (resource == null);
    }
}
