package com.superychen.base.mybatis.entity.test;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 8784567363316542979L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private Long rid;

    public SysUserRole(Long uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

}
