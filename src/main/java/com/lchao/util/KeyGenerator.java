package com.lchao.util;

import org.apache.commons.lang3.RandomUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * create by isora on 2021/5/12
 **/
public class KeyGenerator {

    private final static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private final static int seed = 18;

    public static String EncryptKey(Integer id) {
        StringBuilder sb = new StringBuilder();
        Integer keyindex = RandomUtils.nextInt(0, 9);
        int gene = seed - (keyindex % 10);
        int sed = gene * keyindex * (8 << gene);
        String a = Integer.toString(id + sed, 32);
        System.out.println(a + "+" + keyindex);
        int l = a.length();
        int seedl = seed - l - 2;
        if (seedl > 0) {
            for (int i = 0; i < seed; i++) {
                if (i == keyindex) {
                    sb.append(a);
                }
                if (i < seedl) {
                    Integer r = RandomUtils.nextInt(0, chars.length - 1);
                    sb.append(chars[r]);
                }
            }
        }
        char[] key = Integer.toString(l * 100 + keyindex, 32).toCharArray();
        sb.insert(0, key[1]);
        sb.insert(sb.length() - 1, key[0]);
        return sb.toString();
    }

    /**
     * 获取应用程序密钥
     *
     * @param primarKey 数据库主键
     * @return {@link String}
     *///生成8位appKey
    public static String generatorToken(Integer primarKey) {
        StringBuilder shortBuffer = new StringBuilder();
        //获取用户id进行字符串截取
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < uuid.length() - 5; i++) {
            String str = uuid.substring(i, i + 2);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        shortBuffer.append(EncryptKey(primarKey));
        return shortBuffer.toString();
    }

}
