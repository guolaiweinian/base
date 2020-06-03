package com.lqf.base.error;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\26 0026  14:40
 * @description 错误提示枚举类
 */
public enum ErrorEnum implements CommonError {
    // 未知异常，用于非自定义异常抛出
    UNKNOWN_ERROR(-1, "未知异常"),

    // 校验错误
    PARAMETER_VALIDATION_ERROR(-10000, "入参异常"),
    PARAMETER_NONE_ERROR(-10001,"入参不能为空！"),
    ;

    private int errorNo;
    private String errorMsg;

    private ErrorEnum(int errorNo, String errorMsg){
        this.errorNo = errorNo;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorNo(){
        return this.errorNo;
    }

    @Override
    public String getErrorMsg(){
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
