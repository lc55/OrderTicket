package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lchao.pojo.Train;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TrainMapper extends BaseMapper<Train> {
    List<Map<String, Object>> getTrainList(IPage<Map<String, Object>> page);

    List<Map<String, Object>> getBaseTrainInfoList(List<Integer> trainIds);
}
