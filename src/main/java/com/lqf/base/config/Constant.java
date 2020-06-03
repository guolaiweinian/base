package com.lqf.base.config;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\6\2 0002  15:54
 * @description 全局配置
 */
public class Constant {

    /* 返回状态 */
    // 返回成功状态
    public static String REQUEST_SUCCESS = "success";
    // 返回失败状态
    public static String REQUEST_FAIL = "fail";

    /* filter 全局配置*/
    // XSS过滤器启动名
    public static final String XSS_FILTER_ENABLE="xssEnable";
    // XSS过滤器排除链接名
    public static final String XSS_FILTER_EXCLUDE_URL="xssExcludeUrl";
    // 自定义式XSS清除
    public static final int XSS_FILTER_CLEAN= 0;
    // JSOUP式XSS清除
    public static final int XSS_FILTER_CLEAN_JSOUP= 1;
    // sql过滤器启动名
    public static final String SQL_FILTER_ENABLE="sqlEnable";
    // sql过滤器排除链接名
    public static final String SQL_FILTER_EXCLUDE_URL="sqlExcludeUrl";
}
