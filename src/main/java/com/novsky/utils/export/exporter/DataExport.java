package com.novsky.utils.export.exporter;


import com.novsky.utils.export.docType.DocType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Bill on 2016/11/2.
 * 数据导出接口
 */
public interface DataExport {

    /**
     * @param docType
     * @param request
     * @param response
     * @param titles
     * @param colNames
     * @param dataList
     * @param docName
     * @throws Exception
     */
    void export(DocType docType, HttpServletRequest request, HttpServletResponse response, String[] titles, String[] colNames, List dataList, String docName) throws Exception;
}
