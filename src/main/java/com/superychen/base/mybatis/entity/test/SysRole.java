package com.superychen.base.mybatis.entity.test;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 6371505195864471206L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户
    //角色 -- 权限关系：多对多关系;
    private List<SysPermission> permissions;
    // 用户 - 角色关系定义;
    private List<UserInfo> userInfos;// 一个角色对应多个用户

}
