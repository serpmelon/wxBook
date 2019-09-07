package com.togo.wx.common.entity;

import javax.management.relation.RelationSupport;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年08月18日 09:38
 * @since 1.0
 */
public class Result {

    private String msg = "success";
    private int code = 1;
    private boolean success = true;
    private Object data;

    public static Result getSuccessResultWithDataAndMessage(Object data, String message) {

        Result result = new Result();
        result.data = data;
        result.msg = message;
        return result;
    }

    public static Result getSuccessResultWithData(Object data) {

        return getSuccessResultWithDataAndMessage(data, "success");
    }

    public static Result getErrorResultWithMessage(String message) {

        return new Result(-1, false, message);
    }

    public Result() {
    }

    public Result(int code, boolean success, String msg) {

        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}