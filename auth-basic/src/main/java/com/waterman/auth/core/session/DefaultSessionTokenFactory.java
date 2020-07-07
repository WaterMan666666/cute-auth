package com.waterman.auth.core.session;

import com.waterman.auth.basic.enums.SessionTokenType;
import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.SessionTokenFactory;
import com.waterman.auth.core.token.session.AccessToken;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 默认的SessionToken工厂
 */
public class DefaultSessionTokenFactory implements SessionTokenFactory {
    @Override
    public SessionToken generateSessionToken(SessionTokenType type, Session session) {
        String id = session.getId();
        if(type == null){
            return new AccessToken(id);
        }
        switch (type){
            case Code:
            case AccessToken:
                return new AccessToken(id);
        }
        return null;
    }
}
