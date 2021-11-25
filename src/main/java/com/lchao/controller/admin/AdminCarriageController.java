package com.lchao.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.service.ICarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/carriage")
public class AdminCarriageController {

    @Autowired
    private ICarriageService iCarriageService;

    @Login(userType = 2)
    @GetMapping("/list")
    public Result getCarriageList(Integer pageNum, Integer pageSize) {
        return iCarriageService.getCarriageList(pageNum, pageSize);
    }

    @Login(userType = 2)
    @PostMapping("/add")
    public Result addCarriage(@RequestBody JSONObject jsonObject) {
        // {carriageNumber:001,sizeLeft:2,sizeRight:2,sizeRow:10,seatNumber:40,level:1,adapter:1}
        return iCarriageService.addCarriage(jsonObject);
    }

    @Login(userType = 2)
    @GetMapping("/info")
    public Result getCarriageInfo(Integer id) {
        return iCarriageService.getCarriageInfo(id);
    }

    @Login(userType = 2)
    @PostMapping("/edit")
    public Result editCarriage(@RequestBody JSONObject jsonObject) {
        //{id:1,priceBase:12,sizeLeft:2,sizeRight:2,sizeRow:10,seatNumber:40,level:1,adapter:1}
        return iCarriageService.editCarriage(jsonObject);
    }

    @Login(userType = 2)
    @GetMapping("/delete")
    public Result deleteCarriage(Integer id) {
        return iCarriageService.deleteCarriage(id);
    }
}
