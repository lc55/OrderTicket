package com.lchao.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.entity.Passenger;

public interface IPassengerService extends IService<Passenger> {
    /**
     * 根据userId 获取乘客列表
     *
     * @param id id
     * @return result
     */
    Result getPassengerListByUserId(Integer id);

    /**
     * 添加乘客
     *
     * @param jsonObject {phone:123,name:张三,idCard:510888888,id:1}
     * @return result
     */
    Result addPassenger(JSONObject jsonObject);

    /**
     * 编辑乘客
     *
     * @param jsonObject {id:i,phone:123,idCard:51088,name:zs}
     * @return result
     */
    Result editPassenger(JSONObject jsonObject);

    /**
     * 删除乘客
     *
     * @param id id
     * @return result
     */
    Result deletePassenger(Integer id);
}
