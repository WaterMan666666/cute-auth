package com.waterman.auth.web.filter.support;

import com.waterman.auth.web.PatternMatcher;
import com.waterman.auth.web.filter.manager.AuthFilterChain;
import com.waterman.auth.web.utils.AntPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/7/2
 * @Description:
 */
public class PathMatchingFilter extends AbstractFilter {

    private static final Logger log = LoggerFactory.getLogger(PathMatchingFilter.class);

    private final int order;
    private final String CONTINUE_HANDLER = "CONTINUE_HANDLER";
    private final String CONTINUE_CHAIN = "CONTINUE_CHAIN";

    public PathMatchingFilter(String name, int order) {
        super(name);
        this.order = order;
    }

    protected PatternMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        init(servletRequest, servletResponse, filterChain);
        preHanlder(servletRequest, servletResponse, filterChain);
        boolean continueHandler = continueHandler(servletRequest);
        if(continueHandler) {
            this.doHandler(servletRequest, servletResponse, filterChain);
        }
        boolean continueChain = continueChain(servletRequest);
        if(continueChain) {
            executeChain(servletRequest,servletResponse,filterChain);
        }
        afterHandler(servletRequest, servletResponse);
    }

    private void init(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        continueHandler(servletRequest, Boolean.TRUE);
        continueChain(servletRequest, Boolean.TRUE);
    }


    /**
     * 执行外部剩下的filter
     */
    protected void continueOriginChain(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(filterChain instanceof AuthFilterChain){
            AuthFilterChain chain = (AuthFilterChain) filterChain;
            chain.doOriginChain();
        }
    }

    /**
     * 前置处理
     */
    protected void preHanlder(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    }
    /**
     * 后置处理
     */
    protected void afterHandler(ServletRequest servletRequest, ServletResponse servletResponse) {

    }
    /**
     * 是否继续处理业务逻辑
     */
    protected boolean continueHandler(ServletRequest servletRequest) {
        return Boolean.valueOf(servletRequest.getAttribute(CONTINUE_HANDLER).toString());
    }


    /**
     * 是否继续处理业务逻辑
     */
    protected void continueHandler(ServletRequest servletRequest,  boolean isContinue) {
        servletRequest.setAttribute(CONTINUE_HANDLER, isContinue);
    }

    /**
     * 是否继续处理业务逻辑
     */
    protected void continueChain(ServletRequest servletRequest,  boolean isContinue) {
        servletRequest.setAttribute(CONTINUE_CHAIN, isContinue);
    }

    /**
     * 是否继续处理Filter链
     */
    protected boolean continueChain(ServletRequest servletRequest){
        return Boolean.valueOf(servletRequest.getAttribute(CONTINUE_CHAIN).toString());
    }


    /**
     * 执行业务逻辑
     */
    @Override
    protected void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

    }

    protected void executeChain(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    protected int getOrder() {
        return order;
    }

}
