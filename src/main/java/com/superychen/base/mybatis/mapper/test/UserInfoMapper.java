package com.superychen.base.mybatis.mapper.test;

import com.superychen.base.mybatis.entity.test.UserInfo;

import org.springframework.stereotype.Component;

import tk.mybatis.mapper.common.Mapper;

@Component
public interface UserInfoMapper extends Mapper<UserInfo> {
}
