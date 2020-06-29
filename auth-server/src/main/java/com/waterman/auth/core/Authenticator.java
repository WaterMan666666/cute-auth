package com.waterman.auth.core;

/**
 * @author tongdong
 * @Date: 2020/6/8
 * @Description:
 */
public interface Authenticator {



    Session verify(Token token);
    

}
