package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lchao.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface OrderMapper extends BaseMapper<Order> {
    IPage<Map<String, Object>> getList(IPage<Map<String, Object>> page, String trainNumber, String passengerName, String idCard, Integer orderStatus, Integer seatType);

    IPage<Map<String, Object>> getMyOrderList(IPage<Map<String, Object>> page, Integer userId, String startDate, String endDate, Integer type, Integer orderState);
}
