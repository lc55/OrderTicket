package com.lchao.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.enums.UserType;
import com.lchao.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/order")
public class UserOrderController {

    @Autowired
    private IOrderService iOrderService;

    @Login(userType = UserType.user)
    @PostMapping("/toOrder")
    public Result toOrder(@RequestBody JSONObject jsonObject) {
        //{id:1,startDate:2021-09-29,trainNumber:G2021,startId:1,endId:3,startTime:08:30,endTime:09:19,passengerList:{{id:1,levelCar:2},{}}}
        return iOrderService.toOrder(jsonObject);
    }

    @Login(userType = UserType.user)
    @PostMapping("/cancelOrder")
    public Result cancelOrder(@RequestBody List<Integer> orderIdList) {
        return iOrderService.cancelOrder(orderIdList);
    }

    @Login(userType = UserType.user)
    @PostMapping("/payment")
    public Result payment(@RequestBody List<Integer> orderIdList) {
        return iOrderService.payment(orderIdList);
    }

    @Login(userType = UserType.user)
    @GetMapping("/myOrderList")
    public Result myOrderList(Integer userId,
                              Integer pageNum,
                              Integer pageSize,
                              @RequestParam(required = false) String startDate,
                              @RequestParam(required = false) String endDate,
                              @RequestParam(required = false) Integer type,
                              @RequestParam(required = false) Integer orderState) {
        return iOrderService.getMyOrderList(userId, pageNum, pageSize, startDate, endDate, type, orderState);
    }
}
