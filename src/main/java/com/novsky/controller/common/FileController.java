package com.novsky.controller.common;


import com.novsky.dao.locations.LocationsRepository;
import com.novsky.service.org.SysInfoService;
import com.novsky.utils.SessionUtil;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/file")
@Data
public class FileController implements ServletContextAware {

    @Autowired
    LocationsRepository locationsRepository;

    //Spring这里是通过实现ServletContextAware接口来注入ServletContext对象
    ServletContext servletContext;


    Log log = LogFactory.getLog(FileController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public
    @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Boolean handleFileUpload(@RequestParam("llid") Long llid, @RequestParam("name") String name, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String contextPath = SessionUtil.getContextPath(request);
        String realPath = "img\\app\\locations\\" + name;
        String filePath = contextPath + realPath;
        log.info(contextPath);
        log.info(realPath);
        log.info(filePath);
        return true;

    }



}



