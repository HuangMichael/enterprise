package com.novsky.controller.person;


import com.novsky.controller.common.BaseController;
import com.novsky.domain.app.MyPage;
import com.novsky.domain.person.Person;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.ResourceService;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.person.PersonSearchService;
import com.novsky.service.person.PersonService;
import com.novsky.utils.PageUtils;
import com.novsky.object.ReturnObject;
import com.novsky.service.commonData.CommonDataService;
import com.novsky.service.person.PersonSearchService;
import com.novsky.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/person")
public class PersonController extends BaseController {

    @Autowired
    PersonSearchService personSearchService;

    @Autowired
    PersonService personService;


    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonDataService commonDataService;


    /**
     * 分页查询
     *
     * @param request      http请求
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
        return new PageUtils().searchBySortService(personSearchService, searchPhrase, 2, current, rowCount, pageable);
    }


    /**
     * @param person
     * @return 保存人员信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(Person person) {
        person = personService.save(person);
        return commonDataService.getReturnType(person != null, "人员信息保存成功!", "人员信息保存失败!");
    }


    /**
     * @param person 人员信息
     * @return 新建人员信息
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Person create(Person person) {
        return personService.save(person);
    }


    /**
     * 查询激活状态的人员信息
     *
     * @return
     */
    @RequestMapping(value = "/findActivePerson")
    @ResponseBody
    public List<Person> findActivePerson() {
        List<Person> personList = personService.findActivePerson();
        return personList;
    }


    /**
     * 根据ID查询人员信息
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    List<Long> findAllIds() {
        return personService.selectAllId();
    }


    /**
     * @return 载入新建人员表单
     */
    @RequestMapping(value = "/loadCreateForm", method = RequestMethod.GET)
    public String loadCreateForm() {
        return "/person/create";
    }


    /**
     * 根据ID查询人员信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        boolean result = personService.delete(id);
        return commonDataService.getReturnType(result, "人员信息删除成功!", "人员信息删除失败!");

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
        List<Person> dataList = personSearchService.findByConditions(param, 2);
        personService.setDataList(dataList);
        personService.exportExcel(request, response, docName, titles, colNames);
    }

}
