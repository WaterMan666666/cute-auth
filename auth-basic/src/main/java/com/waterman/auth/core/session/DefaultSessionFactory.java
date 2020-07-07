package com.waterman.auth.core.session;

import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionFactory;

import java.util.UUID;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 默认session工厂实现
 */
public class DefaultSessionFactory implements SessionFactory {
    @Override
    public Session generateSession() {
        DefaultSession session = new DefaultSession();
        session.setId(UUID.randomUUID().toString());
        return session;
    }
}
