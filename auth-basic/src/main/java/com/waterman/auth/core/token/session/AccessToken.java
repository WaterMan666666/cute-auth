package com.waterman.auth.core.token.session;

import com.waterman.auth.core.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class AccessToken extends SessionToken {


    public AccessToken(String accesstoken) {
        super(accesstoken);
    }

}
