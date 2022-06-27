package com.lchao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.pojo.SoldTicket;

import java.util.List;
import java.util.Map;

public interface ISoldTicketService extends IService<SoldTicket> {
    List<Map<String, Object>> getSoldTicket(Integer trainId, Integer curValue, String startDate);
}
