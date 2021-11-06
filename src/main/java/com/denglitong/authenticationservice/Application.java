package com.denglitong.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/11/5
 */
@SpringBootApplication
@EnableEurekaClient
// 声明是一个Oauth令牌服务，生成oauth令牌（token）
@EnableAuthorizationServer
// 声明是一个资源授权服务，对授权后生成的token进行鉴权
@EnableResourceServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
