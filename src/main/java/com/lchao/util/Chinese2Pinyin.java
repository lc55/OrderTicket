package com.lchao.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class Chinese2Pinyin {
    /**
     * 将两个城市转为简拼
     *
     */
    public static String getJP(String start, String end) {
        String s1 = convert(start);
        String s2 = convert(end);
        if (s1.compareTo(s2) < 0){
            return s1+"-"+s2;
        }else{
            return s2+"-"+s1;
        }
    }

    public static String convert(String str) {
        StringBuilder convert = new StringBuilder();
        if (str == null || str.length() == 0) {
            return convert.toString();
        }
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert.append(pinyinArray[0].charAt(0));
            } else {
                convert.append(word);
            }
        }
        return convert.toString().toUpperCase();
    }

    public static void main(String[] args) {
        String jp = getJP("佛山", "成都");
        System.out.println(jp);
    }
}

