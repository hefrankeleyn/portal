package com.hef.qhjiaoyiyuan.bean;

import java.time.Instant;

/**
 * 文章描述
 * @Date 2020/3/11
 * @Author lifei
 */
public class Article {

    /** 文章的id */
    private Integer aid;

    /** 文章的标题 */
    private String contentTitle;

    /** 文章的内容（带标签） */
    private String contentText;

    /** 文章的摘录 */
    private String about;

    /** 文章的内容（不带标签） */
    private String content;

    /** 作者 */
    private String author;

    /**  封面图片的URL */
    private String coverUrl;

    /** 文章发布的实践 */
    private Instant issueTime;

    /**
     * 文章的状态
     * 0 暂存
     * 1 发布
     * 2 删除
     * */
    private int status=0;

    /**
     * 每一篇文章应当关联一个专栏
     */
    private Channel channel;

    public Article(){}

    private Article(Builder builder){
        this.contentTitle = builder.contentTitle;
        this.contentText = builder.contentText;
        this.about = builder.about;
        this.content = builder.content;
        this.author = builder.author;
        this.coverUrl = builder.coverUrl;
        this.issueTime = builder.issueTime;
        this.status = builder.status;
    }

    public static class Builder{
        private String contentTitle;

        private String contentText;

        private String about;

        private String content;

        private String author;

        private String coverUrl;

        private Instant issueTime;

        private int status=0;

        public Builder contentTitle(String contentTitle){
            this.contentTitle = contentTitle;
            return this;
        }

        public Builder contentText(String contentText){
            this.contentText = contentText;
            return this;
        }

        public Builder about(String about){
            this.about = about;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Builder coverUrl(String coverUrl){
            this.coverUrl = coverUrl;
            return this;
        }

        public Builder issueTime(Instant issueTime){
            this.issueTime = issueTime;
            return this;
        }

        public Builder status(int status){
            this.status= status;
            return this;
        }

        public Article builder(){
            return new Article(this);
        }
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Instant getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Instant issueTime) {
        this.issueTime = issueTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        if (channel==null)return;
        this.channel = channel;
    }



    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", contentTitle='" + contentTitle + '\'' +
                ", contentText='" + contentText + '\'' +
                ", about='" + about + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", issueTime=" + issueTime +
                ", status=" + status +
                '}';
    }
}
