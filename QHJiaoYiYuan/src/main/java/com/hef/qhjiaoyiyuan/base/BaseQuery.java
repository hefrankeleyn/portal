package com.hef.qhjiaoyiyuan.base;

/**
 * @Date 2020/3/15
 * @Author lifei
 */
public class BaseQuery {
    /**
     * 当前页,默认为第1页
     */
    protected Integer currentPage=1;

    /**
     * 一页的条数， 默认为10条
     */
    protected Integer pageSize = 2;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
