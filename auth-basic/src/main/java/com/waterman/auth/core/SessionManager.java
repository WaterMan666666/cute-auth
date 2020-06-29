package com.waterman.auth.core;


/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SessionManager {


    /**
     * 用户登录
     */
    SessionToken login(LoginToken token);

    /**
     * @Author tongdong
     * @Date  2020/6/10
     * 用户是否已经登录
     */
    Session checkLogin(SessionToken token);


    /**
     * @Author tongdong
     * @Date  2020/6/10
     * 用户登出
     */
    void logout(SessionToken token);
}
