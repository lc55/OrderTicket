package com.lchao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_sys_config")
public class SysConfig {

    @TableId(type = IdType.AUTO,value = "sys_config_id")
    private Integer sysConfigId;

    @TableField("sys_key")
    private String sysKey;

    @TableField("sys_value")
    private String sysValue;

    @TableField("description")
    private String description;

    public Integer getSysConfigId() {
        return sysConfigId;
    }

    public void setSysConfigId(Integer sysConfigId) {
        this.sysConfigId = sysConfigId;
    }

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
