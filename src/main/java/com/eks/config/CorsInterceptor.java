package com.eks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
@Component("corsInterceptor")
public class CorsInterceptor extends HandlerInterceptorAdapter {

    @Value("${cookie_name}")
    private String cookieName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","*");
        response.addHeader("Access-Control-Max-Age","100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type," + cookieName);
        response.addHeader("Access-Control-Expose-Headers", cookieName);
        response.addHeader("Access-Control-Allow-Credentials","false");
        return super.preHandle(request, response, handler);
    }
}
