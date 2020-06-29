package com.waterman.auth.core.client.manager;


import com.waterman.auth.basic.enums.LoginScop;
import com.waterman.auth.core.*;
import com.waterman.auth.core.client.session.dao.DefaultLocalSessionDao;
import com.waterman.auth.core.session.DefaultSessionFactory;
import com.waterman.auth.core.session.DefaultSessionTokenFactory;
import com.waterman.auth.core.token.login.PasswordTokenProcessor;
import com.waterman.auth.core.token.session.AccessTokenProcessor;

import java.util.Arrays;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class DefaultSimpleClientSessionManager extends AbstractSessionManager {



    public DefaultSimpleClientSessionManager(int sessionExpireTime) {
        setScop(LoginScop.Singleton);

        AccessTokenProcessor accessTokenProcessor = new AccessTokenProcessor();
        setSessionTokenProcessors(Arrays.asList(accessTokenProcessor));

        PasswordTokenProcessor passwordTokenProcessor = new PasswordTokenProcessor();
        setLoginTokenProcessors(Arrays.asList(passwordTokenProcessor));

        setSessionDao(new DefaultLocalSessionDao(sessionExpireTime));
        setSessionTokenFactory(new DefaultSessionTokenFactory());
        setSessionFactory(new DefaultSessionFactory());
    }
}
