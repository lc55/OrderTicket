package com.lchao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lchao.common.Result;
import com.lchao.common.Token;
import com.lchao.enums.SysConfigKey;
import com.lchao.enums.UserType;
import com.lchao.service.ISysConfigService;
import com.lchao.service.ITokenService;
import com.lchao.util.DateUtils;
import com.lchao.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ITokenServiceImpl implements ITokenService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ISysConfigService iSysConfigService;

    @Value("${token.ut}")
    private String UT;
    @Value("${token.tk}")
    private String TK;
    @Value("${token.adminTK}")
    private String AdminTK;

    @Override
    public Result addToken(Token token) {
        if (token == null) {
            return Result.Error("token参数为空！");
        }
        String UT = this.UT;
        String TK = this.TK;
        if (token.getUserType() == UserType.admin.getType()){
            UT = "admin" + UT;
            TK = "admin" + TK;
        }
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        Long tokenExpireTime = iSysConfigService.getSysValue(SysConfigKey.Token_Expire_Time, Long.class);
        // 旧的token
        String oldTokenKey = opsForValue.get(UT + token.getId());
        if (StringUtils.isNotBlank(oldTokenKey)) {
            // 有token，修改过期时间
            redisTemplate.expire(UT + token.getId(), tokenExpireTime, TimeUnit.MINUTES);
            redisTemplate.expire(TK + oldTokenKey, tokenExpireTime, TimeUnit.MINUTES);
            token.setToken(oldTokenKey);
            token.setExpireTime(DateUtils.getAfterMinuteTimeStamp(tokenExpireTime));
        } else {
            // 没有token，生成token
            String newTokenValue = UUIDUtil.getUUID();
            token.setToken(newTokenValue);
            token.setExpireTime(DateUtils.getAfterMinuteTimeStamp(tokenExpireTime));
            opsForValue.set(UT+token.getId(),newTokenValue,tokenExpireTime,TimeUnit.MINUTES);
            opsForValue.set(TK+newTokenValue, JSONObject.toJSONString(token),tokenExpireTime,TimeUnit.MINUTES);
        }
        return Result.OK(token);
    }

    @Override
    public void deleteTokenByUserId(Integer userId, UserType tokenType) {
        String TK = this.TK;
        String UT = this.UT;
        if (tokenType == UserType.admin) {
            TK = "admin" + TK;
            UT = "admin" + UT;
        }
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String tokenKey = opsForValue.get(UT + userId);
        redisTemplate.delete(UT + userId);
        redisTemplate.delete(TK + tokenKey);
    }

    @Override
    public Token getTokenByType(String tokenKey, UserType userType) {
        String TK = this.TK;
        if (userType == UserType.admin){
            TK = "admin" + TK;
        }
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String tokenStr = opsForValue.get(TK + tokenKey);
        if (StringUtils.isNotBlank(tokenStr)){
            return JSONObject.toJavaObject(JSONObject.parseObject(tokenStr),Token.class);
        }
        return null;
    }
}
