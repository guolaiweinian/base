package com.lqf.base.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  16:44
 * @description
 */
public abstract class BaseFilter implements Filter {
    /**
     * 过滤排除链接
     */
    public List<String> excludeUrls = new ArrayList<>();

    /**
     * xss过滤开关
     */
    public boolean enabled = false;

    /**
     * 拦截处理方法
     * @param request
     * @param response
     * @return
     */
    public boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response){
        // 不启用直接退出
        if (!enabled){
            return true;
        }
        // 过滤地址为空则不处理
        if (excludeUrls == null || excludeUrls.isEmpty()){
            return false;
        }
        // 获取请求链接
        String url = request.getServletPath();
        // 匹配不需要过滤地址
        for (String pattern : excludeUrls){
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

}
