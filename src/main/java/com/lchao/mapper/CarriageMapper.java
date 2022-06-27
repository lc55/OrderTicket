package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.pojo.Carriage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CarriageMapper extends BaseMapper<Carriage> {
}
