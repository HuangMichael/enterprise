package com.novsky.controller.docLib;/**
 * Created by Administrator on 2016/12/8.
 */

import com.novsky.controller.common.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
