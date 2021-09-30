package com.lchao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.entity.Seat;
import com.lchao.mapper.SeatMapper;
import com.lchao.service.ISeatService;
import org.springframework.stereotype.Service;

@Service
public class ISeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements ISeatService {
}
