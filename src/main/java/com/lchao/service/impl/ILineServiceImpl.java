package com.lchao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.pojo.Line;
import com.lchao.mapper.LineMapper;
import com.lchao.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ILineServiceImpl extends ServiceImpl<LineMapper, Line> implements ILineService {

    @Autowired
    private LineMapper lineMapper;

    @Override
    public Integer getCurValue(Integer trainId, Integer startId, Integer endId) {
        return lineMapper.getCurValue(trainId, startId, endId);
    }
}
