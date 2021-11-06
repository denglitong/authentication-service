package com.denglitong.authenticationservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/11/5
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // use the rawPassword in your Basic Auth Http post...
        String encodedSecret = passwordEncoder.encode("this_is_secret");

        // 定义有哪些注册服务到OAuth
        clients.inMemory()
                .withClient("eagleeye")
                .secret(encodedSecret) // clientId调用OAuth以获取令牌时需提供的密钥
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient"); // 定义令牌可操作的作用域
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 使用Spring提供的默认验证管理器和用户详细信息服务
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(new BCryptPasswordEncoder())
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

}
