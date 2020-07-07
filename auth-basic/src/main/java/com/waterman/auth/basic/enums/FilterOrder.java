package com.waterman.auth.basic.enums;

/**
 * @author tongdong
 * @Date: 2020/7/2
 * @Description: 内置过滤器的顺序
 */
public enum FilterOrder {

    /**匿名通过*/
    anno(Integer.MIN_VALUE),
    /**服务端认证*/
    auth_server(-100),
    /**客户端端认证*/
    auth(0);



    public int order;

    FilterOrder( int order){
        this.order = order;
    }


}
