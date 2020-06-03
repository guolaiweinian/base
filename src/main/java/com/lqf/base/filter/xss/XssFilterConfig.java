package com.lqf.base.filter.xss;

import com.superchiken.order.config.Constant;
import com.superchiken.order.config.MapResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;


/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  11:21
 * @description XSS攻击过滤器初始化注册类
 */
@Configuration
public class XssFilterConfig {

    /**
     * 过滤开关
     */
    @Value("${xss.enabled:false}")
    private String xssEnabled;

    /**
     * 过滤排除链接
     */
    @Value("${xss.excludeUrl}")
    private String xssExcludeUrl;

    /**
     * 过滤链接
     */
    @Value("${xss.url:''}")
    private String url;

    @Bean
    public FilterRegistrationBean xssFilterRegistration(){
        System.out.println("启动xss过滤");
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setDispatcherTypes(DispatcherType.REQUEST);
        // 注册自定义过滤器
        registration.setFilter(new XssFilter());
        // 过滤器名称
        registration.setName("xssFilter");
        //设置过滤路径
        registration.addUrlPatterns(url.split("|"));
        // 设置过滤优先级(数字越低，越优先)
        registration.setOrder(1);
        // 初始化参数
        MapResult initParam = new MapResult();
        initParam.set(Constant.XSS_FILTER_EXCLUDE_URL, xssExcludeUrl);
        initParam.set(Constant.XSS_FILTER_ENABLE, xssEnabled);
        registration.setInitParameters(initParam);
        return registration;
    }
}
