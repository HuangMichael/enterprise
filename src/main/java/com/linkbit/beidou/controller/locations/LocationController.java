package com.linkbit.beidou.controller.locations;

import com.linkbit.beidou.controller.common.BaseController;
import com.linkbit.beidou.dao.equipments.VequipmentsRepository;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.locations.LocationsService;
import com.linkbit.beidou.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 位置控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/location")
public class LocationController extends BaseController {

    @Autowired
    LocationsService locationsService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    VequipmentsRepository vequipmentsRepository;
    @Autowired
    CommonDataService commonDataService;


    /**
     * @param id
     * @param modelMap
     * @param session
     * @return 根据ID显示位置信息 显示明细
     */
    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap modelMap, HttpSession session) {
        String url = "/location";
        Locations object = locationsService.findById(id);

        if (id != 0) {
            url += "/locEqList";

        }
        List<Vequipments> equipmentsList = vequipmentsRepository.findByLocationStartingWith(object.getLocation());
        modelMap.put("equipmentsList", equipmentsList);
        return url;
    }


    /**
     * @param request
     * @return 查询所有的位置信息
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Locations> findAll(HttpServletRequest request) {
        List<Locations> locationsList = (List<Locations>) request.getSession().getAttribute("locationsList");
        if (locationsList.isEmpty()) {
            locationsList = locationsService.findAll();
        }

        return locationsList;
    }


    /**
     * @param httpSession 当前会话
     * @return 查询的位置树节点集合
     */
    @RequestMapping(value = "/findTree")
    @ResponseBody
    public List<Object> findTree(HttpSession httpSession) {
        List<Object> objectList = null;
        User user = SessionUtil.getCurrentUserBySession(httpSession);
        if (user.getLocation() != null && !user.getLocation().equals("")) {
            objectList = locationsService.findTree(user.getLocation() + "%");
        }
        return objectList;
    }

    /**
     * @param locLevel 节点级别
     * @return 查询节点级别小于 locLevel的记录
     */
    @RequestMapping(value = "/findByLocLevelLessThan/{locLevel}")
    @ResponseBody
    public List<Locations> findBylocLevelLessThan(@PathVariable("locLevel") Long locLevel) {
        List<Locations> locationsList = locationsService.findByLocLevelLessThan(locLevel);
        return locationsList;
    }


    /**
     * @param id
     * @return 新建位置信息
     */
    @RequestMapping(value = "/create/{id}")
    @ResponseBody
    public Locations create(@PathVariable("id") Long id) {
        Locations locations = locationsService.create(id);
        locations.setStatus("1");
        return locations;
    }


    /**
     * @param locations
     * @return 保存位置信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(Locations locations) {
        locations = locationsService.save(locations);
        return commonDataService.getReturnType(locations != null, "位置信息保存成功!", "位置信息保存失败!");

    }

    /**
     * @param id
     * @return 根据id查询
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public Locations findById(@PathVariable("id") Long id) {
        return locationsService.findById(id);

    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        Locations locations = locationsService.findById(id);
        Boolean deleted = locationsService.delete(locations);
        return commonDataService.getReturnType(deleted, "位置信息删除成功！", "位置信息删除失败,数据有关联，请联系管理员!");
    }


    /**
     * @param pid 上级节点
     * @return 根据上级节点id查询
     */
    @RequestMapping(value = "/findNodeByParentId/{pid}")
    @ResponseBody
    public List<Locations> findNodeByParentId(@PathVariable("pid") Long pid) {
        List<Locations> locationsList = locationsService.findByParentId(pid);
        return locationsList;
    }


    /**
     * @return 根据上级节点id查询
     */
    @RequestMapping(value = "/loadReportForm")
    public String loadReportForm() {
        return "/location/locationReport2";
    }

}
