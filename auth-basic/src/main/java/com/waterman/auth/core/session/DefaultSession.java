package com.waterman.auth.core.session;

import com.waterman.auth.core.Session;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 默认的session实现
 */
public class DefaultSession implements Session {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public Object getAttribute(Object key) {
        return null;
    }

    @Override
    public void setAttribute(Object key, Object value) {

    }

    @Override
    public Object removeAttribute(Object key) {
        return null;
    }

    @Override
    public void setId(String id) {

    }
}
