package com.lqf.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\6\2 0002  15:51
 * @description 通用web返回类
 */
public class RequestResult {
    private static final Logger logger = LoggerFactory.getLogger(RequestResult.class);

    //
    private String status;

    //
    private Object data;

    public static RequestResult create(Object result){
        return RequestResult.create(Constant.REQUEST_SUCCESS, result);
    }

    private static RequestResult create(String status, Object result) {
        RequestResult requestResult = new RequestResult();
        requestResult.setStatus(status);
        requestResult.setData(result);
        return requestResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
