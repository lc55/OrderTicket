package com.lchao.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.CurUser;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.common.UserDetails;
import com.lchao.enums.UserType;
import com.lchao.service.IPassengerService;
import com.lchao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IPassengerService iPassengerService;

    @PostMapping("/register")
    public Result register(@RequestBody JSONObject jsonObject) {
        // json {phone:123,password:adf}
        return iUserService.register(jsonObject);
    }

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject jsonObject) {
        // json {phone:123,password:adf}
        return iUserService.login(jsonObject);
    }

    @Login(userType = UserType.user)
    @GetMapping("/logout")
    public Result logout(@CurUser UserDetails userDetails) {
        return iUserService.logout(userDetails);
    }

    @Login(userType = UserType.user)
    @GetMapping("/getPassengerList")
    public Result getPassengerList(Integer id) {
        return iPassengerService.getPassengerListByUserId(id);
    }

    @Login(userType = UserType.user)
    @PostMapping("/addPassenger")
    public Result addPassenger(@RequestBody JSONObject jsonObject) {
        // json {phone:123,name:张三,idCard:510888888,id:1}
        return iPassengerService.addPassenger(jsonObject);
    }

    @Login(userType = UserType.user)
    @PostMapping("/editPassenger")
    public Result editPassenger(@RequestBody JSONObject jsonObject) {
        // json {id:i,phone:123,idCard:51088,name:zs}
        return iPassengerService.editPassenger(jsonObject);
    }

    @Login(userType = UserType.user)
    @GetMapping("/deletePassenger")
    public Result deletePassenger(Integer id) {
        return iPassengerService.deletePassenger(id);
    }
}
