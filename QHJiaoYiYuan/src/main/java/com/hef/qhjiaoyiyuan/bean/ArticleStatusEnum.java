package com.hef.qhjiaoyiyuan.bean;

/**
 * @Date 2020/3/16
 * @Author lifei
 */
public enum  ArticleStatusEnum {

    ISSUE(1,"发布"),
    CONSOLE(0, "暂存"),
    DELETE(2,"删除");

    private int status;
    private String desc;

    ArticleStatusEnum(int status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
