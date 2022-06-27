package com.lchao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.enums.SysConfigKey;
import com.lchao.pojo.SysConfig;

public interface ISysConfigService extends IService<SysConfig> {

    <T> T getSysValue(SysConfigKey sysConfigKey, Class<T> clazz);
}
