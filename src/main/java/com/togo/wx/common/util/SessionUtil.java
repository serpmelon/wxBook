package com.togo.wx.common.util;

import com.togo.wx.common.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
 * @date Created in 2019年08月28日 21:19
 * @since 1.0
 */
@Component
public final class SessionUtil {

    @Autowired
    private HttpServletRequest request;

    public HttpSession getSession(HttpServletRequest request) {

        return request.getSession();
    }

    public void setOpenId(String openId) {

        HttpSession session = getSession(request);
        session.setAttribute(CommonConstant.OPEN_ID, openId);
    }

    public String getOpenId(HttpSession session) {

        Object object = session.getAttribute(CommonConstant.OPEN_ID);
        if (object == null)
            return null;
        else
            return (String) object;
    }

    public String getOpenId() {

        return getOpenId(getSession(request));
    }
}