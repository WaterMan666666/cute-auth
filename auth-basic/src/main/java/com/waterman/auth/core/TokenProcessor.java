package com.waterman.auth.core;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface TokenProcessor<T extends Token> {


    /**
     *
     */
    boolean isTypeMatch(Token token);
}
