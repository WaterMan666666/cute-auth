package com.waterman.auth.core.token.login;

import com.waterman.auth.core.Token;
import com.waterman.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class PasswordTokenProcessor extends LoginTokenProcessor<PasswordToken> {


    @Override
    public boolean isTypeMatch(Token token) {
        return token instanceof PasswordToken;
    }

    @Override
    public User parseToken(PasswordToken token) {
        User user = new User();
        user.setId("1");
        user.setRealName("admin");
        user.setUsername("admin");
        return user;
    }
}
