package com.waterman.auth.core.token.session;

import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionDao;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.Token;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class AccessTokenProcessor extends SessionTokenProcessor {

    private SessionDao sessionDao;

    public AccessTokenProcessor(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public boolean isTypeMatch(Token token) {
        return token instanceof AccessToken;
    }


    @Override
    public Session parseToken(SessionToken token) {
        return sessionDao.readSession(token.getToken());
    }
}
