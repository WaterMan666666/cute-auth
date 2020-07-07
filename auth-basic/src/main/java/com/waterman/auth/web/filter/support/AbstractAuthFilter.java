package com.waterman.auth.web.filter.support;

import com.waterman.auth.web.FilterChainResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public  class AbstractAuthFilter  extends AbstractFilter {

    private static final Logger log = LoggerFactory.getLogger(AbstractAuthFilter.class);

    private FilterChainResolver filterChainResolver;


    public AbstractAuthFilter(FilterChainResolver filterChainResolver) {
        super("cute-auth");
        this.filterChainResolver = filterChainResolver;
    }

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        doHandler(servletRequest, servletResponse, filterChain);
    }

    @Override
    protected void doHandler(ServletRequest request, ServletResponse response, FilterChain originChain) throws ServletException, IOException {
        //当创建出session时，则需要管理session信息
        updateSessionLastAccessTime(request, response);
        //创建组装执行链
        FilterChain chain = getExecutionChain(request, response, originChain);
        chain.doFilter(request, response);
    }


    private FilterChain getExecutionChain(ServletRequest request, ServletResponse response, FilterChain originChain) {
        return filterChainResolver.getChain(request, response, originChain);
    }

    /**
     * 更新session的时间
     */
    private void updateSessionLastAccessTime(ServletRequest request, ServletResponse response) {

    }

}
