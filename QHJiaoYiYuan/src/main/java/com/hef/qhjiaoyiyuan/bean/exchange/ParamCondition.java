package com.hef.qhjiaoyiyuan.bean.exchange;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/3/29
 * @Author lifei
 */
public class ParamCondition {

    /** 栏目id */
    private Integer cid;
    /** 文章的名称 **/
    private String titleName;

    private List<Integer> status = new ArrayList<>();

    public ParamCondition(){}
    public ParamCondition(Builder builder){
        this.cid = builder.cid;
        this.titleName = builder.titleName;
        this.status = builder.status;
    }

    public static class Builder{
        private Integer cid;
        private String titleName;
        private List<Integer> status = new ArrayList<>();

        public Builder cid(Integer cid){
            this.cid = cid;
            return this;
        }

        public Builder titleName(String titleName){
            this.titleName = titleName;
            return this;
        }
        public Builder status(List<Integer> status){
            if (CollectionUtils.isEmpty(status)) return this;
            this.status.clear();;
            this.status.addAll(status);
            return this;
        }

        public ParamCondition builder(){
            return new ParamCondition(this);
        }
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        if (CollectionUtils.isEmpty(status)) return;
        this.status.clear();
        this.status.addAll(status);
    }

    @Override
    public String toString() {
        return "ParamCondition{" +
                "cid=" + cid +
                ", titleName='" + titleName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
