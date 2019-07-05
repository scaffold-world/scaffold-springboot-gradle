package com.cms.scaffold.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cms.scaffold.common.exception.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description: http客户端
 * @Author: chenweilin
 * @Date: 2018/3/28 17:04
 **/
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 请求地址
     */
    private URL url;

    /**
     * 连接超时时间
     */
    private int connectionTimeout;

    /**
     * 读取超时时间
     */
    private int readTimeOut;

    /**
     * 响应结果
     */
    private String result;

    /**
     * 报文编码格式
     */
    public final static String CONTENT_TYPE_DEFAULT_FROM = "application/x-www-form-urlencoded";
    public final static String CONTENT_TYPE_MULTIPART_FROM = "multipart/form-data";
    public final static String CONTENT_TYPE_JSON = "application/json";
    public final static String CONTENT_TYPE_TEXT = "text/plain";

    /**
     * 默认连接超时时间
     */
    public final static int DEFAULT_CONNECTION_TIMEOUT = 30000;

    /**
     * 默认读取超时时间
     */
    public final static int DEFAULT_READ_TIMEOUT = 30000;

    /**
     * 默认编码
     */
    public final static String DEFAULT_ENCODE = "UTF-8";

    /**
     * GET请求
     */
    public final static String GET = "GET";

    /**
     * POST请求
     */
    public final static String POST = "POST";


    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public HttpClientUtil(String url) {
        try {
            this.url = new URL(url);
            this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
            this.readTimeOut = DEFAULT_READ_TIMEOUT;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public HttpClientUtil(String url, int connectionTimeout, int readTimeOut) {
        try {
            this.url = new URL(url);
            this.connectionTimeout = connectionTimeout;
            this.readTimeOut = readTimeOut;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data 发送的报文
     * @Author: chenweilin
     * @Description: 发送post请求
     * @Date: 2018/3/28 17:32
     */
    public void sendPost(String data) throws IOException {
        sendPost(data, CONTENT_TYPE_JSON);
    }

    /**
     * @param data        发送的报文
     * @param contentType 请求方式
     * @Author: chenweilin
     * @Description: 发送post请求
     * @Date: 2018/3/28 17:32
     */
    public void sendPost(String data, String contentType) throws IOException {
        sendPost(data, contentType, DEFAULT_ENCODE);
    }

    /**
     * @param data        发送的报文
     * @param contentType 请求方式
     * @param encode      编码格式
     * @Author: chenweilin
     * @Description: 发送post请求
     * @Date: 2018/3/28 17:28
     */
    public void sendPost(String data, String contentType, String encode) throws IOException {
        HttpURLConnection httpURLConnection = createConnection(POST, contentType, encode);
        //写入数据
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(data.getBytes());
        outputStream.flush();
        outputStream.close();
        //获取返回结果
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK)
            this.result = this.getReponseResult(httpURLConnection);
    }

    /**
     * @Author: chenweilin
     * @Description: 创建http连接
     * @Date: 2018/3/28 19:11
     */
    private HttpURLConnection createConnection(String method, String contentType, String encode) throws IOException {
        HttpURLConnection httpURLConnection = null;
        if (StringUtil.isEmpty(encode))
            encode = DEFAULT_ENCODE;
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.connectionTimeout);// 连接超时时间
        httpURLConnection.setReadTimeout(this.readTimeOut);// 读取结果超时时间
        httpURLConnection.setDoInput(true); // 可读 post设置的参数
        httpURLConnection.setDoOutput(true); // 可写 post设置的参数
        httpURLConnection.setUseCaches(false);// 取消缓存
        httpURLConnection.setRequestProperty("Content-type",
                contentType + "; charset=" + encode);
        httpURLConnection.setRequestMethod(method);
        return httpURLConnection;
    }

    /**
     * @Author: chenweilin
     * @Description: 获取返回结果
     * @Date: 2018/3/28 19:52
     */
    private String getReponseResult(HttpURLConnection httpURLConnection) throws IOException {
        //得到响应流
        InputStream inputStream = httpURLConnection.getInputStream();
        //将响应流转换成字符串
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = inputStream.read()) != -1) {
            baos.write(i);
        }
        String result = baos.toString(DEFAULT_ENCODE);
        return result;
    }


    public static JSONObject httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpGet.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");

                JSONObject result = JSON.parseObject(resultStr);
                if (result.getInteger("errcode") == 0) {
                    return result;
                } else {
                    int errCode = result.getInteger("errcode");
                    String errMsg = result.getString("errmsg");
                    throw new BusinessException(errMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static JSONObject httpPost(String url, Object data){
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");

        try {
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
            httpPost.setEntity(requestEntity);

            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");

                JSONObject result = JSON.parseObject(resultStr);
                return result;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }

        return null;
    }

}
