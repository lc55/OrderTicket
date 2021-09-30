package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.entity.Line;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface LineMapper extends BaseMapper<Line> {
    Integer getCurValue(Integer trainId, Integer startId,Integer endId);
}
