package com.lchao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_line")
public class Line {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("train_id")
    private Integer trainId;

    @TableField("site_id")
    private Integer siteId;

    @TableField("time_consume")
    private String timeConsume;

    @TableField("site_order")
    private Integer siteOrder;

    @TableField("site_value")
    private Integer siteValue;

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

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getTimeConsume() {
        return timeConsume;
    }

    public void setTimeConsume(String timeConsume) {
        this.timeConsume = timeConsume;
    }

    public Integer getSiteOrder() {
        return siteOrder;
    }

    public void setSiteOrder(Integer siteOrder) {
        this.siteOrder = siteOrder;
    }

    public Integer getSiteValue() {
        return siteValue;
    }

    public void setSiteValue(Integer siteValue) {
        this.siteValue = siteValue;
    }
}
