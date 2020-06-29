package com.waterman.auth.core.token.login;

import com.waterman.auth.core.LoginToken;
import com.waterman.auth.core.TokenProcessor;
import com.waterman.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public abstract class LoginTokenProcessor<T extends LoginToken> implements TokenProcessor<T> {



    /**
     * 验证toke并获取用户信息
     */
    public abstract User parseToken(T token);
}
