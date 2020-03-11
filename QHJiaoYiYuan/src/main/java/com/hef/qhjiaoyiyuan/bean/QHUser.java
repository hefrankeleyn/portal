package com.hef.qhjiaoyiyuan.bean;

import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * 登陆用户的信息
 * @Date 2020/3/1
 * @Author lifei
 */
public class QHUser implements Cloneable{

    /** 主键，自增 */
    private Integer uId;

    /** 登陆用户名 */
    private String loginUserName;

    /** 登陆密码 */
    private String loginPassWorld;

    /** 昵称 */
    private String nickname;

    /** 用户描述 */
    private String userDes;

    /** 用户注册的时间 */
    private Instant createDate;

    private List<QHRole> roles = new ArrayList<>();

    public QHUser(){
    }

    public QHUser(Builder builder){
        this.loginUserName = builder.loginUserName;
        this.loginPassWorld = builder.loginPassWorld;
        this.nickname = builder.nickname;
        this.userDes = builder.userDes;
        this.createDate = builder.createDate;
    }

    public static class Builder{
        private String loginUserName;
        private String loginPassWorld;
        private String nickname;
        private String userDes;
        private Instant createDate;

        public Builder loginUserName(String  loginUserName){
            this.loginUserName = loginUserName;
            return this;
        }
        public Builder loginPassWorld(String loginPassWorld){
            this.loginPassWorld = loginPassWorld;
            return this;
        }
        public Builder nickname(String nickname){
            this.nickname = nickname;
            return this;
        }
        public Builder userDes(String userDes){
            this.userDes = userDes;
            return this;
        }
        public Builder createDate(Instant createDate){
            this.createDate = createDate;
            return this;
        }

        public QHUser builder(){
            return new QHUser(this);
        }

    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginPassWorld() {
        return loginPassWorld;
    }

    public void setLoginPassWorld(String loginPassWorld) {
        this.loginPassWorld = loginPassWorld;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserDes() {
        return userDes;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public List<QHRole> getRoles() {
        return roles;
    }

    public void setRoles(List<QHRole> roles) {
        if (CollectionUtils.isEmpty(roles)){
            return;
        }
        this.roles.clear();
        for (QHRole role : roles) {
            this.roles.add(role.clone());
        }
    }

    @Override
    public QHUser clone()  {
        try {
            return (QHUser) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "QHUser{" +
                "uId=" + uId +
                ", loginUserName='" + loginUserName + '\'' +
                ", loginPassWorld='" + loginPassWorld + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userDes='" + userDes + '\'' +
                ", createDate=" + createDate +
                ", roles=" + roles +
                '}';
    }
}
