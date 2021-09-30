package com.lchao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_all_ticket")
public class AllTicket {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("train_id")
    private Integer trainId;

    @TableField("carriage_id")
    private Integer carriageId;

    @TableField("seat_id")
    private Integer seatId;

    @TableField("state")
    private Integer state;

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

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
