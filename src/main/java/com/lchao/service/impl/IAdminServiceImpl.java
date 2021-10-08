package com.lchao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.common.Token;
import com.lchao.entity.Admin;
import com.lchao.enums.AdminState;
import com.lchao.enums.TokenType;
import com.lchao.mapper.AdminMapper;
import com.lchao.service.IAdminService;
import com.lchao.service.ITokenService;
import com.lchao.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IAdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ITokenService iTokenService;

    @Override
    public Result login(JSONObject jsonObject) {
        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
            return Result.Error("phone/password 为空");
        }
        String md5 = MD5Util.getMD5(password);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("password", md5);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin == null) {
            return Result.Error("用户名或密码错误");
        }
        if (admin.getState().equals(AdminState.disable.getState())) {
            return Result.Error(admin.getName() + "用户被禁用了！");
        }
        // 生成token
        Token token = new Token();
        token.setUserId(admin.getId());
        token.setName(admin.getName());
        token.setPhone(admin.getPhone());
        token.setTokenType(TokenType.admin.getType());
        token.setPassword(admin.getPassword());
        String tokenKey = iTokenService.addToken(token);
        Map<String, Object> map = new HashMap<>();
        map.put("token", tokenKey);
        map.put("admin", admin);
        return new Result(map);
    }

    @Override
    public Result logout(Integer adminId) {
        iTokenService.deleteTokenByUserId(adminId,TokenType.admin);
        return Result.OK();
    }

    @Override
    public Result adminList(Integer pageNum, Integer pageSize) {

        if (pageNum == null || pageSize == null) {
            return Result.Error("分页参数存在空值！");
        }
        IPage<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        IPage<Map<String, Object>> mapIPage = pageMaps(page, new QueryWrapper<Admin>().select("id", "name", "phone", "state"));
        return Result.page(mapIPage);
    }

    @Override
    public Result addOrUpdateAdmin(JSONObject jsonObject) {

        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        String phone = jsonObject.getString("phone");
        String name = jsonObject.getString("name");
        Integer state = jsonObject.getInteger("state");
        String password = jsonObject.getString("password");
        Integer id = jsonObject.getInteger("id");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(name) || state == null || StringUtils.isBlank(password)) {
            return Result.Error("参数存在空值");
        }
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPhone(phone);
        admin.setPassword(MD5Util.getMD5(password));
        admin.setState(state);
        if (id == null) {
            boolean save = save(admin);
            if (!save) {
                return Result.Error("添加失败！");
            }
        } else {
            Admin a = getOne(new QueryWrapper<Admin>().eq("id", id));
            if (a == null) {
                return Result.Error("用户不存在！");
            } else {
                admin.setId(a.getId());
                boolean b = updateById(admin);
                if (!b) {
                    return Result.Error("更新失败！");
                }
            }
        }
        return Result.OK();
    }

    @Override
    public Result adminInfo(Integer id) {
        if (id == null) {
            return Result.Error("参数存在为空");
        }
        Admin admin = getOne(new QueryWrapper<Admin>().eq("id", id));

        return new Result(admin);
    }
}
