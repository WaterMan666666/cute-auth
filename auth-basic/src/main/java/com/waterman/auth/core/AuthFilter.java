package com.waterman.auth.core;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/6/24
 * @Description: 所有授权认证的主过滤器，该过滤器负责组织所有的过滤器在该内容中解决
 */
public class AuthFilter implements Filter {

    protected FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.setFilterConfig(filterConfig);
    }

    @Override
    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
