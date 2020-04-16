package com.van.demo.filter;

import com.github.pagehelper.StringUtil;
import com.van.demo.annotation.Ignore;
import com.van.demo.dao.entity.Name;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = request.getRequestURI();
        log.debug("Method: " + method.getName() + ", IgnoreSecurity: " + method.isAnnotationPresent(Ignore.class));
        log.debug("requestPath: " + requestPath);
        if (requestPath.contains("/v2/api-docs") || requestPath.contains("/swagger") || requestPath.contains("/configuration/ui")) {
            return true;
        }
        if (requestPath.contains("/error")) {
            return true;
        }
        if (method.isAnnotationPresent(Ignore.class)) {
            return true;
        }
        String token = request.getHeader("ACCESS_TOKEN");
        log.debug("token: " + token);
        if (StringUtil.isEmpty(token)) {
            throw new Exception("无效token");
        }
        Name name = new Name();
        name.setId(1);
        name.setName(token);
        request.setAttribute("Name", name);
        return true;
    }
}
