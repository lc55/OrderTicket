package com.lchao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.common.Token;
import com.lchao.common.UserDetails;
import com.lchao.enums.UserType;
import com.lchao.mapper.UserMapper;
import com.lchao.pojo.User;
import com.lchao.service.ITokenService;
import com.lchao.service.IUserService;
import com.lchao.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final String userToken = "userToken";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ITokenService iTokenService;

    @Override
    public Result login(JSONObject jsonObject) {

        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");

        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
            return Result.Error("phone/password 为空");
        }
        String md5 = MD5Util.getMD5(password);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("password", md5);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.Error("用户名或密码错误");
        }
        // 生成token
        Token token = new Token();
        token.setId(user.getId());
        token.setPhone(user.getPhone());
        token.setUserType(UserType.user.getType());
        return iTokenService.addToken(token);
    }

    @Override
    public Result register(JSONObject jsonObject) {

        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");

        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
            return Result.Error("phone/password 为空");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        Integer count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.Error("手机号已经存在了，换个手机号试试！");
        }

        String md5 = MD5Util.getMD5(password);
        User user = new User();
        user.setPhone(phone);
        user.setPassword(md5);
        int insert = userMapper.insert(user);
        if (insert != 1) {
            return Result.Error("插入数据失败");
        }
        return Result.OK("注册成功！");
    }

    @Override
    public Result logout(UserDetails userDetails) {
        Integer id = userDetails.getId();
        iTokenService.deleteTokenByUserId(id, UserType.user);
        return Result.OK("成功退出！");
    }
}
