package com.waterman.auth.core;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SsoSessionManager extends  SessionManager{


    /**
     * 用户单点登录
     */
    Session ssoLogin(SessionToken token, Client client);
    /**
     * 该接口主要完成所有已经登录的子系统的登出通知
     */
    void ssoLogout(SessionToken token, List<Client> clients);
}
