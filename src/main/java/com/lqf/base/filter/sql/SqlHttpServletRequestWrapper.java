package com.lqf.base.filter.sql;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\23 0023  16:55
 * @description 入参防sql注入
 */
public class SqlHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public SqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    // 还需写具体实现
}
