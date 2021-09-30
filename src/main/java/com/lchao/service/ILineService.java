package com.lchao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.entity.Line;

import java.util.List;

public interface ILineService extends IService<Line> {

    /**
     * 根据车次和站点获取当前车票的值
     * @param trainId 车次id
     * @param startId 起点
     * @param endId 终点
     * @return Integer
     */
    Integer getCurValue(Integer trainId, Integer startId,Integer endId);
}
