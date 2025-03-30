package com.example.springbootbigevent.interceptors;

import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * Check if user has login, if not, return false
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            // User already login
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            // User didn't login
            response.setStatus(401);
            return false;
        }
    }
}
