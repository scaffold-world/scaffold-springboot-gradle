package com.cms.scaffold.common.util.excel;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2018/6/28.
 */
public class ReadExcelUtil<T> {

    private static final String NOT_NULL_VALUE_MESSAGE = "存在不能为空的字段";
    private static final String ALLOW_EXCEL_MESSAGE = "只允许上传EXCEL文件";
    private static final String NOT_NULL_EXCEL_MESSAGE = "导入的EXCEL不能为空";



    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;

    private String message;

    //构造方法
    public ReadExcelUtil(){}
    /**
     * 验证EXCEL文件
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){
            return false;
        }
        return true;
    }

    /**
     * 读EXCEL文件，获取数据集合
     * @param is
     * @param fileName 文件名称
     * @param titleNameMap 标题对应的对象字段名称
     * @param beanClass 数据对应的类
     * @return
     */
    public List<T> getExcelInfo(InputStream is, String fileName, Map<String,String> titleNameMap, Class<T> beanClass){

        // 初始化取数据的集合
        List<T> offlineDataList = new ArrayList();
        // 初始化输入流
        try{
            // 验证文件名是否合格
            if(!validateExcel(fileName)){
                setMessage(ALLOW_EXCEL_MESSAGE);
                return null;
            }
            // 根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            // 根据excel里面的内容读取订单信息
            offlineDataList = getExcelInfo(is, isExcel2003, titleNameMap, beanClass);
            is.close();
        }catch(Exception e){
            setMessage(e.getMessage());
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                }
            }
        }
        if(offlineDataList == null || offlineDataList.size() <= 0){
            setMessage(NOT_NULL_EXCEL_MESSAGE);
        }
        return offlineDataList;
    }

    /**
     * 根据excel里面的内容读取数据信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public  List<T> getExcelInfo(InputStream is, boolean isExcel2003, Map<String,String> titleNameMap, Class<T> beanClass){
        List<T> offlineDataList = null;
        try{
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            // 当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(is);
            }else{
                wb = new XSSFWorkbook(is);// 当excel是2007时
            }
            // 读取Excel里面订单的信息
            offlineDataList = readExcelValue(wb, titleNameMap, beanClass);
        }catch (Exception e)  {
            setMessage(e.getMessage());
        }
        return offlineDataList;
    }

    /**
     * 读取Excel里面数据的信息
     * @param wb
     * @return
     */
    private List<T> readExcelValue(Workbook wb, Map<String,String> titleNameMap, Class<T> beanClass){
        List<T> dataList = new ArrayList<>();
        // 得到第一个shell
        try{
            Sheet sheet = wb.getSheetAt(0);
            // 得到Excel的行数
            this.totalRows = sheet.getPhysicalNumberOfRows();

            // 得到Excel的列数(前提是有行数)
            if(totalRows >=1 && sheet.getRow(0) != null){
                this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }
            // 循环Excel行数,从第二行开始。标题不入库
            String[] titles = null;
            for(int r = 0;r < totalRows;r++){
                Row row = sheet.getRow(r);
                if (row == null) continue;
                T offlineObj = beanClass.newInstance();
                // 循环Excel的列
                if(r == 0){
                    titles = new String[this.totalCells];
                }
                int valueNotNull = 0;
                for(int c = 0; c < this.totalCells; c++){
                    Cell cell = row.getCell(c);
                    if (null != cell && !cell.equals("") && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK){
                        if(r == 0){
                            if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                                throw new IllegalArgumentException(":"+(c+1)+"列的【"+cell+"】:标题必须是字符");
                            }
                            String value = cell.getStringCellValue();
                            if(value==null||"".equals(value)){
                                throw new IllegalArgumentException(":"+(c+1)+"列的【"+value+"】:标题不能为空");
                            }
                            titles[c] = replaceBlank(value);
                        }else{
                            String title = titles[c];
                            String obj = getCellData(cell);
                            if(obj==null||"".equals(obj)){
                                break;
                            }
                            if(!titleNameMap.containsKey(title)){
                                throw new IllegalArgumentException(title+":不存在此列标题");
                            }
                            Field field  = offlineObj.getClass().getDeclaredField(titleNameMap.get(title));
                            field.setAccessible(true);
                            field.set(offlineObj, obj);
                            valueNotNull++;
                        }
                    }
                }
                //添加欺诈用户信息
                if(r != 0){
                    if(valueNotNull == 0){
                        continue;
                    }else if(valueNotNull < titles.length){
                        setMessage(NOT_NULL_VALUE_MESSAGE);
                        return dataList;
                    }else{
                        dataList.add(offlineObj);
                    }

                }
            }

        }catch (Exception e){
            setMessage(e.getMessage());
        }
        return dataList;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public String getCellData(Cell cell) {
        if(cell == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else { // 纯数字
                    return df.format(cell.getNumericCellValue());
                }
            default:
                return null;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
