package com.jiaheng.scaffold.route.operate.controller.sys;

import com.jiaheng.scaffold.core.util.AliyunOSSClientUtil;
import com.jiaheng.scaffold.route.operate.controller.BaseController;
import com.jiaheng.scaffold.route.operate.response.sys.UploadResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 文件上传
 * @Date 2018/11/1 19:49
 */
@Controller
@RequestMapping("/sys/upload/")
public class SysUploadController extends BaseController {

    private static final String OSS_FOLDER ="temp/";

    @RequestMapping("fileUpload")
    @ResponseBody
    public UploadResponse normalUpload(@RequestParam MultipartFile file) {
        UploadResponse model =null;
        try {
            String address = uploadFile(file);
            model = new UploadResponse(address);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件上传失败！");
            model = new UploadResponse(500, "上传失败,联系管理员");
        }

        return model;
    }

    @RequestMapping("ueditorUpload")
    @ResponseBody
    public Map<String, Object> ueditorUpload(@RequestParam MultipartFile upfile) {
        Map<String, Object> ret = new HashMap<>();
        String fileName = upfile.getOriginalFilename();
        try {
            String address = uploadFile(upfile);
            logger.info("ue编辑器上传文件地址：" + address);
            ret.put("state", "SUCCESS");
            ret.put("url", address);
            ret.put("size", upfile.getSize());
            ret.put("title", fileName);
            ret.put("original", fileName);
            ret.put("type", upfile.getContentType());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件上传失败！");
            ret.put("state", "ERROR");
            ret.put("url", "");
            ret.put("title", fileName);
            ret.put("original", fileName);
            ret.put("size", upfile.getSize());
            ret.put("type", upfile.getContentType());
        }

        return ret;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        fileName = System.currentTimeMillis()+"_"+fileName;
        //获取item中的上传文件的输入流
        InputStream in = file.getInputStream();
        //创建一个文件输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //创建一个缓冲区
        byte buffer[] = new byte[1024];
        //判断输入流中的数据是否已经读完的标识
        int len = 0;
        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
        while ((len = in.read(buffer)) > 0) {
            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
            out.write(buffer, 0, len);
        }
        //关闭输入流
        in.close();
        //关闭输出流
        out.close();
        //删除处理文件上传时生成的临时文件
        AliyunOSSClientUtil.uploadObject2OSS(out.toByteArray(), fileName, AliyunOSSClientUtil.getBucketName(), OSS_FOLDER);
        String address = AliyunOSSClientUtil.getUrl(fileName, OSS_FOLDER);
        logger.info("文件上传成功！");
        return address;
    }


}
