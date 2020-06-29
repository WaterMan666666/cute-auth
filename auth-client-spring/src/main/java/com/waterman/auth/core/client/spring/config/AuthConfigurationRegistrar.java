package com.waterman.auth.core.client.spring.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description:
 */
public class AuthConfigurationRegistrar implements ImportBeanDefinitionRegistrar {

//    private static final Log logger = LogFactory.getLog(AuthConfigurationRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //可以控制其扫描配置包下面的所有信息
        System.out.println("==============================");
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(registry);
        int count = classPathBeanDefinitionScanner.scan("com.waterman.auth.core.client.spring");
//        if (logger.isDebugEnabled()) {
//            logger.debug("开启auth整合spring的扫描，参与类个数：{}", count);
//        }

    }


    //        return new DefaultSimpleClientSessionManager(30 * 60);
}
