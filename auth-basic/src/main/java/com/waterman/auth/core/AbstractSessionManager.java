package com.waterman.auth.core;

import com.waterman.auth.basic.enums.LoginScop;
import com.waterman.auth.basic.enums.SessionTokenType;
import com.waterman.auth.core.token.login.LoginTokenProcessor;
import com.waterman.auth.core.token.session.SessionTokenProcessor;

import java.util.List;


/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public abstract class AbstractSessionManager implements SessionManager {

    private LoginScop scop ;

    private List<SessionTokenProcessor> sessionTokenProcessors;

    private List<LoginTokenProcessor> loginTokenProcessors;

    private SessionDao sessionDao;

    private SessionTokenFactory sessionTokenFactory;

    private SessionFactory sessionFactory;


    /**
     * 用户登录
     */
    @Override
    public SessionToken login(LoginToken token) {
        //检查信息
        checkLoginInfo(token);
        //判断LoginToken是否为有效Token
        User loginUser = authenticate(token);
        //登录的扩展处理
        //创建session
        Session session = createSession(loginUser);
        //根据想要获取的token模式发送SessionToken
        return distributeSessionToken(token, session);
    }

    /**
     * 验证用户的登录信息是否正确
     */
    @Override
    public Session checkLogin(SessionToken sessionToken) {
        //判断SessionToken是否为有效Token
        Session session = readSession(sessionToken);
        return session;
    }

    /**
     * 登出
     */
    @Override
    public void logout(SessionToken token) {
        //判断SessionToken是否为有效Token
        Session session = checkLogin(token);
        //销毁已经存在的Session
        if(session != null) {
            destroySession(session);
        }
    }


    /**
     * 验证用户的登录信息是否正确
     */
    private User authenticate(LoginToken token) {
        //验证成功失败需要有扩展
        if(loginTokenProcessors != null && !loginTokenProcessors.isEmpty()){
            for (int i = 0; i < loginTokenProcessors.size(); i++) {
                TokenProcessor tokenProcessor = loginTokenProcessors.get(i);
                if(tokenProcessor instanceof LoginTokenProcessor && tokenProcessor.isTypeMatch(token)){
                    return ((LoginTokenProcessor) tokenProcessor).parseToken(token);
                }
            }
        }
        return null;
    }

    /**
     * 检查登录信息
     */
    protected void checkLoginInfo(LoginToken token){

    }

    /**
     * 检查登录信息
     */
    protected SessionToken distributeSessionToken(LoginToken loginToken, Session session){
        SessionTokenType sessionTokenType = loginToken.getSessionTokenType();
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken(sessionTokenType, session);
        return sessionToken;
    }

    /**
     * 根据登录用户创建session
     */
    protected Session createSession(User loginUser){
        Session session = sessionFactory.generateSession();
        sessionDao.createSession(session);
        return session;
    }

    /**
     * 获取session信息
     */
    protected  Session readSession(SessionToken token){
        //验证成功失败需要有扩展
        if(sessionTokenProcessors != null && !sessionTokenProcessors.isEmpty()){
            for (int i = 0; i < sessionTokenProcessors.size(); i++) {
                TokenProcessor tokenProcessor = sessionTokenProcessors.get(i);
                if(tokenProcessor instanceof SessionTokenProcessor && tokenProcessor.isTypeMatch(token)){
                    return ((SessionTokenProcessor) tokenProcessor).parseToken(token);
                }
            }
        }
        return null;
    }

    /**
     * 销毁session
     */
    protected void destroySession(Session session){
        sessionDao.destroySession(session);
    }


    protected void setScop(LoginScop scop) {
        this.scop = scop;
    }

    protected void setSessionTokenProcessors(List<SessionTokenProcessor> sessionTokenProcessors) {
        this.sessionTokenProcessors = sessionTokenProcessors;
    }

    protected void setLoginTokenProcessors(List<LoginTokenProcessor> loginTokenProcessors) {
        this.loginTokenProcessors = loginTokenProcessors;
    }

    protected void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    protected void setSessionTokenFactory(SessionTokenFactory sessionTokenFactory) {
        this.sessionTokenFactory = sessionTokenFactory;
    }

    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
