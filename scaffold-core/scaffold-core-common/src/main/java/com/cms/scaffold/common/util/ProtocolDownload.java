package com.cms.scaffold.common.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class ProtocolDownload
{
    private static Logger logger = LoggerFactory.getLogger(ProtocolDownload.class);


    @Deprecated
    public Map<String, String> creatPdf(String protocolContext, String filename)
    {

        String contextPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
        String downloadFileName = filename + ".pdf";
        String realPath = contextPath + downloadFileName;
        // 创建Document文档
        Document document = new Document();
        FileOutputStream os = null;
        try
        {
            os = new FileOutputStream(realPath);
            // 获取PdfWriter实例
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();
            // 往文件中添加内容
            ByteArrayInputStream htmlIs = new ByteArrayInputStream(protocolContext.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlIs, Charset.forName("UTF-8"));
            document.close();
            Map<String, String> map = new HashMap<String, String>();
            map.put("downloadFileName", downloadFileName);
            map.put("realPath", realPath);
            return map;
        }
        catch(FileNotFoundException e)
        {
            logger.error("文件未找到");
        }
        catch(DocumentException e)
        {
            logger.info("context不存在");
        }
        catch(IOException e)
        {
            logger.error("获取PdfWriter实例失败");
        }catch (Exception e){
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != os)
                {
                    os.close();

                }
            }
            catch(IOException e2)
            {
                throw new RuntimeException("关闭流失败", e2);
            }

        }

        return null;
    }

    @Deprecated
    public void downloadFile(HttpServletResponse response, String downloadFileName, String realPath)
    {
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(realPath));
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(downloadFileName.getBytes()));
            response.addHeader("Content-Length", "" + new File(realPath).length());
            out = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            out.write(buffer);
            out.flush();
            out.close();
        }
        catch(FileNotFoundException e)
        {
            logger.error("协议pdf文件未找到！");
        }
        catch(IOException e1)
        {
            logger.error(e1.toString());

        }
        finally
        {
            try
            {
                if (null != in)
                {
                    in.close();
                }
            }
            catch(IOException e2)
            {
                throw new RuntimeException("关闭流失败", e2);
            }
            try
            {
                if (null != out)
                {
                    out.close();
                }
            }
            catch(IOException e3)
            {
                throw new RuntimeException("关闭流失败", e3);
            }

        }

    }

    /**
     * 生成二进制pdf流
     * @param protocolContext
     * @return
     */
    public byte[] createPdf(String protocolContext){
        // 创建Document文档
        Document document = new Document();
        byte[] result= null;
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//字节输出流
            // 获取PdfWriter实例
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();
            // 往文件中添加内容
            ByteArrayInputStream htmlIs = new ByteArrayInputStream(protocolContext.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlIs, Charset.forName("UTF-8"));
            document.close();
            result = baos.toByteArray();
        }
        catch(FileNotFoundException e)
        {
            logger.error("文件未找到");
        }
        catch(DocumentException e)
        {
            logger.info("context不存在");
        }
        catch(IOException e)
        {
            logger.error("获取PdfWriter实例失败");
        }catch(Exception e)
        {
           e.printStackTrace();
        }
         return  result;
    }


    public void downloadPdf(String protocolContext, HttpServletResponse response, String downloadFileName)
    {
        // 创建Document文档
        Document document = new Document();
        try
        {
            response.reset();
            downloadFileName = downloadFileName+".PDF";
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(downloadFileName.getBytes()));
            response.setContentType("application/pdf");
            OutputStream os = response.getOutputStream();
            // 获取PdfWriter实例
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();
            // 往文件中添加内容
            ByteArrayInputStream htmlIs = new ByteArrayInputStream(protocolContext.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlIs, Charset.forName("UTF-8"));
            document.close();
            os.flush();
            os.close();
        }
        catch(FileNotFoundException e)
        {
            logger.error("文件未找到");
        }
        catch(DocumentException e)
        {
            logger.info("context不存在");
        }
        catch(IOException e)
        {
            logger.error("获取PdfWriter实例失败");
        }
    }
    
    public static void main(String[] args) throws DocumentException, IOException
    {
        String downloadFileName ="C:\\generate.pdf";
        // 创建Document文档
        Document document = new Document();
        PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(downloadFileName));
        document.open();
        
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("C:\\test.html"), Charset.forName("UTF-8"));
        document.close();
    }

}
