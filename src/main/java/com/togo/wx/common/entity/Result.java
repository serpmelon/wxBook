package com.togo.wx.common.entity;

import com.togo.wx.common.constant.ResultCode;
import org.apache.commons.lang3.StringUtils;

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

    private String message = ResultCode.SUCCESS.getMessage();
    private int code = ResultCode.SUCCESS.getIndex();
    private boolean success = true;
    private Object data;

    public static Result getSuccessResultWithDataAndMessage(Object data, String message) {

        Result result = new Result();
        result.data = data;
        result.message = message;
        return result;
    }

    public static Result getSuccessResultWithData(Object data) {

        return getSuccessResultWithDataAndMessage(data, ResultCode.SUCCESS.getMessage());
    }

    public static Result getErrorResultWithMessage(String message) {

        return new Result(ResultCode.ERROR_DEFAULT, false, message);
    }

    public static Result getErrorResultWithMessage(ResultCode resultCode) {

        return new Result(ResultCode.ERROR_DEFAULT, false, resultCode.getMessage());
    }

    public static Result getErrorResultWithMessage(String message, ResultCode resultCode) {

        return new Result(resultCode, false, message);
    }

    public Result() {
    }

    public Result(ResultCode resultCode, boolean success, String message) {

        this(resultCode.getIndex(), success, StringUtils.isBlank(message) ? resultCode.getMessage() : message);
    }

    public Result(int code, boolean success, String message) {

        this.code = code;
        this.success = success;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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