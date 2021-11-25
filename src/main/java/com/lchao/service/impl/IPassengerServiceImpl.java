package com.lchao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.entity.Passenger;
import com.lchao.mapper.PassengerMapper;
import com.lchao.service.IPassengerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements IPassengerService {
    @Override
    public Result getPassengerListByUserId(Integer id) {
        if (id == null) {
            return Result.Error("参数为空！");
        }
        List<Passenger> passengerList = list(new QueryWrapper<Passenger>().eq("user_id", id));
        return new Result(passengerList);
    }

    @Override
    public Result addPassenger(JSONObject jsonObject) {

        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        String name = jsonObject.getString("name");
        String phone = jsonObject.getString("phone");
        String idCard = jsonObject.getString("idCard");
        Integer id = jsonObject.getInteger("id");
        if (id == null || StringUtils.isBlank(name) || StringUtils.isBlank(phone) || StringUtils.isBlank(idCard)) {
            return Result.Error("参数存在空值！");
        }
        Passenger one = getOne(new QueryWrapper<Passenger>().eq("passenger_phone", phone).eq("passenger_card", idCard));
        if (one != null) {
            return Result.Error("手机号或身份证已经存在了");
        }
        Passenger passenger = new Passenger();
        passenger.setUserId(id);
        passenger.setPassengerName(name);
        passenger.setPassengerPhone(phone);
        passenger.setPassengerCard(idCard);
        boolean save = save(passenger);
        if (!save) {
            return Result.Error("添加失败！");
        }
        return Result.OK();
    }

    @Override
    public Result editPassenger(JSONObject jsonObject) {

        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        Integer id = jsonObject.getInteger("id");
        String name = jsonObject.getString("name");
        String idCard = jsonObject.getString("idCard");
        String phone = jsonObject.getString("phone");
        if (id == null || StringUtils.isBlank(name) || StringUtils.isBlank(idCard) || StringUtils.isBlank(phone)) {
            return Result.Error("参数存在空值！");
        }
        Passenger passenger = new Passenger();
        passenger.setId(id);
        passenger.setPassengerName(name);
        passenger.setPassengerPhone(phone);
        passenger.setPassengerCard(idCard);
        boolean b = updateById(passenger);
        if (!b) {
            return Result.Error("编辑失败！");
        }
        return Result.OK();
    }

    @Override
    public Result deletePassenger(Integer id) {

        if (id == null) {
            return Result.Error("参数存在空值");
        }
        boolean b = removeById(id);
        if (!b) {
            return Result.Error("删除失败！");
        }
        return Result.OK();
    }
}
