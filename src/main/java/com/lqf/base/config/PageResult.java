package com.lqf.base.config;

import java.util.List;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\5\8 0008  15:25
 * @description page返回类型封装类
 */
public class PageResult<T> {
    // 分页大小
    private int pageSize;
    // 当前页数
    private int pageNum;
    // 记录集合
    private List<T> rows;
    // 总条数
    private long total;
    // 总页数
    private int pages;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
