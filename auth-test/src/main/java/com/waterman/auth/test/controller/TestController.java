package com.waterman.auth.test.controller;

import com.waterman.auth.core.SessionManager;
import com.waterman.auth.core.SessionToken;
import com.waterman.auth.core.token.login.PasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tongdong
 * @Date: 2020/7/6
 * @Description:
 */
@Controller
public class TestController {


    @Autowired
    private SessionManager sessionManager;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        logger.info("登录调用");
        return "login";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public SessionToken loginPost(@RequestParam String username, @RequestParam String password){
        logger.info("登录-POST");
        PasswordToken token = new PasswordToken(username, password);
        SessionToken sessionToken = sessionManager.login(token);
        return sessionToken;
    }
}
