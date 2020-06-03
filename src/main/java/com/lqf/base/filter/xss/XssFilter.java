package com.lqf.base.filter.xss;

import com.superchiken.order.config.Constant;
import com.superchiken.order.filter.BaseFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  10:36
 * @description 防XSS攻击过滤器类
 */
public class XssFilter extends BaseFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initExcludesUrl = filterConfig.getInitParameter(Constant.XSS_FILTER_EXCLUDE_URL);
        String initEnable = filterConfig.getInitParameter(Constant.XSS_FILTER_ENABLE);

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

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handleExcludeURL(req, resp)){
            chain.doFilter(request, response);
            return;
        }
        // XSS过滤处理
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {

    }
}
