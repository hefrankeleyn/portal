package com.hef.qhjiaoyiyuan.base.impl;

import com.hef.qhjiaoyiyuan.base.BaseQuery;

import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/3/15
 * @Author lifei
 */
public class ArticleQuery extends BaseQuery {

    /** 文章的题目 */
    private String articleTitle;
    /** 文章的状态 */
    private int[] status;
    /** 栏目的id */
    private int[] cids;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public int[] getStatus() {
        return status;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public int[] getCids() {
        return cids;
    }

    public void setCids(int[] cids) {
        this.cids = cids;
    }

    @Override
    public String toString() {
        return "ArticleQuery{" +
                "articleTitle='" + articleTitle + '\'' +
                ", status=" + Arrays.toString(status) +
                ", cids=" + Arrays.toString(cids) +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
