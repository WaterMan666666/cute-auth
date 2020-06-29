package com.waterman.auth.core.service;

import com.waterman.auth.core.Client;

/**
 * @author tongdong
 * @Date: 2020/6/12
 * @Description:
 */
public interface ClientService {

    /**
     * 判断登录的客户端存在
     */
    boolean isExist(String clientId);


    /**
     * 获取客户端完整的信息
     */
    Client getClient(String clientId);
}
