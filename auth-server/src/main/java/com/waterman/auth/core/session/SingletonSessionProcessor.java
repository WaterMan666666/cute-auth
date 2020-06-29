package com.waterman.auth.core.session;

import com.waterman.auth.core.Session;
import com.waterman.auth.core.User;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/18
 * @Description:
 */
public class SingletonSessionProcessor extends AbstractSessionProcessor {


    @Override
    public Session createSession(User loginUser) {
        //查询出session
        //删除旧session
        List<Session> sessions = readSession(loginUser);
        for (int i = 0; i <; i++) {
            destroySession(session);
        }
        //创建session
        return super.createSession(loginUser);
    }

    @Override
    public void destroySession(Session session) {
        super.destroySession(session);
    }

    private List<Session> readSession(User loginUser){
        return null;
    }
}
