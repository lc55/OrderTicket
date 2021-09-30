package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.entity.SoldTicket;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SoldTicketMapper extends BaseMapper<SoldTicket> {
    List<Map<String, Object>> getSoldTicket(Integer trainId, Integer curValue, String startDate);
}
