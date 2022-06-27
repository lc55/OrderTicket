package com.lchao.enums;

public enum SysConfigKey {
    Token_Expire_Time("token_expire_time"),
    ;

    private final String sysKey;
    SysConfigKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysKey() {
        return sysKey;
    }
}
