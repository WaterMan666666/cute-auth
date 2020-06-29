package com.waterman.auth.core;

import com.waterman.auth.basic.enums.SessionTokenType;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 创建的sessionToken的工厂
 */
public interface SessionTokenFactory {

    /**
     * 创建会话的Token
     */
    SessionToken generateSessionToken(SessionTokenType type ,Session session);
}
