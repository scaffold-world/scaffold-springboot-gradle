package com.cms.scaffold.route.operate.response.sys;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadResponse {
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 提示标题
     */
    private String title;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回路径
     */
    private String filePath;

    /**
     * 成功返回地址
     * @param filePath
     */
    public UploadResponse(String filePath){
        this(200, "上传成功", filePath);
    }

    /**
     * 失败返回
     * @param statusCode
     * @param message
     */
    public UploadResponse(int statusCode, String message){
        this(200, "上传成功", "");
    }

    /**
     * 构造
     * @param statusCode
     * @param message
     * @param filePath
     */
    public UploadResponse(int statusCode, String message, String filePath){
        this.statusCode = statusCode;
        this.message = message;
        this.filePath = filePath;
        this.title = "操作提示";
    }
}
