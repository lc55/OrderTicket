package com.lchao.ann;

import com.lchao.enums.TokenType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by isora on 2021/8/23
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permiss {
     TokenType type() default TokenType.user; //用户类型
}
