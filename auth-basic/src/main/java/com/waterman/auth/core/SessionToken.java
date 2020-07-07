package com.waterman.auth.core;

import com.waterman.auth.basic.enums.SessionTokenType;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public abstract class SessionToken implements Token {

    /** token真实值 */
    private String token;

    /** token的类型 */
    private SessionTokenType type;

    public SessionToken(String token, SessionTokenType type) {
        this.token = token;
        this.type = type;
    }

    @Override
    public String getToken() {
        return token;
    }
}
