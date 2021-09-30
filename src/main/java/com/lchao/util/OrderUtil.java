package com.lchao.util;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderUtil {

    public static String getOrderNumber() {
        StringBuilder builder = new StringBuilder();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        builder.append(time).append(RandomUtils.nextInt());
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getOrderNumber());
    }
}
