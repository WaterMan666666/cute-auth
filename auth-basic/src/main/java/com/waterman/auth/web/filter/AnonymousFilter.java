package com.waterman.auth.web.filter;

import com.waterman.auth.basic.enums.FilterOrder;
import com.waterman.auth.web.filter.manager.AuthFilterChain;
import com.waterman.auth.web.filter.support.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/6/29
 * @Description: 什么都通过的Filter
 */
public class AnonymousFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(AnonymousFilter.class);

    public AnonymousFilter() {
        super(FilterOrder.anno.name(), FilterOrder.anno.order);
    }

    @Override
    protected void preHanlder(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        continueOriginChain(request, response, filterChain);
    }
}
