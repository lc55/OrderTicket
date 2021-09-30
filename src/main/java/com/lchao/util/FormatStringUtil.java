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
     * 用字符替换字符串中间的字符
     *
     * @param str    需要格式化的字符串
     * @param start  需要保留的前几位
     * @param end    需要保留的后几位
     * @param symbol 用于替换的字符
     * @return
     */
    public static String replaceStrBySymbol(String str, Integer start, Integer end, String symbol) {
        return replaceStrBySymbol(str,start,end,symbol,1);
    }

    /**
     * 用字符替换字符串中间的字符
     *
     * @param str    需要格式化的字符串
     * @param start  需要保留的前几位
     * @param end    需要保留的后几位
     * @param symbol 用于替换的字符
     * @param type 1每一位替换成symbol,2所有替换成symbol
     * @return
     */
    public static String replaceStrBySymbol(String str, int start, int end, String symbol,int type) {
        if(StringUtils.isBlank(str)){
            return null;
        }
        if(str.length()<=(start+end)){
            return str;
        }
        int replaceSum = str.length() - (start + end);
        StringBuilder replace = new StringBuilder("");
        if(type==1){
            for (int i = 0; i < replaceSum; i++) {
                replace.append(symbol);
            }
        }else if(type==2){
            replace.append(symbol);
        }
        //前start位后end位
        return str.substring(0, start) + replace + str.substring(str.length() - end);
    }

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
