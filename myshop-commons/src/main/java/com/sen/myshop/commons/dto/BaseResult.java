package com.sen.myshop.commons.dto;

import java.io.Serializable;

/**
 * 自定义返回结果
 * <p>Title: BaseResult</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/6/19 14:31
 */
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAILED = 500;

    private int status;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResult success() {
        return createCommonResult(STATUS_SUCCESS, "操作成功",null);
    }
    public static BaseResult success(String message) {
        return createCommonResult(STATUS_SUCCESS, message,null);
    }
    public static BaseResult success(String message,Object data) {
        return createCommonResult(STATUS_SUCCESS, message,data);
    }

    public static BaseResult failed() {
        return createCommonResult(STATUS_FAILED, "操作失败",null);
    }

    public static BaseResult failed(String message,Object data) {
        return createCommonResult(STATUS_FAILED, "message",data);
    }

    public static BaseResult failed(String message) {
        return createCommonResult(STATUS_FAILED, message,null);
    }

    public BaseResult() {
    }

    public BaseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 重构创建BaseResult的方法
     * @param status
     * @param message
     * @return
     */
    public static BaseResult createCommonResult(int status, String message,Object data) {
        BaseResult baseResult = new BaseResult(status, message,data);
        return baseResult;
    }
}
