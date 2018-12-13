package com.superychen.base.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 用户查询
     */
    @RequiresPermissions("userInfo:view")
    @RequestMapping("/userList")
    public String userInfo() {
        return "userInfo";
    }

    /**
     * 用户添加
     */
    @RequiresPermissions("userInfo:add")
    @RequestMapping("/userAdd")
    public String userInfoAdd() {
        return "userAdd";
    }

    /**
     * 用户删除
     */
    @RequiresPermissions("userInfo:del")
    @RequestMapping("/userDel")
    public String userInfoDel() {
        return "userDel";
    }

}
