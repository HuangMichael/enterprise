package com.linkbit.beidou.controller.user;


import com.linkbit.beidou.controller.common.BaseController;
import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.dao.person.PersonRepository;
import com.linkbit.beidou.dao.user.UserRepository;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.locations.Vlocations;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.user.UserSearchService;
import com.linkbit.beidou.service.user.UserService;
import com.linkbit.beidou.utils.CommonStatusType;
import com.linkbit.beidou.utils.MD5Util;
import com.linkbit.beidou.utils.PageUtils;
import com.linkbit.beidou.utils.SessionUtil;
import com.linkbit.beidou.utils.search.SortedSearchable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
@Data
public class UserController extends BaseController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserSearchService userSearchService;


    @Autowired
    PersonRepository personRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;

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
    public MyPage data(HttpServletRequest request,
                       @RequestParam(value = "current", defaultValue = "0") int current,
                       @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount,
                       @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(userSearchService, searchPhrase, 2, current, rowCount, pageable);
    }


    @RequestMapping(value = "/personal")
    public String personal() {
        return "/user/personal";
    }


    @RequestMapping(value = "/create")
    public String create() {
        return "/user/create";
    }


    /**
     * 保存用户信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject save(User user) {
        user = userService.save(user);
        return commonDataService.getReturnType(user != null, "用户信息保存成功", "用户信息保存失败");
    }


    /**
     * 保存用户信息
     *
     * @param personId
     * @param locationId
     * @return 创建用户
     */
    @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject createUser(@RequestParam("userName") String userName, @RequestParam("personId") Long personId, @RequestParam("locationId") Long locationId) {
        User user = new User();
        user.setUserName(userName);
        user.setPerson(personRepository.findById(personId));
        Vlocations vlocations = vlocationsRepository.findById(locationId);
        user.setVlocations(vlocations);
        user.setLocation(vlocations.getLocation());
        user = userService.createUser(user);
        ReturnObject returnObject = new ReturnObject();
        returnObject.setResult(user != null);
        String resStr = returnObject.getResult() ? "成功" : "失败";
        returnObject.setResultDesc("用户信息创建" + resStr);
        returnObject.getObjectsList().add(user);
        return returnObject;
    }

    /**
     * @param session  当前会话
     * @param modelMap 显示个人信息
     * @return
     */
    @RequestMapping(value = "/profile")
    public String profile(HttpSession session, ModelMap modelMap) {
        User user = SessionUtil.getCurrentUserBySession(session);
        modelMap.put("user", user);
        return "/user/profile";
    }


    /**
     * @param id 用户id
     * @return 根据用户id查询
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    /**
     * @return 修改密码
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject changePwd(@RequestParam("userName") String userName, @RequestParam("newPwd") String newPwd) {
        ReturnObject returnObject = new ReturnObject();
        boolean result = userService.changePwd(userName, newPwd);
        returnObject.setResult(result);
        if (returnObject.getResult()) {
            returnObject.setResultDesc("用户密码修改成功!");
        } else {
            returnObject.setResultDesc("用户密码修改失败!");
        }
        return returnObject;
    }


    /**
     * 检查用户密码合法性
     */
    @RequestMapping(value = "/checkPwd", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject checkPwd(@RequestParam("userName") String userName, @RequestParam("oldPwd") String oldPwd) {
        ReturnObject returnObject = new ReturnObject();
        oldPwd = MD5Util.md5(oldPwd);
        List<User> userList = userService.findByUserNameAndPasswordAndStatus(userName, oldPwd, CommonStatusType.STATUS_YES);
        if (!userList.isEmpty()) {
            returnObject.setResult(true);
            returnObject.setResultDesc("用户密码验证通过!");
        } else {
            returnObject.setResult(false);
            returnObject.setResultDesc("用户密码验证失败!");
        }
        return returnObject;

    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    List<Long> findAllIds() {
        return userService.selectAllId();
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
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("param") String param, @RequestParam("docName") String docName, @RequestParam("titles") String titles[], @RequestParam("colNames") String[] colNames, @RequestParam("sort") String[] sort) {
        List<User> dataList = userSearchService.findByConditions(param, 2);
        userService.setDataList(dataList);
        userService.exportExcel(request, response, docName, titles, colNames);
    }

}
