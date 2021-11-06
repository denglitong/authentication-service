package com.denglitong.authenticationservice.controller;

import com.denglitong.authenticationservice.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/11/5
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping
    public Map<String, Object> user(OAuth2Authentication user) {
        logger.info("user: {}", user);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(
                user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }
}
