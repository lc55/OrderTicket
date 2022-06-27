package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.pojo.TrainCarriage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TrainCarriageMapper extends BaseMapper<TrainCarriage> {
}
