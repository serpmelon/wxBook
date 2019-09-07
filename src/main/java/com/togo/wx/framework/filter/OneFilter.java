package com.togo.wx.framework.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

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
 * @date Created in 2019年08月29日 20:41
 * @since 1.0
 */
@WebFilter(filterName = "oneFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "", value = "")})
public class OneFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("one init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("one before filter");
        String openId = servletRequest.getParameter("openId");
//        if ()
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("one after filter");
    }

    @Override
    public void destroy() {

        System.out.println("one destroy");
    }
}