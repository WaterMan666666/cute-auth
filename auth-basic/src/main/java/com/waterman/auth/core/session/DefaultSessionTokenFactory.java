package com.waterman.auth.core.session;

import com.waterman.auth.basic.enums.SessionTokenType;
import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.SessionTokenFactory;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 默认的SessionToken工厂
 */
public class DefaultSessionTokenFactory implements SessionTokenFactory {
    @Override
    public SessionToken generateSessionToken(SessionTokenType type, Session session) {
        return null;
    }
}
