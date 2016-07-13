/**
 * com.wolong.web.filter.LogUserInfoFilter.java created by Xueliang(liang.xue@shenma-inc.com) on
 * 2015骞�鏈�4鏃�涓婂崍11:07:14
 */
package com.xhj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.xhj.common.ThreadContext;

public class ThreadContextFilter implements Filter {

  public void destroy() {}

  public void doFilter(ServletRequest arg1, ServletResponse arg2, FilterChain filterChain)
      throws IOException, ServletException {
    ThreadContext.init();
    filterChain.doFilter(arg1, arg2);
    ThreadContext.clean();
  }

  public void init(FilterConfig arg0) throws ServletException {}
}
