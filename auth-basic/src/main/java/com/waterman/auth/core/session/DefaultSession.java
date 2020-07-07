package com.waterman.auth.core.session;

import com.waterman.auth.core.Session;

import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 默认的session实现
 */
public class DefaultSession implements Session {

    private String id;

    private Map<Object,Object> attributes;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getAttribute(Object key) {
        return attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        return attributes.remove(key);
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
