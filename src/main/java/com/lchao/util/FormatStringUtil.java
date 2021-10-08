package com.lchao.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cxy
 * @Date: 2020/10/27 17:17
 * @Description:
 */
public class FormatStringUtil {

    /**
     * 将ids转为List<Integer>集合
     *
     * @param str    用symbol参数的符号隔开的字符串
     * @param symbol 符号，如 ","
     * @return 返回为空表示转换失败
     */
    public static List<Integer> idsToList(String str, String symbol) {
        ArrayList<Integer> list = new ArrayList<>();
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] split = str.trim().split(symbol);
        if (split.length == 0) {
            return list;
        }
        try {
            for (String s : split) {
                Integer id = Integer.parseInt(s);
                list.add(id);
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        } finally {
            if (list != null && list.size() == 0) {
                list = null;
            }
        }
        return list;
    }

}
