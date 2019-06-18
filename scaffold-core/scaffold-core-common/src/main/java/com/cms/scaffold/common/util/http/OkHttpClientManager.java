package com.cms.scaffold.common.util.http;

import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;

/**
 * Created by jinkia on 2017/7/12.
 */
public class OkHttpClientManager {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientManager.class);
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;
    //请求体
    private Request request;
    //超时时间
    public static final int TIMEOUT = 1000 * 60;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClientManager() {
        mOkHttpClient = new OkHttpClient();
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }

    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }
/** ---------------------------------------------------------------------------------------------**/
    /**
     * 创建json格式提交
     *
     * @param url
     * @param jsonStr
     * @return
     */
    public OkHttpClientManager createJsonRequest(String url, String jsonStr) {
        RequestBody body = RequestBody.create(JSON, jsonStr);
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return this;
    }

    /**
     * 创建键值对提交
     * @param url
     * @param params
     * @return
     */
    public OkHttpClientManager createMapRequest(String url, Map<String, Object> params) {
        RequestBody body = buildMap(params);
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return this;
    }
/** ---------------------------------------------------------------------------------------------**/
    /**
     * 同步获取返回字符串
     *
     * @return
     */
    public String getResultStr() {
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "";
        }
    }

    /**
     * 异步获取请求结果
     *
     * @param resultCallback
     */
    public void getAsycResult(final ResultCallback resultCallback) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                resultCallback.onError(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                resultCallback.onResponse(response);
            }
        });
    }

    /**
     * ---------------------------------------------------------------------------------------------
     **/

    /**
     * 构造map参数
     *
     * @param map
     * @return
     */
    private RequestBody buildMap(Map<String, Object> map) {
        FormEncodingBuilder formBodyBuild = new FormEncodingBuilder();

        //遍历map
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                formBodyBuild.add(entry.getKey(), entry.getValue().toString());
            }
        }
        return formBodyBuild.build();
    }

    public static abstract class ResultCallback {

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(Response response);
    }
}
