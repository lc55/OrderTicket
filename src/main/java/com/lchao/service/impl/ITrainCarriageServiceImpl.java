package com.lchao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.pojo.TrainCarriage;
import com.lchao.mapper.TrainCarriageMapper;
import com.lchao.service.ITrainCarriageService;
import org.springframework.stereotype.Service;

@Service
public class ITrainCarriageServiceImpl extends ServiceImpl<TrainCarriageMapper, TrainCarriage> implements ITrainCarriageService {
}
