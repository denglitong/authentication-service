package com.denglitong.authenticationservice.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/11/5
 */
@Component
public class InspectHeaderFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(InspectHeaderFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        logger.info("I am hitting the auth server: {}", httpServletRequest.getHeader("Authorization"));

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {

    }
}
