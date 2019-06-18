package com.cms.scaffold.common.util.excel;

import com.cms.scaffold.common.util.DateUtil;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/5/1.
 */
public class ExcelExportUtil {

    static Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);

    private static final Integer rownum = 10000;

    /**
     * 只能接受百万以内的数据
     * 使用多线程进行Excel写操作，提高写入效率。
     */
    public static void multiExportSXSSFExcel(OutputStream out, List<Object> list, String title,
                                             String[] hearders, String[] fields) throws Exception {
        if(list.size()> rownum * 100){
            logger.warn("导出数据太大，不进行导出");
            return;
        }
        long beginTime = System.currentTimeMillis();
        String excelTitle = title + "_" + com.cms.scaffold.common.util.DateUtil.dateStr3(com.cms.scaffold.common.util.DateUtil.getNow());
        TableData tableData = null;
        logger.info("数据处理");
        tableData = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        long endTime = System.currentTimeMillis();
        logger.info("拼接时间导出 : " + (endTime - beginTime));
        SXSSFWorkbook workbook = new SXSSFWorkbook(100000);
        workbook = multiWriteSheet(workbook, excelTitle, tableData);
        long endTime1 = System.currentTimeMillis();
        logger.info("拼接时间1 : " + (endTime1 - endTime));
        workbook.write(out);
        out.close();
        workbook.dispose();

    }

    /**
     * Excel报表导出通用方法
     *
     * @param list
     * @param title
     * @param hearders
     * @param fields
     * @throws Exception
     */
    public static void exportSXSSFExcel(HttpServletResponse response, List<Object> list, String title,
                                        String[] hearders, String[] fields,Map convertorMap) throws Exception {
        String excelTitle = title + "_" + com.cms.scaffold.common.util.DateUtil.dateStr3(com.cms.scaffold.common.util.DateUtil.getNow());
        TableData tableData = null;
        tableData = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields,convertorMap);
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        workbook = writeSheet(workbook, title, tableData);

        String sFileName = excelTitle + ".xlsx";

        sFileName = URLEncoder.encode(sFileName, "UTF8");
        response.setHeader("Content-Disposition", "attachment; filename=".concat(sFileName));
        response.setContentType("application/msexcel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
        workbook.dispose();
    }

    /**
     * Excel报表导出通用方法
     *
     * @param out
     * @param list
     * @param title
     * @param hearders
     * @param fields
     * @throws Exception
     */
    public static void exportSXSSFExcel(OutputStream out, List<Object> list, String title,
                                        String[] hearders, String[] fields) throws Exception {
        long beginTime = System.currentTimeMillis();
        String excelTitle = title + "_" + com.cms.scaffold.common.util.DateUtil.dateStr3(DateUtil.getNow());
        TableData tableData = null;
        logger.info("数据处理");
        tableData = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        long endTime = System.currentTimeMillis();
        logger.info("拼接时间导出 : " + (endTime - beginTime));
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        workbook = writeSheet(workbook, excelTitle, tableData);
        long endTime1 = System.currentTimeMillis();
        logger.info("拼接时间1 : " + (endTime1 - endTime));
        workbook.write(out);
        out.close();
        workbook.dispose();
    }


    /**
     * 写入sheet
     *
     * @param workbook
     * @param title
     * @param tableData
     * @return
     * @throws Exception
     */
    public static SXSSFWorkbook writeSheet(SXSSFWorkbook workbook, String title, TableData tableData) throws Exception {
        long beginTime = System.currentTimeMillis();
        Sheet sheet = workbook.createSheet(title);
        Row row = null;
        Cell cell = null;

        int rowIndex = 0;
        // 标题
        Font titleFont = workbook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setFont(titleFont);
        row = sheet.createRow(rowIndex++);
        TableHeaderMetaData headerMetaData = tableData.getTableHeader();// 获得HTML的表头元素
        for (int i = 0; i < headerMetaData.getOriginColumns().size(); i++) {
            TableColumn tc = headerMetaData.getOriginColumns().get(i);
            String display = tc.getDisplay();
            cell = row.createCell(i);
            cell.setCellStyle(titleCellStyle);
            cell.setCellValue(display);
            if (display != null) {
                sheet.setColumnWidth(i, display.getBytes().length * 2 * 172);
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("标题 : " + (endTime - beginTime));

        // 数据
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        List<TableDataRow> dataRows = tableData.getRows();
        int rowSize = (dataRows == null) ? 0 : dataRows.size();
        for (int i = rowIndex; i < rowSize + rowIndex; i++) {
            TableDataRow tableDataRow = dataRows.get(i - rowIndex);
            row = sheet.createRow(i);
            List<TableDataCell> tableDataCellList = tableDataRow.getCells();
            for (int j = 0; j < tableDataCellList.size(); j++) {
                TableDataCell tableDataCell = tableDataCellList.get(j);
                cell = row.createCell(j);
                cell.setCellStyle(dataCellStyle);
                cell.setCellValue(tableDataCell.getValue());
            }
        }
        long endTime1 = System.currentTimeMillis();
        logger.info("数据 : " + (endTime1 - endTime));

        return workbook;
    }

    /**
     * 写入sheet
     *
     * @param workbook
     * @param title
     * @param tableData
     * @return
     * @throws Exception
     */
    public static SXSSFWorkbook multiWriteSheet(SXSSFWorkbook workbook, String title, TableData tableData) throws Exception {
        logger.info("开始写入writeSheet");
        long beginTime = System.currentTimeMillis();
        Sheet sheet = workbook.createSheet(title);
        Row row = null;
        Cell cell = null;

        int rowIndex = 0;
        // 标题
        Font titleFont = workbook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setFont(titleFont);
        row = sheet.createRow(rowIndex++);
        TableHeaderMetaData headerMetaData = tableData.getTableHeader();// 获得HTML的表头元素
        for (int i = 0; i < headerMetaData.getOriginColumns().size(); i++) {
            TableColumn tc = headerMetaData.getOriginColumns().get(i);
            String display = tc.getDisplay();
            cell = row.createCell(i);
            cell.setCellStyle(titleCellStyle);
            cell.setCellValue(display);
            if (display != null) {
                sheet.setColumnWidth(i, display.getBytes().length * 2 * 172);
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("标题 : " + (endTime - beginTime));


        // 数据
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        List<TableDataRow> dataRows = tableData.getRows();
        int rowSize = (dataRows == null) ? 0 : dataRows.size();
        /**
         * 使用线程池进行线程管理。
         */
        ExecutorService es = Executors.newCachedThreadPool();
        int size = rowSize % rownum == 0 ? rowSize / rownum : rowSize / rownum + 1;
        int start = 0;
        int end = 0;
        /**
         * 使用计数栅栏
         */
        System.out.println(size);
        CountDownLatch doneSignal = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            start = rownum * i;
            end = rownum * (i + 1);
            if(end >= rowSize){
                es.submit(new MultiThreadPoiWriter(doneSignal, sheet, dataRows.subList(start, rowSize), dataCellStyle, start+1, rowSize+1));
            }else{
                es.submit(new MultiThreadPoiWriter(doneSignal, sheet, dataRows.subList(start, end), dataCellStyle, start+1, end+1));
            }

        }
        doneSignal.await();
        es.shutdown();
        long endTime1 = System.currentTimeMillis();
        logger.info("数据 : " + (endTime1 - endTime));

        return workbook;
    }


    /**
     * JavaBean转Map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!StringUtils.equals(name, "class")) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public static TableData createTableData(List list, TableHeaderMetaData headMeta, String[] fields) {

        TableData td = new TableData(headMeta);
        TableDataRow row = null;
        if (list != null && list.size() > 0) {
            if (list.get(0).getClass().isArray()) {// 数组类型
                for (Object obj : list) {
                    row = new TableDataRow(td);
                    for (Object o : (Object[]) obj) {
                        row.addCell(o);
                    }
                    td.addRow(row);
                }
            } else {// JavaBean或Map类型
                for (Object obj : list) {
                    row = new TableDataRow(td);
                    Map<String, Object> map = (obj instanceof Map) ? (Map<String, Object>) obj : beanToMap(obj);
                    for (String key : fields) {
                        row.addCell(map.get(key));
                    }
                    td.addRow(row);
                }
            }
        }
        return td;
    }

}
