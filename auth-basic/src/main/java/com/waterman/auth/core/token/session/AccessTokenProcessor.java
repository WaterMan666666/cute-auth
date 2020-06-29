package com.waterman.auth.core.token.session;

import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.Token;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class AccessTokenProcessor extends SessionTokenProcessor {


    @Override
    public boolean isTypeMatch(Token token) {
        return false;
    }


    @Override
    public Session parseToken(SessionToken token) {
        return null;
    }
}
