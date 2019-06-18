package com.cms.scaffold.route.app.interceptor;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParamInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ParamInterceptor.class);

    private static final String DENIED_TEXTS = "\\b(AND|OR)\\b.+?(>|<|=|\\bIN\\b|\\bLIKE\\b)|\\/\\*.+?\\*\\/|<\\s*SCRIPT\\b|\\bEXEC\\b|UNION.+?SELECT|UPDATE.+?SET|INSERT\\s+INTO.+?VALUES|(SELECT|DELETE).+?FROM|(CREATE|ALTER|DROP|TRUNCATE)\\s+(TABLE|DATABASE)|\\bON.+\\b\\s*=|\\bJAVASCRIPT\\s*:\\b|DECLARE|RENAME|JOIN|WHERE|LIKE|CAST|EVAL|<\\s*SCRIPT\\b|GRANT|UNICODE|CONFIRM|[0-9]AND[0-9]|[0-9]OR[0-9]|[0-9]+[0-9]TRIM|EXIST|BUTTON|[';<>\"&$%\\*]|</|[|]|\\\'|\\\"|<\\s>|()";
    private static final String URL_TEXTS = "SCRIPT|ALERT|BUTTON|TRIM|IFRAME|DECLARE|CONFIRM|HREF|LINK|INJECTED|WINDOW|LOCATION|EVAL|FUNCTION";

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    private static List<String> paramsName = new ArrayList<String>();

    static {
        paramsName.add("redirectURL");
        paramsName.add("content");
        paramsName.add("token");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        this.request = request;
        this.response = response;
        if (handler instanceof HandlerMethod) {
            Enumeration names = request.getParameterNames();
            String url = request.getRequestURI();
            // 默认不包含有  
            String hasDeniedText = null;
            // 默认不包含有
            boolean hasDeniedXss = false;
            String illegalParam = "";
            String checkText = DENIED_TEXTS;
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                //参数值为url地址时，请在paramsName中加入参数名称
                if (paramsName.contains(name)) {
                    checkText = URL_TEXTS;
                }
                String[] values = request.getParameterValues(name);
                for (int i = 0; i < values.length; i++) {
                    // 先对请求进行转码，然后转成大写
                    illegalParam = values[i];
                    String needCheckParams = values[i].replace("+", "%2B");//前台传入的+特殊处理，解决+不能过滤的问题
                    needCheckParams = urlDecoder(needCheckParams).toUpperCase();

                    // input框过滤 包含富文本编辑器/模版编辑等
                    if (inputFilter(name, url)) {
                        hasDeniedXss = isAttack(needCheckParams);
                    } else {
                        hasDeniedXss = isAttack(name);
                        hasDeniedText = isDeniedText(needCheckParams, checkText);
                    }
                    // 文本校验
                    if (StringUtils.isNotBlank(hasDeniedText)) {
                        break;
                    }
                    // XSS校验
                    if (hasDeniedXss) {
                        break;
                    }
                }
                // 文本校验
                if (StringUtils.isNotBlank(hasDeniedText)) {
                    break;
                }
                // XSS校验
                if (hasDeniedXss) {
                    break;
                }
                checkText = DENIED_TEXTS;
            }
            // 文本过滤
            if (StringUtils.isNotBlank(hasDeniedText)) {
                logger.info("存在非法字符:  " + hasDeniedText + "");
                responseJson("存在非法字符!", false);
                return false;
            }
            // xss过滤
            if (hasDeniedXss) {
                logger.info("存在非法字符: " + illegalParam);
                //super.service("存在非法字符: " + illegalParam);
                responseJson("存在非法字符!", false);
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    /**
     * 过滤input框
     */
    public boolean inputFilter(String name, String path) {
        //富文本编辑器
        if (name.equals("content")) {
            return true;
        }

        return false;
    }

    /**
     * 提取参数
     *
     * @param safety 是否对密码过滤
     * @return 参数信息
     */
    protected String getAllParams(boolean safety) {
        StringBuffer ps = new StringBuffer();
        Enumeration<?> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameter = (String) parameterNames.nextElement();
            String value = request.getParameter(parameter);
            if (StringUtils.isNotBlank(value)) {
                if (!safety || (safety && !parameter.contains("password") && !parameter.contains("pwd"))) { // 安全性
                    ps.append(parameter + "=" + value);
                    if (parameterNames.hasMoreElements()) {
                        ps.append("&");
                    }
                }
            }
        }
        return ps.toString();
    }

    public void responseJson(String msg, boolean result) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }

    /**
     * 字符串解码
     */
    public static String urlDecoder(String sStr) {
        String sReturnCode = sStr;
        try {
            sReturnCode = URLDecoder.decode(sStr, "utf-8");
        } catch (Exception e) {
        }
        return sReturnCode;
    }

    /**
     * 返回true就是包含非法字符，返回false就是不包含非法字符 系统内容过滤规则 1、包含  『 and 1 特殊字符 』， 特殊字符指>,<,=, in , like 字符 2、『 /特殊字符/ 』，特殊字符指 *字符
     * 3、『<特殊字符 script 』特殊字符指空字符 4、『 EXEC 』 5、『 UNION SELECT』 5、『 UPDATE SET』 5、『 INSERT INTO VALUES』 5、『 SELECT或DELETE
     * FROM』 5、『CREATE或ALTER或DROP或TRUNCATE TABLE或DATABASE』
     */
    public static boolean isAttack(String input) {
        String getfilter = "\\b(AND|OR)\\b.+?(>|<|=|\\bIN\\b|\\bLIKE\\b)|\\/\\*.+?\\*\\/|<\\s*SCRIPT\\b|\\bEXEC\\b|UNION.+?SELECT|UPDATE.+?SET|INSERT\\s+INTO.+?VALUES|(SELECT|DELETE).+?FROM|(CREATE|ALTER|DROP|TRUNCATE)\\s+(TABLE|DATABASE)|\\bON.+\\b\\s*=|\\bJAVASCRIPT\\s*:\\b";
        Pattern pat = Pattern.compile(getfilter);
        Matcher mat = pat.matcher(input);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * 通过数组 过滤
     */
    public String isDeniedText(String text, String checkText) {
        StringBuffer strBuffer = new StringBuffer("");
        Pattern pat = Pattern.compile(checkText);
        Matcher mat = pat.matcher(text);
        while (mat.find()) {
            if (!StringUtils.isBlank(mat.group())) {
                strBuffer.append("  '" + mat.group() + "'   ");
            }
        }
        return strBuffer.toString();
    }
}
