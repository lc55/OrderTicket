package com.lchao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.entity.AllTicket;

import java.util.Map;

public interface IAllTicketService extends IService<AllTicket> {
    /**
     * 查询车次以及余票
     *
     * @param startId   起点
     * @param endId     终点
     * @param startDate 出发时间
     * @param types     types
     * @return result
     */
    Result searchTicket(Integer startId, Integer endId, String startDate, String types);

    /**
     * 为乘车人选一张车票
     *
     * @param id        车次id
     * @param curValue  当前票值
     * @param levelCar  类型
     * @param startDate 出发时间
     * @return result
     */
    Map<String, Object> getMoreTicket(Integer id, Integer curValue, Integer levelCar, String startDate);
}
