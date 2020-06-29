package com.waterman.auth.core;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface OauthSessionManager extends  SessionManager{


    /**
     * 用户单点登录
     */
    Session ssoLogin(SessionToken token, Client client);
    /**
     * 该接口主要完成所有已经登录的子系统的登出通知
     */
    void ssoLogout(SessionToken token, List<Client> clients);
    /**
     * 获取所有登录人及登录系统的信息
     */
    List<User> getAllOlineInfo();
    /**
     * 将某位用户强制登出
     */
    void logout(User user);
    /**
     * 将某位用户强制登出部分系统
     */
    void logout(User user, List<Client> clients);
}
