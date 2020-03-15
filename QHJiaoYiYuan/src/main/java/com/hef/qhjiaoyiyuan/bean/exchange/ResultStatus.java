package com.hef.qhjiaoyiyuan.bean.exchange;

public enum  ResultStatus {

    SUCCESS(0, "SUCCESS"),FAIL(1, "FAIL");


    private int status;
    private String info;

    ResultStatus(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}
