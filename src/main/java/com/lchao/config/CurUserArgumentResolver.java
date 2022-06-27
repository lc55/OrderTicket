package com.lchao.config;

import com.lchao.ann.CurUser;
import com.lchao.common.UserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义请求参数解析器
 */
public class CurUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 只有加了 @CurUser 注解，并且参数类型是 UserDetails 才做处理
        CurUser ann = methodParameter.getParameterAnnotation(CurUser.class);
        Class<?> parameterType = methodParameter.getParameterType();
        return ann != null && UserDetails.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null){
            return null;
        }
        return request.getAttribute("userDetails");
    }
}
