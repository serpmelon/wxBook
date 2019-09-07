package com.togo.wx.framework.filter;

import javax.servlet.*;
import javax.sound.midi.Soundbank;
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
 * @date Created in 2019年08月29日 20:55
 * @since 1.0
 */
public class TwoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("two init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("two before");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("two after");
    }

    @Override
    public void destroy() {

    }
}