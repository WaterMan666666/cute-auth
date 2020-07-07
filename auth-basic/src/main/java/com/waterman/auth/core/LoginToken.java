package com.waterman.auth.core;

import com.waterman.auth.basic.enums.SessionTokenType;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public abstract class LoginToken implements Token {

    private String clientId;

    private String redirectUri;

    private SessionTokenType sessionTokenType;


    public LoginToken() {
    }



    public String getClientId() {
        return clientId;
    }


    public String getRedirectUri() {
        return redirectUri;
    }

    public SessionTokenType getSessionTokenType() {
        return sessionTokenType;
    }
}
