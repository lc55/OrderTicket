package com.lchao.config;

import com.alibaba.fastjson.JSON;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Login login = method.getAnnotation(Login.class);
        if (login == null) {
            return true;
        }
        String token = request.getHeader("Auth");
        if (login.userType() == 1) {
            if (StringUtils.isBlank(token)) {
                return Write(response, "登录失效！");
            }
        } else if (login.userType() == 2) {
            if (StringUtils.isBlank(token)) {
                return Write(response, "登录失效！");
            }
        } else {
            return Write(response, "错误的用户类型！");
        }
        return true;
    }

    private boolean Write(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        Result result = new Result(101);
        result.msg = msg;
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
        return false;
    }
}
