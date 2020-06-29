package com.waterman.auth.core.token.login;

import com.waterman.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class PasswordTokenProcessor extends LoginTokenProcessor<PasswordToken> {


    @Override
    public boolean isTypeMatch(PasswordToken token) {
        return false;
    }

    @Override
    public User parseToken(PasswordToken token) {
        return null;
    }
}
