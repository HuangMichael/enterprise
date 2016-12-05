package com.novsky.controller.common;


import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.domain.budget.VbudgetBill;
import com.novsky.domain.equipments.Vequipments;
import com.novsky.service.app.BaseService;
import com.novsky.service.app.ResourceService;
import com.novsky.utils.PageUtils;
import com.novsky.utils.SessionUtil;
import com.novsky.utils.StringUtils;
import com.novsky.utils.export.docType.ExcelDoc;
import com.novsky.utils.export.exporter.DataExport;
import com.novsky.utils.export.exporter.ExcelDataExporter;
import com.novsky.domain.app.resoure.VRoleAuthView;
import com.novsky.service.app.ResourceService;
import com.novsky.utils.SessionUtil;
import com.novsky.utils.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础控制器
 */
@Controller
@Data
@EnableAutoConfiguration
public class BaseController {

    @Autowired
    ResourceService resourceService;
    String userLocation;


    @RequestMapping(value = "/list")
    public String list(HttpSession httpSession, ModelMap modelMap) {
        //加载查询菜单
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        String url = "/" + StringUtils.lowerCaseCamel(controllerName) + "/list";
        System.out.println("url----------" + url);
        return url;
    }


    /**
     * @param httpSession session回话
     * @return 获取当前用户所在位置
     */
    public String getUserLocation(HttpSession httpSession) {
        userLocation = SessionUtil.getCurrentUserLocationBySession(httpSession);
        return userLocation;
    }

    /**
     * @param requestMap
     * @return 获取排序的map
     * 第0位为排序顺序  asc desc
     * 第1位为排序字段
     */
    public Sort getSort(Map<String, String[]> requestMap) {
        String sortName = "id";
        Sort.Direction direction = Sort.Direction.ASC; //默认升序排列
        String directionStr = "asc";
        for (String key : requestMap.keySet()) {
            //如果key中以sort开头 获取它的值
            if (key.startsWith("sort")) {
                sortName = (key != null) ? key.split("\\[|\\]")[1] : sortName;
                directionStr = (requestMap.get(key)[0] != null) ? requestMap.get(key)[0] : directionStr;
                break;
            }
        }
        if (directionStr.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = new Sort(direction, sortName);
        return sort;
    }
}

