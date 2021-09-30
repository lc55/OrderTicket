package com.lchao.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lchao.common.Token;
import com.lchao.enums.TokenType;
import com.lchao.service.ITokenService;
import com.lchao.util.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class ITokenServiceImpl implements ITokenService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String UT = "UT:";
    private String TK = "TK:";

    @Override
    public String addToken(Token token) {
        String UT = this.UT;
        String TK = this.TK;
        if (token.getTokenType().equals(TokenType.admin.getType())) {
            UT = "admin" + UT;
            TK = "admin" + TK;
        }
        if (token.getTokenType().equals(TokenType.user.getType())) {
            UT = "user" + UT;
            TK = "user" + TK;
        }
        String tokenKey = KeyGenerator.generatorToken(token.getUserId());
        Object oldKey = redisTemplate.opsForHash().get(UT + token.getUserId(), token.getTokenType().toString());
        if (oldKey != null) {
            Boolean has = redisTemplate.hasKey(TK + oldKey);
            if (has != null && has) {
                return oldKey.toString();
            }
            redisTemplate.delete(TK + oldKey);
        }
        token.setToken(tokenKey);
        redisTemplate.opsForHash().put(UT + token.getUserId(), token.getTokenType().toString(), tokenKey);
        redisTemplate.opsForValue().set(TK + tokenKey, JSONObject.toJSONString(token), 30, TimeUnit.SECONDS);
        return tokenKey;
    }

    @Override
    public Token checkToken(String token, TokenType tokenType) {
        String UT = this.UT;
        String TK = this.TK;
        if (tokenType.getType() == TokenType.admin.getType()) {
            UT = "admin" + UT;
            TK = "admin" + TK;
        }
        if (tokenType.getType() == TokenType.user.getType()) {
            UT = "user" + UT;
            TK = "user" + TK;
        }
        Boolean has = redisTemplate.hasKey(TK + token);
        if (has != null && has) {
            String json = redisTemplate.opsForValue().get(TK + token);
            return JSONObject.parseObject(json, Token.class);
        }
        return null;
    }

    @Override
    public void deleteTokenByUserId(Integer userId) {
        Boolean has=redisTemplate.hasKey(UT+userId);
        if (has!=null&&has) {
            Map<Object, Object> map = redisTemplate.opsForHash().entries(UT + userId);
            if (map.size() > 0) {
                Set<String> tokens = new HashSet<>(map.size());
                map.forEach((k, v) -> tokens.add(TK+v.toString()));
                redisTemplate.delete(tokens);
            }
        }
    }

    @Override
    public void deleteTokenByUserId(Integer userId, TokenType tokenType) {

    }
}
