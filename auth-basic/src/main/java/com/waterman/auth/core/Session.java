package com.waterman.auth.core;

import java.io.Serializable;

/**
 * @author tongdong
 * @Date: 2020/6/8
 * @Description:
 */
public interface Session {


    String getId();

    Object getAttribute(Object key);

    void setAttribute(Object key, Object value);

    Object removeAttribute(Object key);

    void setId(String id);

}
