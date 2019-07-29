package com.cms.scaffold.code.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.cms.scaffold.code.config.AliyunOSSClientConfig;
import com.cms.scaffold.code.spring.SpringContextHolder;
import com.cms.scaffold.common.util.ObjectUtils;
import com.cms.scaffold.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

public class AliyunOSSClientUtil {
    //log日志
    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    private static OSSClient ossClient = SpringContextHolder.getBean(OSSClient.class);

    public static AliyunOSSClientConfig aliyunOSSClientConfig = SpringContextHolder.getBean(AliyunOSSClientConfig.class);

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(String bucketName) {
        //存储空间  
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间  
            Bucket bucket = ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param bucketName 存储空间
     */
    public static void deleteBucket(String bucketName) {
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(String bucketName, String folder) {
        //文件夹名   
        final String keySuffixWithSlash = getFolder(folder);
        //判断文件夹是否存在，不存在则创建  
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            //创建文件夹  
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            //得到文件夹名  
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"qj_nanjing/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, getFolder(folder) + key);
        logger.info("删除" + bucketName + "下的文件" + getFolder(folder) + key + "成功");
    }

    /**
     * 删除BACKET_NAME 下的文件
     *
     * @param key
     */
    public static void deleteFile(String key) {
        ossClient.deleteObject(aliyunOSSClientConfig.getBucketName(), getFolder(key));
        logger.info("删除" + aliyunOSSClientConfig.getBucketName() + "下的文件" + getFolder(key) + "成功");
    }

    /**
     * 上传图片至OSS
     *
     * @param file       上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param bucketName 存储空间
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadObject2OSS(File file, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件  
            InputStream is = new FileInputStream(file);
            //文件名  
            String fileName = file.getName();
            //文件大小  
            Long fileSize = file.length();
            //创建上传Object的Metadata    
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度  
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为  
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header  
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式  
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，  
            //如果没有扩展名则填默认值application/octet-stream  
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）  
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)  
            PutObjectResult putResult = ossClient.putObject(bucketName, getKey(fileName, folder), is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }


    /**
     * 上传图片至OSS
     *
     * @param context    文件字节
     * @param bucketName 存储空间
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadObject2OSS(byte[] context, String fileName, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = new ByteArrayInputStream(context);
            //文件大小
            Integer fileSize = context.length;
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, getKey(fileName, folder), is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 获取到追加上传对象 初始先上传一个空的byte 后面position初始使用0L
     * @param filaneme
     * @param folder
     * @return
     */
    public static AppendObjectRequest getAppendRequest(String filaneme, String folder){
        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType(getContentType(filaneme));
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(getBucketName(), getKey(filaneme, folder), new ByteArrayInputStream(new byte[]{}),meta);
        appendObjectRequest.setPosition(0L);
        ossClient.appendObject(appendObjectRequest);
        return appendObjectRequest;
    }

    /**
     * csv文件BOM头的初始化
     * 必须在文件追加数据之前通过这个获取position
     * @param appendObjectRequest
     * @return position，文件数据开始追加的位置
     */
    public static Long initCsvFileBomHeader(AppendObjectRequest appendObjectRequest){
        // csv格式的文件需要先追加BOM 头 保证可以使用UTF-8格式的编码 使用excel直接打开
        byte[] content = new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF };
        appendObjectRequest.setPosition(0L);
        appendObjectRequest.setInputStream(new ByteArrayInputStream(content));
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
        return appendObjectResult.getNextPosition();
    }

    /**
     * 追加上传内容
     * @param appendObjectRequest
     * @param position 文件上传位置（重要）
     * @param content
     * @return 每追加一次都会返回下一次的position 为文件应该追加的位置
     */
    public static Long appendFile2Oss(AppendObjectRequest appendObjectRequest,Long position,byte[] content){
        appendObjectRequest.setPosition(position);
        appendObjectRequest.setInputStream(new ByteArrayInputStream(content));
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
        return appendObjectResult.getNextPosition();
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        //文件的后缀名  
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".pdf".equalsIgnoreCase(fileExtension)) {
            return "application/pdf";
        }
        if (".xlsx".equalsIgnoreCase(fileExtension) || ".xls".equalsIgnoreCase(fileExtension)) {
            return "application/msexcel";
        }
        if (".csv".equalsIgnoreCase(fileExtension)) {
            return "application/csv";
        }
        //默认返回类型  
        return "image/jpeg";
    }

