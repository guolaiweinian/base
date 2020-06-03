package com.lqf.base.error;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\26 0026  15:02
 * @description 通用错误类接口
 */
public interface CommonError {

    // 获取错误号
    public int getErrorNo();

    // 获取错误信息
    public String getErrorMsg();

    // 设置错误信心并返回错误类
    public CommonError setErrorMsg(String errorMsg);
}
