package com.superychen.base.mybatis.entity.test;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = -8097176091262521649L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//主键.
    private String name;//名称.
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Boolean available = Boolean.FALSE;
    private List<SysRole> roles;

}
