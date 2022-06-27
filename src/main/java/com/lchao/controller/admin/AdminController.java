package com.lchao.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.CurUser;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.common.UserDetails;
import com.lchao.enums.UserType;
import com.lchao.service.IAdminService;
import com.lchao.vo.AdminLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/manage")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @PostMapping("/login")
    public Result login(@RequestBody AdminLoginVo adminLoginVo) {
        // json {phone:123,password:adf}
        return iAdminService.login(adminLoginVo);
    }

    @Login(userType = UserType.admin)
    @GetMapping("/logout")
    public Result logout(@CurUser UserDetails userDetails) {
        return iAdminService.logout(userDetails);
    }

    @Login(userType = UserType.admin)
    @GetMapping("/list")
    public Result adminList(Integer pageNum, Integer pageSize) {
        return iAdminService.adminList(pageNum, pageSize);
    }

    @Login(userType = UserType.admin)
    @PostMapping("/add")
    public Result addAdmin(@RequestBody JSONObject jsonObject,@CurUser UserDetails userDetails) {
        // {phone:131111,name:zs,password:123,state:1,id:编辑时传}
        return iAdminService.addOrUpdateAdmin(jsonObject,userDetails);
    }

    @Login(userType = UserType.admin)
    @GetMapping("/info")
    public Result adminInfo(Integer id) {
        return iAdminService.adminInfo(id);
    }

    @Login(userType = UserType.admin)
    @PostMapping("/edit")
    public Result updateAdmin(@RequestBody JSONObject jsonObject,UserDetails userDetails) {
        return iAdminService.addOrUpdateAdmin(jsonObject,userDetails);
    }
}
