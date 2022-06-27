package com.lchao.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.common.UserDetails;
import com.lchao.pojo.Admin;
import com.lchao.vo.AdminLoginVo;

public interface IAdminService extends IService<Admin> {
    /**
     * @param adminLoginVo {phone:123,password:adf}
     * @return result
     */
    Result login(AdminLoginVo adminLoginVo);

    Result logout(UserDetails userDetails);

    /**
     * 获取平台用户列表
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return result
     */
    Result adminList(Integer pageNum, Integer pageSize);

    /**
     * 添加/更新平台用户
     *
     * @param jsonObject {phone:131111,name:zs,password:123,state:1}
     * @return result
     */
    Result addOrUpdateAdmin(JSONObject jsonObject,UserDetails userDetails);

    /**
     * 获取用户详情
     *
     * @param id id
     * @return result
     */
    Result adminInfo(Integer id);
}
