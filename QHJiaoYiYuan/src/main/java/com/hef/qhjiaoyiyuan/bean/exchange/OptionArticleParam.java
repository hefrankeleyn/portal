package com.hef.qhjiaoyiyuan.bean.exchange;

/**
 * @Date 2020/3/16
 * @Author lifei
 */
public class OptionArticleParam {

    // 作品的id
    private Integer aid;
    // 作品的状态
    private Integer status;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OptionArticleParam{" +
                "aid=" + aid +
                ", status=" + status +
                '}';
    }
}
