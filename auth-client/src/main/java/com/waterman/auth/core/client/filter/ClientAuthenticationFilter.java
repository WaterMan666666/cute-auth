package com.waterman.auth.core.client.filter;

import com.waterman.auth.basic.enums.FilterOrder;
import com.waterman.auth.core.SessionManager;
import com.waterman.auth.web.filter.AuthenticationFilter;

/**
 * @author tongdong
 * @Date: 2020/7/2
 * @Description:
 */
public class ClientAuthenticationFilter extends AuthenticationFilter {


    public ClientAuthenticationFilter(SessionManager sessionManager) {
        super(sessionManager, FilterOrder.auth.name(), FilterOrder.auth.order);
    }
}
