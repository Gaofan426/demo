package com.van.demo.filter;

import com.van.demo.annotation.Na;
import com.van.demo.dao.entity.Name;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentNameMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Name.class)
                && parameter.hasParameterAnnotation(Na.class);
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Name name = (Name) webRequest.getAttribute("Name", RequestAttributes.SCOPE_REQUEST);
        if (name != null) {
            return name;
        }
        throw new Exception("获取name异常");
    }
}
