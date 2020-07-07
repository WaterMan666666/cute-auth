package com.waterman.auth.test;

import com.waterman.auth.core.client.spring.annotation.EnableAuthClient;
import com.waterman.auth.test.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tongdong
 * @Date: 2020/6/4
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.waterman.auth.test")
@RestController
public class AuthTestWebStarter {

    private static Logger logger = LoggerFactory.getLogger(AuthTestWebStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthTestWebStarter.class, args);
    }

    /**
     * 域名监控
     * @return
     */
    @RequestMapping(value = "/api/test")
    @ResponseBody
    public String urlmonitor(){
        return "OK";
    }
}
