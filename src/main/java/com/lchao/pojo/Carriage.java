package com.lchao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_carriage")
public class Carriage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("carriage_number")
    private String carriageNumber;

    @TableField("price_base")
    private Long priceBase;

    @TableField("left_seat")
    private Integer leftSeat;

    @TableField("right_seat")
    private Integer rightSeat;

    @TableField("row_seat")
    private Integer rowSeat;

    @TableField("seat_count")
    private Integer seatCount;

    @TableField("level_car")
    private Integer levelCar;

    @TableField("adapt_model")
    private Integer adaptModel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(String carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public Long getPriceBase() {
        return priceBase;
    }

    public void setPriceBase(Long priceBase) {
        this.priceBase = priceBase;
    }

    public Integer getLeftSeat() {
        return leftSeat;
    }

    public void setLeftSeat(Integer leftSeat) {
        this.leftSeat = leftSeat;
    }

    public Integer getRightSeat() {
        return rightSeat;
    }

    public void setRightSeat(Integer rightSeat) {
        this.rightSeat = rightSeat;
    }

    public Integer getRowSeat() {
        return rowSeat;
    }

    public void setRowSeat(Integer rowSeat) {
        this.rowSeat = rowSeat;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getLevelCar() {
        return levelCar;
    }

    public void setLevelCar(Integer levelCar) {
        this.levelCar = levelCar;
    }

    public Integer getAdaptModel() {
        return adaptModel;
    }

    public void setAdaptModel(Integer adaptModel) {
        this.adaptModel = adaptModel;
    }
}
