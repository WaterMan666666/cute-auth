package com.waterman.auth.core.server.manager;

import com.waterman.auth.core.*;
import com.waterman.auth.core.service.ClientService;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class DefaultSsoServerSessionManager extends AbstractServerSessionManager implements SsoSessionManager, ServerSessionManager {

    @Override
    public SessionToken login(LoginToken token) {
        //检查信息
        SessionToken sessionToken = super.login(token);
        //获取client信息
        Client client = getClientService().getClient(token.getClientId());
        //单点登录/
        ssoLogin(sessionToken, client);
        return sessionToken;
    }

    @Override
    public Session ssoLogin(SessionToken token, Client client) {
        return null;
    }

    @Override
    public void ssoLogout(SessionToken token, List<Client> clients) {

    }

    @Override
    public List<User> getAllOlineInfo() {
        return null;
    }

    @Override
    public void logout(User user) {

    }

    @Override
    public void logout(User user, List<Client> clients) {

    }


    @Override
    public Session checkLogin(SessionToken token) {
        return super.checkLogin(token);
    }

    @Override
    public void logout(SessionToken token) {
        super.logout(token);
    }

    protected ClientService getClientService() {
        return null;
    }


}
