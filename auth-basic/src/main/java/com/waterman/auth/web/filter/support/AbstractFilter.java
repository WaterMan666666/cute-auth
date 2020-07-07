package com.waterman.auth.web.filter.support;

import com.waterman.auth.core.Nameable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/6/24
 * @Description: 所有授权认证的主过滤器，该过滤器负责组织所有的过滤器在该内容中解决
 */
public abstract class AbstractFilter implements Filter, Nameable {

    private static final Logger log = LoggerFactory.getLogger(AbstractFilter.class);

    public AbstractFilter(String name) {
        this.name = name;
    }

    private String name;

    protected FilterConfig filterConfig;

    private ServletContext servletContext = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.setFilterConfig(filterConfig);
        this.servletContext = filterConfig.getServletContext();
        if (this.name == null) {
            FilterConfig config = this.getFilterConfig();
            if (config != null) {
                this.name = config.getFilterName();
            }
        }
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        this.doFilterInternal(request, response, filterChain);
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public ServletContext getServletContext(){
        return servletContext;
    }

    protected abstract void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException;

    protected abstract void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException;
}
