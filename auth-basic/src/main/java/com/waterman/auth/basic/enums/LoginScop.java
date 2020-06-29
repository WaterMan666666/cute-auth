package com.waterman.auth.basic.enums;

/**
 * @author tongdong
 * @Date: 2020/6/12
 * @Description:
 */
public enum LoginScop {

    /**同一个用户登录仅能存在一个session实例*/
    Singleton,
    /**同一个用户登录能存在多个session实例*/
    Prototype
}
