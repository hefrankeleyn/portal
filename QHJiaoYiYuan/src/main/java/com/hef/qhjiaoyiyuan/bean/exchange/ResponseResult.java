package com.hef.qhjiaoyiyuan.bean.exchange;


/**
 * @Date 2020/3/15
 * @Author lifei
 */
public class ResponseResult<T> {


    private Integer status;
    private T result;
    private String info;

    public ResponseResult(ResultStatus resultStatus, T result) {
        this.result = result;
        this.status = resultStatus.getStatus();
        this.info = resultStatus.getInfo();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status=" + status +
                ", result=" + result +
                ", info='" + info + '\'' +
                '}';
    }
}
