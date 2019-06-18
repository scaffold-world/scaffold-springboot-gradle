package com.cms.scaffold.plugin.i18n;

import com.cms.scaffold.common.util.ResultSetUtils;
import com.cms.scaffold.plugin.crud.FreeMarkerTemplateUtils;
import com.cms.scaffold.plugin.crud.PropertiesUtil;
import freemarker.template.Template;
import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/4/15.
 */
public class I18nUtils {

    private final String URL = PropertiesUtil.getValue("generator.connectionUrl");
    private final String USER = PropertiesUtil.getValue("generator.connectionUserId");
    private final String PASSWORD = PropertiesUtil.getValue("generator.connectionPwd");
    private final String DRIVER = PropertiesUtil.getValue("generator.driverClass");




    private final String filePath = PropertiesUtil.getValue("generator.path") +
            PropertiesUtil.getValue("generator.messageClientTargetProject") + PropertiesUtil.getValue("generator.messagePackageNameProject");

    public Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        I18nUtils utils = new I18nUtils();
        utils.generate();
    }

    public void generate() throws Exception{
        //使用Connection创建一个Statement
        Connection conn=getConnection();//获取数据库连接
        //sql执行器对象
        PreparedStatement ps=null;
        //结果集对象
        ResultSet rs=null;//查询出来的数据先放到rs中
        List<I18n> list = new ArrayList<>();
        List<Message> defaultList = new ArrayList<>();
        List<Message> zhList = new ArrayList<>();
        List<Message> enList = new ArrayList<>();
        List<Message> idList = new ArrayList<>();
        try {
            String sql="select * from sys_i18n";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();//执行数据库查询的方法，放到rs中
            list = ResultSetUtils.convertToList(rs,I18n.class);
            if(CollectionUtils.isEmpty(list)){
                return;
            }

            for(I18n i18n:list){
                Message defaultMessage = new Message();
                Message zhMessage = new Message();
                Message enMessage = new Message();
                Message idMessage = new Message();

                defaultMessage.setName(i18n.getName());
                defaultMessage.setValue(i18n.getText());
                defaultList.add(defaultMessage);

                zhMessage.setName(i18n.getName());
                zhMessage.setValue(i18n.getZhCH());
                zhList.add(zhMessage);

                enMessage.setName(i18n.getName());
                enMessage.setValue(i18n.getEnUS());
                enList.add(enMessage);

                idMessage.setName(i18n.getName());
                idMessage.setValue(i18n.getIdID());
                idList.add(enMessage);

                generateFile(i18n.getModel(),"Message.ftl","messages",filePath,defaultList);
                generateFile(i18n.getModel(),"Message.ftl","messages_zh_CN",filePath,zhList);
                generateFile(i18n.getModel(),"Message.ftl","messages_en_US",filePath,enList);
                generateFile(i18n.getModel(),"Message.ftl","messages_id_ID",filePath,idList);

            }

            //recursion(sysDictList,conn,ps);

            //generateFile("Constant.ftl",fileName+"Constant",filePath+"/",sysDictList);



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


    private void generateFile(String model,String templateName,String fileName,String diskPath,List<Message> list) throws Exception{

        final String suffix = ".properties";
        final String path = diskPath + fileName + suffix;
        File diskPathFile = new File(diskPath);


        if (!diskPathFile.exists() && !diskPathFile.isDirectory()) {
            System.out.println(diskPathFile+"//不存在");
            diskPathFile.mkdir();
        }
        File mapperFile = new File(path);

        generateFileByTemplate(templateName,mapperFile,list);
    }

    private void generateFileByTemplate(final String templateName,File file,List<Message> list) throws Exception{
        HashMap dataMap = new HashMap<String,Object>();
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("messages",list);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }


}


