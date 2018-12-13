package com.superychen.base.mybatis.entity.test;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 2157719117809830115L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rid;
    private Long pid;
}
