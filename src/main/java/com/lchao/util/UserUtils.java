package com.lchao.util;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {
    /**
     * 获取用户i
     */
    public static Integer getUserId(HttpServletRequest request) {
        Object obj = request.getAttribute("id");
        if (obj != null) {
            return Integer.valueOf(obj.toString());
        }
        return null;
    }

    /**
     * 获取adminId
     */
    public static Integer getAdminId(HttpServletRequest request) {
        Object obj = request.getAttribute("id");
        if (obj != null) {
            return Integer.valueOf(obj.toString());
        }
        return null;
    }
}
