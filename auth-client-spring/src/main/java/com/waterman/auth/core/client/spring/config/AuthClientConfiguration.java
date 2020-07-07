package com.waterman.auth.core.client.spring.config;

import com.waterman.auth.basic.enums.FilterOrder;
import com.waterman.auth.core.SessionManager;
import com.waterman.auth.core.client.config.AuthClientConfig;
import com.waterman.auth.core.client.filter.ClientAuthenticationFilter;
import com.waterman.auth.core.client.manager.DefaultSimpleClientSessionManager;
import com.waterman.auth.web.filter.support.AbstractAuthFilter;
import com.waterman.auth.web.FilterChainResolver;
import com.waterman.auth.web.filter.manager.AuthFilterChainManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;


/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
@Configuration
public class AuthClientConfiguration {


    @Bean
    public AuthClientConfig authClientConfig(){

        AuthClientConfig authClientConfig = new AuthClientConfig();
        authClientConfig.setSessionExpireTime(1800);
        authClientConfig.addFilterMap("/**", Arrays.asList(FilterOrder.auth.name()));
        return authClientConfig;
    }

    @Bean
    public SessionManager sessionManager(AuthClientConfig clientConfig){
        DefaultSimpleClientSessionManager defaultSimpleClientSessionManager = new DefaultSimpleClientSessionManager(clientConfig);
        return defaultSimpleClientSessionManager;
    }


    @Bean
    public Filter tongdongFilter(AuthClientConfig clientConfig, SessionManager sessionManager){
        ClientAuthenticationFilter clientAuthenticationFilter = new ClientAuthenticationFilter(sessionManager);
        clientConfig.addFilter(clientAuthenticationFilter.getName(), clientAuthenticationFilter);
        AuthFilterChainManager authFilterChainManager = new AuthFilterChainManager(clientConfig.getFilters(), clientConfig.getFilterMap());
        return new SpringAuthFilter(authFilterChainManager);
    }


    private static final  class SpringAuthFilter extends AbstractAuthFilter {
        public SpringAuthFilter(FilterChainResolver filterChainResolver) {
            super(filterChainResolver);
        }


    }

}
