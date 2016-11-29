package com.linkbit.beidou.controller.line;


import com.linkbit.beidou.controller.common.BaseController;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.domain.matCost.MatCost;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.line.LineService;
import com.linkbit.beidou.service.line.StationSearchService;
import com.linkbit.beidou.service.line.StationService;
import com.linkbit.beidou.utils.PageUtils;
import com.linkbit.beidou.utils.StringUtils;
import com.linkbit.beidou.utils.export.docType.ExcelDoc;
import com.linkbit.beidou.utils.export.exporter.DataExport;
import com.linkbit.beidou.utils.export.exporter.ExcelDataExporter;
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
 * 设备台账控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/station")
public class StationController extends BaseController {


    @Autowired
    StationService stationService;
    @Autowired
    LineService lineService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    StationSearchService stationSearchService;

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
        return new PageUtils().searchBySortService(stationSearchService, searchPhrase, 3, current, rowCount, pageable);
    }

    /**
     * 根据线路查询站点
     */
    @RequestMapping(value = "/findStationByLine/{lineId}")
    @ResponseBody
    public List<Station> findStationByLine(@PathVariable("lineId") Long lineId) {
        Line line = lineService.findById(lineId);
        if (null != line) {
            return stationService.findStationsByLine(line);
        } else {
            return null;
        }
    }


    /**
     * 新建记录
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/station/create";
    }

    /**
     * 根据所有车站信息
     */
    @RequestMapping(value = "/findByStatus")
    @ResponseBody
    public List<Station> findByStatus() {
        List<Station> stationList = stationService.findByStatus("1");
        return stationList;
    }

    /**
     * @param id 根据id查询车站信息
     * @return
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public Station findById(@PathVariable("id") long id) {
        Station station = stationService.findById(id);
        return station;
    }


    /**
     * @param station
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(Station station) {
        station = stationService.save(station);
        return commonDataService.getReturnType(station != null, "车站信息保存成功!", "车站信息保存失败!");
    }


    /**
     * 查询激活状态的人员信息
     *
     * @return
     */
    @RequestMapping(value = "/findActiveStation")
    @ResponseBody
    public List<Station> findActivePerson() {
        List<Station> stationList = stationService.findActiveStation();
        return stationList;
    }


    /**
     * @param id id
     * @return 根据删除车站信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id) {
        return stationService.delete(id);
    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllIds() {
        return stationService.findAllIds();
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
        List<Station> dataList = stationSearchService.findByConditions(param, 3);
        stationSearchService.setDataList(dataList);
        stationSearchService.exportExcel(request, response, docName, titles, colNames);
    }
}
