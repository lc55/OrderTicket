package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.pojo.Line;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LineMapper extends BaseMapper<Line> {
    Integer getCurValue(Integer trainId, Integer startId, Integer endId);
}
