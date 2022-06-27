package com.lchao.config;

import com.alibaba.fastjson.JSON;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.common.Token;
import com.lchao.common.UserDetails;
import com.lchao.service.ITokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${token.ut}")
    private String UT;
    @Autowired
    private ITokenService iTokenService;

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
        String tokenKey = request.getHeader("Auth");
        if (StringUtils.isBlank(tokenKey)){
            return Write(response,"请登录后访问！");
        }
        Token token = iTokenService.getTokenByType(tokenKey, login.userType());
        if (token == null){
            return Write(response,"请登录后访问！");
        }
        UserDetails userAttribute = new UserDetails();
        userAttribute.setId(token.getId());
        userAttribute.setUserType(token.getUserType());
        request.setAttribute("userDetails", userAttribute);
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
