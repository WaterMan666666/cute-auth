package com.waterman.auth.test;

import com.waterman.auth.core.client.spring.annotation.EnableAuthClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tongdong
 * @Date: 2020/6/4
 * @Description:
 */
@SpringBootApplication
@EnableAuthClient
public class AuthTestWebStarter {

    private static Logger logger = LoggerFactory.getLogger(AuthTestWebStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthTestWebStarter.class, args);
    }
}
