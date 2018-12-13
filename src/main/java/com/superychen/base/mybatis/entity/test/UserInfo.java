package com.superychen.base.mybatis.entity.test;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -8342971126174063394L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//用户id;
    @Column(unique = true)
    private String username;//账号.
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String salt;//加密密码的盐
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    private List<SysRole> roles;// 一个用户具有多个角色

    public String getCredentialsSalt() {
        return username + salt;
    }

}
