package com.lchao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("order_number")
    private String orderNumber;

    @TableField("order_time")
    private Long orderTime;

    @TableField("train_id")
    private Integer trainId;


    @TableField("start_time")
    private String startTime;

    @TableField("start_id")
    private Integer startId;

    @TableField("end_id")
    private Integer endId;


    @TableField("end_time")
    private String endTime;

    @TableField("level_car")
    private Integer levelCar;

    @TableField("price")
    private Double price;

    @TableField("order_status")
    private Integer orderStatus;

    @TableField("passenger_id")
    private Integer passengerId;

    @TableField("seat_id")
    private Integer seatId;

    @TableField("carriage_order")
    private Integer carriageOrder;

    @TableField("start_date")
    private String startDate;

    @TableField("end_date")
    private String endDate;

    @TableField("user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }


    public Integer getLevelCar() {
        return levelCar;
    }

    public void setLevelCar(Integer levelCar) {
        this.levelCar = levelCar;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getCarriageOrder() {
        return carriageOrder;
    }

    public void setCarriageOrder(Integer carriageId) {
        this.carriageOrder = carriageId;
    }
}

