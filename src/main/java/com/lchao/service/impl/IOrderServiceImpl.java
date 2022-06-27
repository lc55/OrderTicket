package com.lchao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.pojo.Order;
import com.lchao.pojo.Passenger;
import com.lchao.pojo.SoldTicket;
import com.lchao.enums.OrderState;
import com.lchao.mapper.OrderMapper;
import com.lchao.service.*;
import com.lchao.util.DateUtils;
import com.lchao.util.OrderUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAllTicketService iAllTicketService;
    @Autowired
    private ILineService iLineService;
    @Autowired
    private IPassengerService iPassengerService;
    @Autowired
    private ISoldTicketService iSoldTicketService;

    @Override
    public Result getList(Integer pageNum, Integer pageSize, String trainNumber, String passengerName, String idCard, Integer orderStatus, Integer seatType) {
        if (pageNum == null || pageSize == null) {
            return Result.Error("分页参数存在空值！");
        }
        IPage<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        IPage<Map<String, Object>> mapList = orderMapper.getList(page, trainNumber, passengerName, idCard, orderStatus, seatType);
        return Result.page(mapList);
    }

    @Transactional
    @Override
    public Result toOrder(JSONObject jsonObject) {
        if (jsonObject == null) {
            return Result.Error("jsonObject 为空！");
        }
        Integer id = jsonObject.getInteger("id");
        Integer userId = jsonObject.getInteger("userId");
        Integer startId = jsonObject.getInteger("startId");
        Integer endId = jsonObject.getInteger("endId");
        String startDate = jsonObject.getString("startDate");
        String endDate = jsonObject.getString("endDate");
        String trainNumber = jsonObject.getString("trainNumber");
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        JSONArray passengerList = jsonObject.getJSONArray("passengerList");
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate) || StringUtils.isBlank(trainNumber) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime) || userId == null || id == null || startId == null || endId == null) {
            return Result.Error("参数存在空值！");
        }
        if (passengerList == null || passengerList.isEmpty()) {
            return Result.Error("乘车人为空！");
        }
        // 判断乘客是否能够买这趟车（可能有车票了，冲突了）
        for (Object o : passengerList) {
            String s = JSONObject.toJSONString(o);
            JSONObject json = JSONObject.parseObject(s);
            Integer passengerId = json.getInteger("id");
            Passenger passenger = iPassengerService.getOne(new QueryWrapper<Passenger>().eq("id", passengerId));
            if (passenger == null) {
                return Result.Error("乘车人不存在！");
            }
            // 判断车票时间是否冲突
            List<Map<String, Object>> mapList = listMaps(new QueryWrapper<Order>().select("order_status", "start_date", "end_date", "start_time", "end_time").eq("passenger_id", passengerId));
            if (mapList != null && !mapList.isEmpty()) {
                for (Map<String, Object> map : mapList) {
                    String sDate = MapUtils.getString(map, "startDate");
                    String sTime = MapUtils.getString(map, "startTime");
                    String eDate = MapUtils.getString(map, "endDate");
                    String eTime = MapUtils.getString(map, "endTime");
                    boolean b = DateUtils.isContainsDateTime(startDate + " " + startTime, endDate + " " + endTime, sDate + " " + sTime, eDate + " " + eTime);
                    if (b) {
                        return Result.Error("乘车人：" + passenger.getPassengerName() + "存在订单时间冲突！");
                    }
                }
            }
        }
        List<Map<String, Object>> orderInfoList = new ArrayList<>();
        // 为每个乘客占一个座位
        for (Object o : passengerList) {
            String s = JSONObject.toJSONString(o);
            JSONObject json = JSONObject.parseObject(s);
            Integer levelCar = json.getInteger("levelCar");
            Integer passengerId = json.getInteger("id");
            Double price = json.getDouble("price");
            Passenger passenger = iPassengerService.getOne(new QueryWrapper<Passenger>().eq("id", passengerId));
            if (passenger == null) {
                return Result.Error("乘客不存在！");
            }
            // 当前车票值
            Integer curValue = iLineService.getCurValue(id, startId, endId);
            // 查询前1张车票
            Map<String, Object> ticketMap = iAllTicketService.getMoreTicket(id, curValue, levelCar, startDate);
            ticketMap.put("passengerId", passengerId);
            ticketMap.put("name", passenger.getPassengerName());
            ticketMap.put("idCard", passenger.getPassengerCard());
            orderInfoList.add(ticketMap);

            // 生成订单
            Order order = new Order();
            order.setOrderNumber(OrderUtil.getOrderNumber());
            order.setOrderTime(DateUtils.getTimeStamp());
            order.setTrainId(id);
            order.setStartTime(startTime);
            order.setStartId(startId);
            order.setEndId(endId);
            order.setEndTime(endTime);
            order.setLevelCar(levelCar);
            order.setPrice(price);
            order.setOrderStatus(OrderState.unpaid.getState());
            order.setPassengerId(passengerId);
            order.setSeatId(MapUtils.getInteger(ticketMap, "seatId"));
            order.setCarriageOrder(MapUtils.getInteger(ticketMap, "carriageOrder"));
            order.setStartDate(startDate);
            order.setEndDate(endDate);
            order.setUserId(userId);
            boolean save = save(order);
            if (!save) {
                return Result.RollBackError("生成订单失败！");
            }
            ticketMap.put("orderId", order.getId());
            // 生成已购买的票
            SoldTicket soldTicket = new SoldTicket();
            soldTicket.setTrainId(id);
            soldTicket.setCarriageId(MapUtils.getInteger(ticketMap, "carriageId"));
            soldTicket.setTicketId(MapUtils.getInteger(ticketMap, "id"));
            soldTicket.setTicketValue(curValue);
            soldTicket.setEndDate(startDate);
            soldTicket.setOrderId(order.getId());
            boolean saveSoldTicket = iSoldTicketService.save(soldTicket);
            if (!saveSoldTicket) {
                return Result.RollBackError("生成车票失败！");
            }

        }
        return Result.OK(orderInfoList);
    }

    @Transactional
    @Override
    public Result cancelOrder(List<Integer> orderIdList) {

        if (orderIdList == null || orderIdList.isEmpty()) {
            return Result.Error("参数为空");
        }
        List<String> orderNumberList = new ArrayList<>();
        for (Integer orderId : orderIdList) {
            Order order = getOne(new QueryWrapper<Order>().eq("id", orderId));
            if (order == null) {
                return Result.Error("订单不存在！");
            }
            // 修改订单状态
            boolean updateOrder = update(new UpdateWrapper<Order>().eq("id", orderId).set("order_status", OrderState.cancel.getState()));
            if (!updateOrder) {
                return Result.RollBackError("修改订单状态失败！");
            }
            orderNumberList.add(order.getOrderNumber());

            // 删除已经生成的车票
            boolean remove = iSoldTicketService.remove(new QueryWrapper<SoldTicket>().eq("order_id", orderId));
            if (!remove) {
                return Result.RollBackError("退车票失败！");
            }

        }
        // 返回订单编号
        return Result.OK(orderNumberList);
    }

    @Override
    public Result payment(List<Integer> orderIdList) {

        if (orderIdList == null || orderIdList.isEmpty()) {
            return Result.Error("参数为空");
        }
        List<String> orderNumberList = new ArrayList<>();
        for (Integer orderId : orderIdList) {
            boolean update = update(new UpdateWrapper<Order>().eq("id", orderId).set("order_status", OrderState.paid.getState()));
            if (!update) {
                return Result.Error("支付失败！");
            }
            String orderNumber = getObj(new QueryWrapper<Order>().select("order_number").eq("id", orderId), Object::toString);
            orderNumberList.add(orderNumber);
        }
        // 返回订单编号

        return Result.OK(orderNumberList);
    }

    @Override
    public Result getMyOrderList(Integer userId, Integer pageNum, Integer pageSize, String startDate, String endDate, Integer type, Integer orderState) {

        if (userId == null || pageNum == null || pageSize == null) {
            return Result.Error("参数存在空值！");
        }
        IPage<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        IPage<Map<String, Object>> mapList = orderMapper.getMyOrderList(page, userId, startDate, endDate, type, orderState);
        return Result.page(mapList);
    }
}
