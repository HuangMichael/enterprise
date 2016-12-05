package com.novsky.utils.export.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangbin on 2016/11/4.
 * 定义接口可导出
 */
public interface Exportable {


    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 响应
     * @param docName  文档名称
     * @param titles   标题集合
     * @param colNames 字段集合
     * @param dataList 数据集
     */
    void exportExcel(HttpServletRequest request, HttpServletResponse response, String docName, String titles[], String[] colNames, List dataList);
}
