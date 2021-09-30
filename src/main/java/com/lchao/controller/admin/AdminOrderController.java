package com.lchao.controller.admin;

import com.lchao.common.Result;
import com.lchao.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private IOrderService iOrderService;

    @GetMapping("/list")
    public Result getList(Integer pageNum, Integer pageSize,
                          @RequestParam(required = false) String trainNumber,
                          @RequestParam(required = false) String passengerName,
                          @RequestParam(required = false) String idCard,
                          @RequestParam(required = false) Integer orderStatus,
                          @RequestParam(required = false) Integer seatType) {
        return iOrderService.getList(pageNum, pageSize, trainNumber, passengerName, idCard, orderStatus, seatType);
    }
}
