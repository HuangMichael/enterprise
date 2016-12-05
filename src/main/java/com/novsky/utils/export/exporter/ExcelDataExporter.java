package com.novsky.utils.export.exporter;


import com.novsky.utils.StringUtils;
import com.novsky.utils.export.docType.DocType;
import com.novsky.utils.StringUtils;
import com.novsky.utils.export.docType.DocType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.hibernate.service.spi.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by bill on 2016/11/2.
 * 数据导出实现类
 */
@Data
@Getter
@Setter
public class ExcelDataExporter implements DataExport {


    private HSSFWorkbook hssfWorkbook;

    private HSSFSheet hssfSheet;

    private HSSFCellStyle hssfCellStyle;
    private DocType docType;

    /**
     * @param request
     * @param response
     * @param docName
     * @return
     * @throws Exception
     */
    public HSSFWorkbook createHSSFWorkbook(HttpServletRequest request, HttpServletResponse response, String docName) throws Exception {

        if (hssfWorkbook == null) {
            hssfWorkbook = new HSSFWorkbook();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");
            String fileName = docName + ".xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        }
        return hssfWorkbook;
    }


    /**
     * @param docName
     * @return
     */
    public HSSFSheet createSheet(String docName) {
        if (hssfSheet == null) {
            //创建sheet
            hssfSheet = hssfWorkbook.createSheet(docName);
            hssfSheet.setDefaultRowHeight((short) (256));
            hssfSheet.setDefaultColumnWidth((short) (20));
            hssfSheet.setFitToPage(true);
            HSSFFont font = this.getHssfWorkbook().createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 16);
        }
        return hssfSheet;
    }

    /**
     * @return 创建样式
     */
    public HSSFCellStyle createStyle() {

        if (hssfCellStyle == null) {
            //创建style
            hssfCellStyle = this.getHssfWorkbook().createCellStyle();
            hssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            hssfCellStyle.setAlignment(HorizontalAlignment.LEFT);
        }
        return hssfCellStyle;
    }


    /**
     * 重写抽象类的导出方法
     */

    public void exportData(List titles, List colNames, List list) throws Exception {

        hssfSheet = this.getHssfSheet();
        HSSFRow row = hssfSheet.createRow(0);
        hssfSheet.createRow(1);
        hssfSheet.createRow(2);
        hssfSheet.createRow(3);
        hssfSheet.createRow(4);


        for (int i = 0; i < titles.size(); i++) {

            if (titles.get(i).equals("")) {

                continue;
            }
            if (titles.get(i) != null) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(titles.get(i).toString());
                cell.setCellStyle(this.getHssfCellStyle());
            }

        }
        Method method = null;
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = getHssfSheet().createRow(i + 1);
            row1.setRowStyle(this.getHssfCellStyle());
            Object object = list.get(i);
            for (int j = 0; j < colNames.size(); j++) {

                if (colNames.get(j).equals("")) {

                    continue;
                }


                if (j > 0 && colNames.get(j) != null) {
                    try {
                        method = object.getClass().getMethod("get" + StringUtils.upperCaseCamel(colNames.get(j).toString()));
                        Object obj = method.invoke(object);
                        if (obj != null) {
                            row1.createCell(j).setCellValue(obj.toString());
                        } else {
                            row1.createCell(j).setCellValue("");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                } else {
                    row1.createCell(j).setCellValue(i + 1);
                }

            }


        }


    }


    /**
     * @param response
     * @throws Exception
     */
    public void closeStream(HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        HSSFWorkbook wb = this.getHssfWorkbook();
        try {
            wb.write(out);
            out.close();
        } catch (ServiceException e) {
            System.out.println("=====导出excel异常====");
        } catch (Exception e1) {
            System.out.println("=====导出excel异常====");
        }
    }

    /**
     * @param request
     * @param response
     * @param titles   标题
     * @param colNames 字段描述
     * @param dataList 数据集合
     * @param docName  文档名称  sheet名称
     * @throws Exception
     */
    public void export(DocType docType, HttpServletRequest request, HttpServletResponse response, String[] titles, String[] colNames, List dataList, String docName) throws Exception {

        List<String> titleList = StringUtils.removeNullValue(titles);
        List<String> colNameList = StringUtils.removeNullValue(colNames);
        createHSSFWorkbook(request, response, docName);
        createSheet(docName);
        createStyle();
        exportData(titleList, colNameList, dataList);
        closeStream(response);

    }
}
