package com.waterman.auth.core.service;

import com.waterman.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/12
 * @Description:
 */
public interface UserService {


    /**
     * 认证
     */
    User authenticate(String username , String password);
}
