package com.waterman.auth.core;

import java.io.Serializable;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SessionDao {


    Session readSession(Serializable sessionId);


    Serializable create(Session session);
}
