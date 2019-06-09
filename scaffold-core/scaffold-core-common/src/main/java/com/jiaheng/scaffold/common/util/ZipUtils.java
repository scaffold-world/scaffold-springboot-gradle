package com.jiaheng.scaffold.common.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by jinkia on 2017/12/22.
 */
public class ZipUtils {
    private ZipUtils() {
    }

    public static void doCompress(String srcFile, String zipFile) {
        try {
            doCompress(new File(srcFile), new File(zipFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件压缩
     *
     * @param srcFile 目录或者单个文件
     * @param zipFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            doCompress(srcFile, out);
        } catch (Exception e) {
            throw e;
        } finally {
            out.close();//记得关闭资源
        }
    }

    public static void doCompress(String filelName, ZipOutputStream out) throws IOException {
        doCompress(new File(filelName), out);
    }

    public static void doCompress(File file, ZipOutputStream out) throws IOException {
        doCompress(file, out, "");
    }

    public static void doCompress(File inFile, ZipOutputStream out, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = inFile.getName();
                    if (!"".equals(dir)) {
                        name = dir + "/" + name;
                    }
                    ZipUtils.doCompress(file, out, name);
                }
            }
        } else {
            ZipUtils.doZip(inFile, out, dir);
        }
    }

    public static void doZip(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);

        int len = 0;
        byte[] buffer = new byte[1024*1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }

    /**
     * 压缩方法，将文件字节传入进行文件压缩输出
     * @param data
     * @param zip
     * @param fileName
     */
    public static void doCompress(byte[] data,ZipOutputStream zip,String fileName) {

        try {

           // ByteArrayOutputStream bos = new ByteArrayOutputStream();

            //ZipOutputStream zip = new ZipOutputStream(bos);

            ZipEntry entry = new ZipEntry(fileName);

            entry.setSize(data.length);//返回条目数据的未压缩大小；如果未知，则返回 -1。

            zip.putNextEntry(entry);// 开始写入新的 ZIP 文件条目并将流定位到条目数据的开始处

            zip.write(data);//将字节数组写入当前 ZIP 条目数据。

            zip.closeEntry();



        } catch (Exception ex) {

            ex.printStackTrace();

        }


    }

    public static void zipOpen(String inUrl, String outUrl)
    {
        File Fout=null;
        ZipEntry entry;
        long startTime=System.currentTimeMillis();
        try {
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  inUrl));//输入源zip路径
            BufferedInputStream Bin=new BufferedInputStream(Zin);
            String Parent=outUrl; //输出路径（文件夹目录）
            try {
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                    Fout=new File(Parent,entry.getName());
                    if(!Fout.exists()){
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out=new FileOutputStream(Fout);
                    BufferedOutputStream Bout=new BufferedOutputStream(out);
                    int b;
                    while((b=Bin.read())!=-1){
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    System.out.println(Fout+"解压成功");
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("耗费时间： "+(endTime-startTime)+" ms");
    }

    public static void main(String[] args) throws IOException {
        doCompress("D:\\downloadfile\\3\\20171221", "D:\\downloadfile\\3\\20171221.zip");
    }
}
