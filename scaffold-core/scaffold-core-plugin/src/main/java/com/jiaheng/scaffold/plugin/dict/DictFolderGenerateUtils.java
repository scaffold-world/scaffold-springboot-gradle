package com.jiaheng.scaffold.plugin.dict;

import com.jiaheng.scaffold.common.util.DateUtil;
import com.jiaheng.scaffold.common.util.ResultSetUtils;
import com.jiaheng.scaffold.plugin.crud.FreeMarkerTemplateUtils;
import com.jiaheng.scaffold.plugin.crud.PropertiesUtil;
import freemarker.template.Template;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 张嘉恒 on 2018/4/15.
 */
public class DictFolderGenerateUtils {

    private final String AUTHOR = "auto";
    private final String CURRENT_DATE = DateUtil.dateStr2(DateUtil.getNow());
    private final String tableName = PropertiesUtil.getValue("generator.tableName");
    private final String URL = PropertiesUtil.getValue("generator.connectionUrl");
    private final String USER = PropertiesUtil.getValue("generator.connectionUserId");
    private final String PASSWORD = PropertiesUtil.getValue("generator.connectionPwd");
    private final String DRIVER = PropertiesUtil.getValue("generator.driverClass");

    //private final String dictNid = PropertiesUtil.getValue("generator.dictNid");



    private final String filePath = PropertiesUtil.getValue("generator.path") +
            PropertiesUtil.getValue("generator.dictClientTargetProject") + PropertiesUtil.getValue("generator.dictPackageNameProject");

    public Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        DictFolderGenerateUtils utils = new DictFolderGenerateUtils();
        utils.generate();
    }

    public void generate() throws Exception{
        //使用Connection创建一个Statement
        Connection conn=getConnection();//获取数据库连接
        //sql执行器对象
        PreparedStatement ps=null;
        //结果集对象
        ResultSet rs=null;//查询出来的数据先放到rs中
        List<SysDict> sysDictList = new ArrayList<>();
        //String fileName = StringUtil.clearUnderlineCap(dictNid);

        try {
            String sql="select * from gjj_sys_dict where  pid = 0 and type = 1 order by sort";
            ps=conn.prepareStatement(sql);
            //ps.setString(1,dictNid);
            rs=ps.executeQuery();//执行数据库查询的方法，放到rs中
            sysDictList = ResultSetUtils.convertToList(rs,SysDict.class);

            recursion(sysDictList,conn,ps);

            generateFile("ConstantFolder.ftl","DictFloderConstant",filePath+"/",sysDictList);



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                }if(ps!=null){
                    ps.close();
                }if(conn!=null){
                    conn.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }

    private void recursion(List<SysDict> sysDictList,Connection conn,PreparedStatement ps) throws Exception{
        String sql="select * from gjj_sys_dict where  pid =? and type = 1 order by sort";
        if(sysDictList!=null && !sysDictList.isEmpty()){
            for(SysDict tempSysDict:sysDictList){
                if(tempSysDict.getType()==1){
                    ps=conn.prepareStatement(sql);
                    ps.setString(1,String.valueOf(tempSysDict.getId()));
                    ResultSet rs=ps.executeQuery();//执行数据库查询的方法，放到rs中
                    List<SysDict> childSysDictList = ResultSetUtils.convertToList(rs,SysDict.class);
                    tempSysDict.setList(childSysDictList);
                    recursion(childSysDictList,conn,ps);
                }

            }
        }
    }

    private void generateFile(String templateName,String fileName,String diskPath,List<SysDict> list) throws Exception{

        final String suffix = ".java";
        final String path = diskPath + fileName + suffix;
        File diskPathFile = new File(diskPath);


        if (!diskPathFile.exists() && !diskPathFile.isDirectory()) {
            System.out.println(diskPathFile+"//不存在");
            diskPathFile.mkdir();
        }
        File mapperFile = new File(path);

        generateFileByTemplate(templateName,fileName,mapperFile,list);

    }

    private void generateFileByTemplate(final String templateName,String fileName,File file,List<SysDict> list) throws Exception{
        HashMap dataMap = new HashMap<String,Object>();
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("fileName",fileName);
        dataMap.put("sysDictList",list);
        //dataMap.put("dictNid",dictNid);
        dataMap.put("author",AUTHOR);
        dataMap.put("date",CURRENT_DATE);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

}


