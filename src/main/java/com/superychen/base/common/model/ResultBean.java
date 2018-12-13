package com.superychen.base.common.model;

import lombok.Data;

import static com.superychen.base.common.model.Constant.CODE_SUCCESS;

@Data
public class ResultBean<T> {

    private String code;
    private String msg;
    private T data;

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(T data) {
        this.code = CODE_SUCCESS;
        this.data = data;
    }

}
