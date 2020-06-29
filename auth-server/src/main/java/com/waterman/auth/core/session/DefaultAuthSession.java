package com.waterman.auth.core.session;

import com.waterman.auth.core.Session;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class DefaultAuthSession implements Session {

    private transient String id;
    private transient long timeout;
    private transient Map<Object, Object> attributes;

    @Override
    public Serializable getId() {
        return id;
    }

    @Override
    public Object getAttribute(Object key) {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value)  {

    }

    @Override
    public Object removeAttribute(Object key) {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        } else {
            return attributes.remove(key);
        }
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }
}
