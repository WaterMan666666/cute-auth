package com.waterman.auth.web.filter;

import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionManager;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.token.session.AccessToken;
import com.waterman.auth.core.token.session.CookieToken;
import com.waterman.auth.web.filter.support.PathMatchingFilter;
import com.waterman.auth.web.utils.CookieUtils;
import com.waterman.auth.web.utils.StringUtils;
import com.waterman.auth.web.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/7/2
 * @Description:
 */
public abstract class AuthenticationFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    private SessionManager sessionManager;

    private static final String LOGIN_URL = "/login";

    private List<String> loginUrl = new ArrayList<>();

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    protected static final String AUTHORIZATION_TOKEN = "token";

    public AuthenticationFilter(SessionManager sessionManager, String name, int order) {
        super(name, order);
        this.sessionManager = sessionManager;
        loginUrl.add(LOGIN_URL);
    }
    //整理思路
    //1.检查当前的请求是否是登录状态
        //1.1登录分模式检查（cookie/token）
    //2. 根据是否登录来判断如何进行下一步 （先在本地session进行验证，未通过再去授权中心进行验证）
        //2.1登录：放行

        //2.2未登录：转向登录页面（返回登录地址的json字符串）
    //重定向到Login页面
    //SSo 去授权服务检查拿到的seesionToken 是不是 有效的
//                有效 -》 登录
//                无效 -》 返回提示，472未登录

    /**
     * 执行业务逻辑
     */
    @Override
    protected void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(doLoginPath(servletRequest,servletResponse)){
            //登录页的请求,执行原始的Filter,放行
            continueOriginChain(servletRequest,servletResponse,filterChain);
            return;
        }
        SessionToken sessionToken = getSessionToken(servletRequest);
        if(isAuthenticate(sessionToken)){
            //已登录, 放行
            return;
        }
        //未登录 重定向到Login页面
        unAuthenticate(servletRequest,servletResponse);
    }

    private boolean isAuthenticate(SessionToken sessionToken){
        Session session = sessionManager.checkLogin(sessionToken);
        return session != null;
    }

    private boolean doLoginPath(ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = WebUtils.getPathWithinApplication(httpServletRequest);
        boolean match = loginUrl.stream().anyMatch(item -> pathMatcher.matches(requestURI, item));
        if(match){
            return true;
        }
        return false;
    }


    protected  SessionToken getSessionToken(ServletRequest servletRequest){
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        //该处支持三种方式
        //  token
        SessionToken sessionToken = getAuthorizationToken(httpRequest);
        if(sessionToken != null){
            return sessionToken;
        }
        //parameter
        sessionToken = getParameterToken(httpRequest);
        if(sessionToken != null){
            return sessionToken;
        }
        // cookie
        sessionToken = getCookieToken(httpRequest);
        return sessionToken;
    }

    private SessionToken getParameterToken(HttpServletRequest httpRequest){
        String parameter = httpRequest.getParameter(AUTHORIZATION_TOKEN);
        if (StringUtils.isBlank(parameter)) {
            return null;
        }
        return new AccessToken(parameter);
    }

    /**
     * 从header中获取token
     */
    private SessionToken getAuthorizationToken(HttpServletRequest httpRequest){
        //header中获取
        String authorizationHeader = httpRequest.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || authorizationHeader.length() == 0) {
            return null;
        }
        String[] authTokens = authorizationHeader.split(" ");
        if (authTokens == null || authTokens.length < 2) {
            return null;
        }
        return new AccessToken(authTokens[1]);
    }

    /**
     * 从cookie中获取token
     */
    private SessionToken getCookieToken(HttpServletRequest httpRequest){
        String cookie = CookieUtils.getValue(httpRequest, "");
        if (cookie == null || cookie.length() == 0) {
            return null;
        }
        return new CookieToken(cookie);
    }



    protected void unAuthenticate(ServletRequest request, ServletResponse response) throws IOException {
        //未登录则，重定向到Login页面
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //返回未登录信息
        httpResponse.sendRedirect(LOGIN_URL);
        //handler
        continueChain(request, false);
    }
}