    /**
     * 根据key 获取文件字节
     *
     * @param key
     * @param folder
     * @return
     */
    public static byte[] getFileFromOSS(String folder, String key) {
        byte[] result = null;
        try {
            OSSObject ossObject = ossClient.getObject(aliyunOSSClientConfig.getBucketName(), getFolder(folder) + key);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024 * 1024]; //1M 缓存
            InputStream in = ossObject.getObjectContent();
            for (int n = 0; (n = in.read(buf, 0, buf.length)) != -1; ) {
                swapStream.write(buf, 0, n);
            }
            in.close();
            result = swapStream.toByteArray();
            swapStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取文件异常:" + folder + key, e);
        }

        return result;
    }

    /**
     * 根据key 获取文件字节
     *
     * @param key 整体路径提取
     * @return
     */
    public static byte[] getFileFromOSS(String key) {
        byte[] result = null;
        try {
            OSSObject ossObject = ossClient.getObject(aliyunOSSClientConfig.getBucketName(), getFolder(key));
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024 * 1024]; //1M 缓存
            InputStream in = ossObject.getObjectContent();
            for (int n = 0; (n = in.read(buf, 0, buf.length)) != -1; ) {
                swapStream.write(buf, 0, n);
            }
            in.close();
            result = swapStream.toByteArray();
            swapStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取文件异常:" + key, e);
        }

        return result;
    }


    /**
     * 复制文件，复制BACKET_NAME下的文件
     *
     * @param sourceKey
     * @param targetKey
     * @return
     */
    public static String copyObject(String sourceKey, String targetKey) {
        String newTag = "";
        try {
            CopyObjectResult result = ossClient.copyObject(aliyunOSSClientConfig.getBucketName(), getFolder(sourceKey), aliyunOSSClientConfig.getBucketName(), getFolder(targetKey));
            newTag = result.getETag();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("阿里云OSS服务器复制异常." + e.getMessage(), e);
        }
        return newTag;
    }

    /**
     * @param filename 带后缀文件名
     * @param folder 除环境文件夹的路径 如 csv/20180720 注意最后有/
     * @return
     */
    public static String getKey(String filename, String folder){
        return getFolder(folder) + filename;
    }



    /**
     * 获得url链接
     *
     * @param filename
     * @return
     */
    public static String getUrl(String filename, String folder) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL

        URL url = ossClient.generatePresignedUrl(aliyunOSSClientConfig.getBucketName(), getKey(filename, folder), expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 根据url地址将文件复制到新地址,返回新的url,并删除原先的文件
     * 注:移动BACKET_NAME 下的文件
     *
     * @param url
     * @param key
     * @param targetFolder
     * @return
     */
    public static String moveFileByUrl(String url, String key, String targetFolder) {
        if (StringUtil.isBlank(url)) {
            return url;
        }
        String newUrl = url;
        String sourceKey = getKeyByUrl(url);
        //若sourceKey 存在targetFolder中则不修改，否则修改
        if (!sourceKey.startsWith(targetFolder)) {
            String targetKey = key + (sourceKey.lastIndexOf(".") > -1 ? sourceKey.substring(sourceKey.lastIndexOf(".")) : "");
            String result = copyObject(sourceKey, targetFolder + targetKey);
            if (StringUtil.isNotBlank(result)) {
                //获取新地址
                String newAddress = getUrl(targetKey, targetFolder);
                if (newAddress != null) {
                    newUrl = newAddress;
                    //删除临时文件
                    deleteFile(sourceKey);
                }
            }
        }
        return newUrl;
    }

    /**
     * 根据url解析key
     *
     * @param url
     * @return
     */
    public static String getKeyByUrl(String url) {
        String str = url.substring(0, url.indexOf("?") > -1 ? url.indexOf("?") : url.length());
        str = str.substring(str.indexOf("/" + aliyunOSSClientConfig.getEnv() + "/")).replace("/" + aliyunOSSClientConfig.getEnv() + "/", "");
        try {
            // 连接中可能出现中文 需要解码后才是正确的 key
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取文件夹目录
     */
    public static String getFolder(String folder) {
        return aliyunOSSClientConfig.getEnv() + "/" + folder;
    }

    /**
     * 返回backetName
     *
     * @return
     */
    public static String getBucketName() {
        return aliyunOSSClientConfig.getBucketName();
    }

}
