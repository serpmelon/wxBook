package com.togo.wx.common.constant;

/**
 * <p>返回结果code编码枚举</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年09月18日 08:27
 * @since 1.0
 */
public enum ResultCode {

    SUCCESS(1, "成功"),
    ERROR_DEFAULT(-1, "失败"),
    ERROR_HAVE_NOT_LOGIN(-2, "用户没有登陆"),
    ;

    private int index;
    private String message;

    ResultCode(int index, String message) {
        this.index = index;
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }
}