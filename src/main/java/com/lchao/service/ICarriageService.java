package com.lchao.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.entity.Carriage;

public interface ICarriageService extends IService<Carriage> {
    /**
     * 添加车厢
     *
     * @param jsonObject {carriageNumber:001,sizeLeft:2,sizeRight:2,sizeRow:10,seatNumber:40,level:1,adapter:1}
     * @return result
     */
    Result addCarriage(JSONObject jsonObject);

    /**
     * 获取车厢列表
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return result
     */
    Result getCarriageList(Integer pageNum, Integer pageSize);

    /**
     * 获取车厢详情
     *
     * @param id 车厢id
     * @return result
     */
    Result getCarriageInfo(Integer id);

    /**
     * 编辑车厢
     *
     * @param jsonObject {carriageNumber:001,priceBase:12,sizeLeft:2,sizeRight:2,sizeRow:10,seatNumber:40,level:1,adapter:1}
     * @return result
     */
    Result editCarriage(JSONObject jsonObject);

    /**
     * 删除车厢
     *
     * @param id 车厢id
     * @return result
     */
    Result deleteCarriage(Integer id);

    /**
     * 获取车厢列表
     *
     * @return result
     */
    Result getCarriageListOnTrain();
}
