package com.lchao.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.enums.AddOrUpdate;
import com.lchao.service.IAdminService;
import com.lchao.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/manage")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject jsonObject){
        // json {phone:123,password:adf}
        return iAdminService.login(jsonObject);
    }

    @Login(userType = 2)
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        Integer userId = UserUtils.getUserId(request);
        return iAdminService.logout(userId);
    }

    @Login(userType = 2)
    @GetMapping("/list")
    public Result adminList(Integer pageNum,Integer pageSize){
        return iAdminService.adminList(pageNum,pageSize);
    }

    @Login(userType = 2)
    @PostMapping("/add")
    public Result addAdmin(@RequestBody JSONObject jsonObject){
        // {phone:131111,name:zs,password:123,state:1,id:编辑时传}
        return iAdminService.addOrUpdateAdmin(jsonObject);
    }

    @Login(userType = 2)
    @GetMapping("/info")
    public Result adminInfo(Integer id){
        return iAdminService.adminInfo(id);
    }

    @Login(userType = 2)
    @PostMapping("/edit")
    public Result updateAdmin(@RequestBody JSONObject jsonObject){
        return iAdminService.addOrUpdateAdmin(jsonObject);
    }
}
