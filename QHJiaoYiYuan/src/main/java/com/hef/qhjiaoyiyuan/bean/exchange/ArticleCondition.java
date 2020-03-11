package com.hef.qhjiaoyiyuan.bean.exchange;

/**
 * 查询文章的条件
 * @Date 2020/3/11
 * @Author lifei
 */
public class ArticleCondition {

    /** 文章的名称 */
    private String articleTitle;

    /** 作者的名字 */
    private String author;

    /** 文章的状态 */
    private Integer status;

    /** 栏目的名称 */
    private String channelName;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "ArticleCondition{" +
                "articleTitle='" + articleTitle + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
