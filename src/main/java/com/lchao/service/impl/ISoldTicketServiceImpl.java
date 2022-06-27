package com.lchao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.pojo.SoldTicket;
import com.lchao.mapper.SoldTicketMapper;
import com.lchao.service.ISoldTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ISoldTicketServiceImpl extends ServiceImpl<SoldTicketMapper, SoldTicket> implements ISoldTicketService {

    @Autowired
    private SoldTicketMapper soldTicketMapper;

    @Override
    public List<Map<String, Object>> getSoldTicket(Integer trainId, Integer curValue, String startDate) {

        return soldTicketMapper.getSoldTicket(trainId, curValue, startDate);
    }
}
