package com.lchao.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.entity.User;

public interface IUserService extends IService<User> {
    /**
     * 登录
     *
     * @param jsonObject {phone:123,password:adf}
     * @return result
     */
    Result login(JSONObject jsonObject);

    /**
     * 注册
     *
     * @param jsonObject {phone:123,password:adf}
     * @return result
     */
    Result register(JSONObject jsonObject);

    Result logout(Integer id);
}
