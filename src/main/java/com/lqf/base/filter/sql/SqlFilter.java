package com.lqf.base.filter.sql;


import com.superchiken.order.config.Constant;
import com.superchiken.order.filter.BaseFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  16:42
 * @description 防sql注入过滤器类
 */
public class SqlFilter extends BaseFilter {

    /**
     * SQL过滤开关
     */
    public boolean enabled = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initExcludesUrl = filterConfig.getInitParameter(Constant.SQL_FILTER_EXCLUDE_URL);
        String initEnable = filterConfig.getInitParameter(Constant.SQL_FILTER_ENABLE);

        // 设置排除链接
        if(StringUtils.isNotEmpty(initExcludesUrl)){
            String[] url = initExcludesUrl.split("\\|");
            for (int i = 0; url != null && i < url.length; i++)
            {
                excludeUrls.add(url[i]);
            }
        }
        // 设置过滤器生效标志
        if (StringUtils.isNotEmpty(initEnable))
        {
            enabled = Boolean.valueOf(initEnable);
        }
    }

    /**
     * 过滤处理
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handleExcludeURL(req, resp)){
            chain.doFilter(request, response);
            return;
        }
        // sql注入过滤处理
        SqlHttpServletRequestWrapper sqlRequest = new SqlHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(sqlRequest, response);
    }

    @Override
    public void destroy() {

    }
}
