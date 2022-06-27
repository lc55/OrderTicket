package com.lchao.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.enums.SysConfigKey;
import com.lchao.mapper.SysConfigMapper;
import com.lchao.pojo.SysConfig;
import com.lchao.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ISysConfigServiceImpl extends ServiceImpl<SysConfigMapper,SysConfig> implements ISysConfigService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${spring.redis.sys-config}")
    private String sysConfigRedisKey;

    @Override
    public <T> T getSysValue(SysConfigKey sysConfigKey, Class<T> clazz) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        // 查询缓存
        String itemSysKey = sysConfigKey.getSysKey();
        Object sysValueObj = opsForHash.get(sysConfigRedisKey, itemSysKey);
        if (sysValueObj == null){
            // 缓存中没有，查询数据库
            sysValueObj = this.getObj(new QueryWrapper<SysConfig>().select("sys_value").eq("sys_key", itemSysKey), o -> o);
            // 放入缓存
            opsForHash.put(sysConfigRedisKey,itemSysKey,sysValueObj);
        }
        return JSON.parseObject(sysValueObj.toString(),clazz);
    }
}
