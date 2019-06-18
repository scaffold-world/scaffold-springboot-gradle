package com.cms.scaffold.common.util;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/6/18.
 */
public class CmdUtil {


    /**
     * 启动一个shell长连接，保持连接，发送多条命令，最后释放连接
     * @param commands
     * @return
     */
    public static List<String> executeNewFlow(List<String> commands){
        List<String> rspList = new ArrayList<>();
        Runtime run = Runtime.getRuntime();
        try {
            Process proc = run.exec("/bin/bash", null, null);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            for (String line : commands) {
                out.println(line);
            }
            // out.println("cd /home/test");
            // out.println("pwd");
            // out.println("rm -fr /home/proxy.log");
            out.println("exit");// 这个命令必须执行，否则in流不结束。
            String rspLine = "";
            while ((rspLine = in.readLine()) != null) {
                System.out.println(rspLine);
                rspList.add(rspLine);
            }
            proc.waitFor();
            in.close();
            out.close();
            proc.destroy();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rspList;
    }

    public static void main(String[] args) {
        //选择数据库
        String useSql = "use p2p;";
        //查询数据
        String sql = "select * from rd_user;";

        String file = "/Users/zhangjiahengpoping@gmail.com/Desktop/user.xls";

        String shellMysql = "mysql -h localhost -u root -proot -e 'use p2p;select * from rd_user' >/Users/zhangjiahengpoping@gmail.com/Desktop/user.xls;";

        String convertFile = "iconv -f utf-8 -t GBK /Users/zhangjiahengpoping@gmail.com/Desktop/user.xls > /Users/zhangjiahengpoping@gmail.com/Desktop/user1.xls;";
        String shell =  MessageFormat.format(shellMysql,file);

        List<String> commands = new ArrayList<>();
        System.out.println(shellMysql);
        commands.add(shellMysql);
        commands.add(convertFile);

        List<String> rspList = executeNewFlow(commands);
        rspList.forEach(s -> System.out.println(s));
    }
}
