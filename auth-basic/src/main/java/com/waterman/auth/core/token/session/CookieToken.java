package com.waterman.auth.core.token.session;

import com.waterman.auth.core.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class CookieToken extends SessionToken {



    public CookieToken(String cookie){
        super(cookie);
    }
    @Override
    public String getToken() {
        return null;
    }
}
