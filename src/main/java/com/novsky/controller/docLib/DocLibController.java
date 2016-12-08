package com.novsky.controller.docLib;/**
 * Created by Administrator on 2016/12/8.
 */

import com.novsky.controller.common.BaseController;
import com.novsky.domain.docLib.DocLib;
import com.novsky.domain.equipments.Equipments;
import com.novsky.service.docLib.DocLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 文档库控制器
 *
 * @author
 * @create 2016-12-08 14:32
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping("/docLib")
public class DocLibController extends BaseController {


    @Autowired
    DocLibService docLibService;

    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<DocLib> findAll() {
        return docLibService.findAll();

    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public DocLib findById(@PathVariable("id") Long id) {
        return docLibService.findById(id);

    }

}
