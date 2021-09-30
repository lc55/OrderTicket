package com.lchao.config;

import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.Permiss;
import com.lchao.common.Result;
import com.lchao.common.Token;
import com.lchao.service.ITokenService;
import com.lchao.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenService iTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String origin = request.getHeader("Origin");
        String h = request.getHeader("Accept-Language");
        response.setHeader("Access-Control-Allow-Origin", StringUtils.isEmpty(origin) ? "*" : origin);
        response.setHeader("Access-Control-Allow-Headers", "Accept-Language");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if (h != null && !h.isEmpty()) {
            String[] hs = h.split(";");
            if (hs.length > 2) {
                Map<String, String> map = new HashMap<>();
                map.put("Accept-Language", hs[2]);
                switch (hs[1]) {
                    case "1":
                        map.put("Content-Type", "application/json");
                        break;
                    case "2":
                        map.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                        break;
                    case "3":
                        map.put("Content-Type", "text/plain");
                        break;
                    default:
                        break;
                }
                if (!"0".equals(hs[0])) {
                    map.put("Authorization", hs[0]);
                }
                RequestUtil.addHeader(request, map);
            }
            String option = request.getMethod();
            if ("OPTIONS".equals(option)) {
                response.setStatus(200);
                return false;
            }
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Permiss permiss = method.getAnnotation(Permiss.class);
        if (permiss != null) {
            String token = request.getHeader("Authorization");
            if (token == null || token.isEmpty()) {
                return Write(response);
            }
            Token tokenObj = iTokenService.checkToken(token, permiss.type());
            if (tokenObj == null) {
                return Write(response);
            }
            request.setAttribute("id", tokenObj.getUserId());
        }
        return true;
    }

    private boolean Write(HttpServletResponse response) throws IOException {
//        if (isDev){
//            return true;
//        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        Result result = new Result();
        result.code = 110;
        result.msg = "请登录后访问";//"该接口已被禁用,请求资源不存在";
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
        return false;
    }
}
