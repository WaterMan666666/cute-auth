package com.waterman.auth.core.token.session;

import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.TokenProcessor;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public abstract class SessionTokenProcessor<T extends SessionToken> implements TokenProcessor<T> {


    /**
     * 验证toke并获取Session信息
     */
    public abstract Session parseToken(T token);
}
