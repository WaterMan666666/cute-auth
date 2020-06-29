package com.waterman.auth.core;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/23
 * @Description:
 */
public interface ServerSessionManager {

    /**
     * 获取所有登录人及登录系统的信息
     */
    List<User> getAllOlineInfo();
    /**
     * 将某位用户强制登出
     */
    void logout(User user);

    /**
     * 将某位用户强制登出部分系统
     */
    void logout(User user, List<Client> clients);
}
