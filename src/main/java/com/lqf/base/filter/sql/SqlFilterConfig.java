package com.lqf.base.filter.sql;

import com.superchiken.order.config.Constant;
import com.superchiken.order.config.MapResult;
import com.superchiken.order.filter.xss.XssFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.servlet.DispatcherType;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  16:40
 * @description 防sql注入过滤器初始化注册类
 */
public class SqlFilterConfig {

    /**
     * 过滤开关
     */
    @Value("${sql.enabled}")
    private String sqlEnabled;

    /**
     * 过滤排除链接
     */
    @Value("${sql.excludeUrl}")
    private String sqlExcludeUrl;

    /**
     * 过滤链接
     */
    @Value("${sql.url}")
    private String url;

    @Bean
    public FilterRegistrationBean sqlFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setDispatcherTypes(DispatcherType.REQUEST);
        // 注册自定义过滤器
        registration.setFilter(new XssFilter());
        // 过滤器名称
        registration.setName("sqlFilter");
        //设置过滤路径
        registration.addUrlPatterns(StringUtils.split(url, "\\|"));
        // 设置过滤优先级(数字越低，越优先)
        registration.setOrder(1);
        // 初始化参数
        MapResult initParam = new MapResult();
        initParam.set(Constant.SQL_FILTER_EXCLUDE_URL, sqlExcludeUrl);
        initParam.set(Constant.SQL_FILTER_ENABLE, sqlEnabled);
        registration.setInitParameters(initParam);
        return registration;
    }
}
