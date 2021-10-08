package com.lchao.config;

import com.lchao.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestInterceptor implements HandlerInterceptor {

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
        return true;
    }
}
