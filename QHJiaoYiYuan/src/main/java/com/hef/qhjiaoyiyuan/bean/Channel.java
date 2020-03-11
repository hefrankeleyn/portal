package com.hef.qhjiaoyiyuan.bean;

/**
 * 栏目信息
 * @Date 2020/3/11
 * @Author lifei
 */
public class Channel implements Cloneable{

    /** 栏目的id */
    private Integer cid;

    /** 栏目的名称 */
    private String channelName;

    /** 栏目的描述 */
    private String channelDes;

    /** 管理员 */
    private QHUser qhUser;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDes() {
        return channelDes;
    }

    public void setChannelDes(String channelDes) {
        this.channelDes = channelDes;
    }

    public QHUser getQhUser() {
        return qhUser;
    }

    public void setQhUser(QHUser qhUser) {
        if (qhUser==null) return;
        this.qhUser = qhUser.clone();
    }

    @Override
    public Channel clone() {
        try {
            Channel clone = (Channel) super.clone();
            clone.qhUser = qhUser.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "Channel{" +
                "cid=" + cid +
                ", channelName='" + channelName + '\'' +
                ", channelDes='" + channelDes + '\'' +
                ", qhUser=" + qhUser +
                '}';
    }
}
