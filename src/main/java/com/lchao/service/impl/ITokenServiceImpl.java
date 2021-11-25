package com.lchao.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lchao.common.Token;
import com.lchao.enums.TokenType;
import com.lchao.service.ITokenService;
import com.lchao.util.KeyGenerator;
import com.lchao.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class ITokenServiceImpl implements ITokenService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String TK = "TK:";

    @Override
    public String addToken(Token token) {
        String TK = this.TK;
        if (token.getTokenType().equals(TokenType.admin.getType())) {
            TK = "admin" + TK;
        }
        if (token.getTokenType().equals(TokenType.user.getType())) {
            TK = "user" + TK;
        }
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String tokenValue = opsForValue.get(TK + token.getUserId());
        if (StringUtils.isBlank(tokenValue)) {
            String newTokenValue = UUIDUtil.getUUID();
            opsForValue.set(TK + token.getUserId(), newTokenValue, 30, TimeUnit.MINUTES);
        } else {
            opsForValue.set(TK + token.getUserId(), tokenValue, 30, TimeUnit.MINUTES);
        }
        return opsForValue.get(TK + token.getUserId().toString());
    }

    @Override
    public void deleteTokenByUserId(Integer userId, TokenType tokenType) {
        String TK = this.TK;
        if (tokenType.getType() == TokenType.admin.getType()) {
            TK = "admin" + TK;
        }
        if (tokenType.getType() == TokenType.user.getType()) {
            TK = "user" + TK;
        }
        redisTemplate.hasKey(TK + userId);
    }
}
