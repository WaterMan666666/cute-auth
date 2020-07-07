package com.waterman.auth.core.client.spring.annotation;

import com.waterman.auth.core.client.spring.config.AuthConfigurationRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@Import({AuthConfigurationRegistrar.class})
public @interface EnableAuthClient {
}
