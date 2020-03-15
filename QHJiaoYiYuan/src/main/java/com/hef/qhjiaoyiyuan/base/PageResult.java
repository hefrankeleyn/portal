package com.hef.qhjiaoyiyuan.base;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/3/15
 * @Author lifei
 */
public class PageResult<T> {

    /**
     * 当页对象
     */
    private List<T> pageObjList = new ArrayList<>();

    /**
     * 当前页页码
     */
    private int currentPageNum;
    /**
     * 下一页页码
     */
    private int nextPageNum;

    /**
     * 上一页页码
     */
    private int lastPageNum;

    /**
     * 总的条数
     */
    private int totalItemNum;

    /**
     * 当前页第一条在所有条目中属于第几条
     */
    private int currentPageFirstItemNum;

    /**
     * 一页的条数
     * 一页默认为10条
     */
    private int pageSize = 10;

    /**
     * 总共的页码数
     */
    private int totalPageNum;

    public PageResult(int totalItemNum, int currentPageNum, int pageSize) {

        // 一页的条数
        if (pageSize <= 0) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
        // 需要传入总条数
        if (totalItemNum < 0) {
            this.totalItemNum = 0;
        } else {
            this.totalItemNum = totalItemNum;
        }
        // 总页数
        this.totalPageNum = (this.totalItemNum % this.pageSize) == 0 ? (this.totalItemNum / this.pageSize)
                : (this.totalItemNum / this.pageSize + 1);
        // 当前页面数
        if (currentPageNum > this.totalPageNum) {
            this.currentPageNum = this.totalPageNum;
        } else if (this.totalPageNum == 0) {
            this.currentPageNum = 0;
        } else if (currentPageNum < 1) {
            this.currentPageNum = 1;
        } else {
            this.currentPageNum = currentPageNum;
        }
        // 下一页
        this.nextPageNum = (this.currentPageNum + 1) > this.totalPageNum ? this.totalPageNum : (this.currentPageNum + 1);
        // 上一页
        this.lastPageNum = this.totalPageNum == 0 ? 0 : (this.currentPageNum - 1) < 1 ? 1 : (this.currentPageNum - 1);
        // 当前页第一条在总条数数是第几条, 从0 开始
        this.currentPageFirstItemNum = this.currentPageNum <= 0 ? 0 : (this.currentPageNum - 1) * this.pageSize;
    }

    public List<T> getPageObjList() {
        return pageObjList;
    }

    public void setPageObjList(List<T> pageObjList) {
        if (CollectionUtils.isEmpty(pageObjList)) return;
        this.pageObjList.clear();
        this.pageObjList.addAll(pageObjList);
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public int getTotalItemNum() {
        return totalItemNum;
    }

    public void setTotalItemNum(int totalItemNum) {
        this.totalItemNum = totalItemNum;
    }

    public int getCurrentPageFirstItemNum() {
        return currentPageFirstItemNum;
    }

    public void setCurrentPageFirstItemNum(int currentPageFirstItemNum) {
        this.currentPageFirstItemNum = currentPageFirstItemNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageObjList=" + pageObjList +
                ", currentPageNum=" + currentPageNum +
                ", nextPageNum=" + nextPageNum +
                ", lastPageNum=" + lastPageNum +
                ", totalItemNum=" + totalItemNum +
                ", currentPageFirstItemNum=" + currentPageFirstItemNum +
                ", pageSize=" + pageSize +
                ", totalPageNum=" + totalPageNum +
                '}';
    }
}
