package com.lchao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_sold_ticket")
public class SoldTicket {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("ticket_id")
    private Integer ticketId;

    @TableField("ticket_value")
    private Integer ticketValue;

    @TableField("end_date")
    private String endDate;

    @TableField("train_id")
    private Integer trainId;

    @TableField("carriage_id")
    private Integer carriageId;

    @TableField("order_id")
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(Integer ticketValue) {
        this.ticketValue = ticketValue;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
