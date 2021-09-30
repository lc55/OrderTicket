package com.lchao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_train_carriage")
public class TrainCarriage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("train_id")
    private Integer trainId;

    @TableField("carriage_id")
    private Integer carriageId;

    @TableField("carriage_order")
    private Integer carriageOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(Integer carriageId) {
        this.carriageId = carriageId;
    }

    public Integer getCarriageOrder() {
        return carriageOrder;
    }

    public void setCarriageOrder(Integer carriageOrder) {
        this.carriageOrder = carriageOrder;
    }
}
