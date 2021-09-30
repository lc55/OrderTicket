package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.entity.AllTicket;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface AllTicketMapper extends BaseMapper<AllTicket> {
    List<Map<String, Object>> getAllTicket(Integer trainId);

    Map<String, Object> getMoreTicket(Integer id, Integer curValue, Integer levelCar,String startDate);
}
