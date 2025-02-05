package com.cjp.musiclibrary.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.cjp.musiclibrary.exception.CustomException;
import com.cjp.musiclibrary.thread.ThreadContext;
import com.cjp.musiclibrary.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author CJP
 * @version 1.0
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        try {
            Map<String, Claim> claimMap = JwtUtil.verify(token);
            ThreadContext.setTl(claimMap.get("user"));
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
//            throw new CustomException("400", "非法鉴权");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        每次请求结束后，清理用户线程
        ThreadContext.removeTl();
    }
}
