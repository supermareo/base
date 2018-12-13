package com.superychen.base.common.model;

import com.github.pagehelper.Page;

import java.util.List;

import lombok.Data;

import static com.superychen.base.common.model.Constant.CODE_SUCCESS;

@Data
public class PageResultBean<T> {

    private String code;
    private String msg;

    private Integer pageNo;
    private Integer pageSize;
    private Integer pages;
    private Long totalNum;
    private List<T> data;

    public PageResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PageResultBean(Page<T> data) {
        this.code = CODE_SUCCESS;
        this.pageNo = data.getPageNum();
        this.pageSize = data.getPageSize();
        this.pages = data.getPages();
        this.totalNum = data.getTotal();
        this.data = data.getResult();
    }

}
