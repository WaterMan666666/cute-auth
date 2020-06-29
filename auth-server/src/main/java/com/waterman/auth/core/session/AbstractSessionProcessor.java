package com.waterman.auth.core.session;

import com.waterman.auth.core.*;

/**
 * @author tongdong
 * @Date: 2020/6/19
 * @Description:
 */
public abstract class AbstractSessionProcessor implements SessionProcessor {

    private SessionDao sessionDao;

    /**
     * 创建session
     */
    @Override
    public Session createSession(User loginUser) {
        return null;
    }
    /**
     * 摧毁session
     */
    @Override
    public void destroySession(Session session) {

    }
    /**
     * 通过token将用户的Session信息查询到
     */
    @Override
    public Session readSession(SessionToken sessionToken) {
        return null;
    }

    protected SessionDao getSessionDao(){
        return sessionDao;
    }
}
