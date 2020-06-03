package com.lqf.base.filter.xss;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  15:33
 * @description 入参Xss攻击清除
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    @Value("${xss.type:0}")
    int type;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 过去单个入参，进行
     * @param parameter
     * @return
     */
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return clean(value, type);
    }

    /**
     * 获取入参数组，入参数组进行Xss清除
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        String [] values = super.getParameterValues(name);
        if(values != null){
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++)
            {
                // 防xss攻击和过滤前后空格
                escapseValues[i] = clean(values[i], type);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }

    /**
     *
     * @return
     */
    @Override
    public Map<String, String[]> getParameterMap(){
        Map<String, String[]> values=super.getParameterMap();
        if (values == null) {
            return null;
        }
        Map<String, String[]> result = new HashMap<>();
        for(String key:values.keySet()){
            String encodedKey = clean(key, type);
            int count=values.get(key).length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++){
                encodedValues[i]=clean(values.get(key)[i], type);
            }
            result.put(encodedKey,encodedValues);
        }
        return result;
    }

    /**
     * 清除header的XSS
     * @param name
     * @return
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return clean(value, type);
    }

    /**
     * 清除Xss
     * @param param
     * @return
     */
    private String clean(String param, int type) {
        switch (type) {
            case 0:
                return cleanXss(param);
            case 1:
                return cleanXssByJsoup(param);
            default:
                return cleanXss(param);
        }
    }

    /**
     * 自定义清除Xss
     * @param param
     */
    private String cleanXss(String param) {
        String value = param.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                .replaceAll("<", "& lt;").replaceAll(">", "& gt;")
                .replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;")
                .replaceAll("'", "& #39;").replaceAll("eval\\((.*)\\)", "")
                .replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"")
                .replaceAll("script", "");
        return value;
    }

    /**
     * 使用Jsoup清除Xss
     * @param param
     */
    private String cleanXssByJsoup(String param) {
        String value = Jsoup.clean(param,Whitelist.relaxed()).trim();
        return value;
    }

}
