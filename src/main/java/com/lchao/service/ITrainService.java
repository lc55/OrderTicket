package com.lchao.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.entity.Train;

import java.util.List;
import java.util.Map;

public interface ITrainService extends IService<Train> {
    /**
     * 添加/更新车次
     * @param jsonObject {trainNumber:G2121,isReturn:1,type:1,startTime:2910290,trainCount:1,siteList:{{siteId:1,useTime:21},{}},carriageList:{{carriageId:1},{}}}
     * @return result
     */
    Result addOrUpdateTrain(JSONObject jsonObject);

    /**
     * 查询车次列表
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return result
     */
    Result getTrainList(Integer pageNum, Integer pageSize);

    /**
     * 获取车次详情
     * @param id id
     * @return result
     */
    Result getTrainInfo(Integer id);

    /**
     * 获取车次的基础信息
     * @param trainIds 车次id集合
     * @return result
     */
    List<Map<String, Object>> getBaseTrainInfoList(List<Integer> trainIds);
}
