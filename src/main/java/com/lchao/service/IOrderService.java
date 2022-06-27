package com.lchao.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.pojo.Order;

import java.util.List;

public interface IOrderService extends IService<Order> {

    /**
     * 获取订单列表
     *
     * @param pageNum       当前页
     * @param pageSize      页面大小
     * @param trainNumber   车次
     * @param passengerName 乘客姓名
     * @param idCard        身份证
     * @param orderStatus   订单状态
     * @param seatType      座位类别
     * @return result
     */
    Result getList(Integer pageNum, Integer pageSize, String trainNumber, String passengerName, String idCard, Integer orderStatus, Integer seatType);

    /**
     * 下订单
     *
     * @param jsonObject {id:1,startDate:2021-09-29,trainNumber:G2021,startId:1,endId:3,startTime:08:30,endTime:09:19,passengerList:{{id:1,levelCar:2},{}}}
     * @return result
     */
    Result toOrder(JSONObject jsonObject);

    /**
     * 取消订单
     *
     * @param orderIdList 订单id集合
     * @return result
     */
    Result cancelOrder(List<Integer> orderIdList);

    /**
     * 支付
     *
     * @param orderIdList 订单id集合
     * @return result
     */
    Result payment(List<Integer> orderIdList);

    /**
     * 获取我的订单列表
     *
     * @param userId     userId
     * @param pageNum    当前页
     * @param pageSize   页面大小
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param type       类型
     * @param orderState 订单状态
     * @return result
     */
    Result getMyOrderList(Integer userId, Integer pageNum, Integer pageSize, String startDate, String endDate, Integer type, Integer orderState);
}
