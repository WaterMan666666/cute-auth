package com.waterman.auth.core.client.session.dao;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.waterman.auth.core.Session;
import com.waterman.auth.core.SessionDao;

import java.util.concurrent.TimeUnit;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 内存中的实现方式，建议不超过5000个用户
 */
public class DefaultLocalSessionDao implements SessionDao {

    private static final int CAFFEINE_MAXSIZE = 5000;

    private final Cache<String, Session> cache ;

    public DefaultLocalSessionDao(int sessionExpireTime) {
        cache = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpireTime, TimeUnit.SECONDS)
                .maximumSize(CAFFEINE_MAXSIZE).build();
    }
    @Override
    public Session readSession(String sessionId) {
        return cache.getIfPresent(sessionId);
    }

    @Override
    public void createSession(Session session) {
        String id = session.getId();
        cache.put(id, session);
    }

    @Override
    public void destroySession(Session session) {
        String id = session.getId();
        cache.invalidate(id);
    }
}
