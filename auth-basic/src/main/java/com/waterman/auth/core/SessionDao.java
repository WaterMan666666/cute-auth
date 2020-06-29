package com.waterman.auth.core;

import java.io.Serializable;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SessionDao {


    Session readSession(String sessionId);


    void createSession(Session session);


    void destroySession(Session session);
}
