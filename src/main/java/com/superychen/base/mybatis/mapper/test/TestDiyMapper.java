package com.superychen.base.mybatis.mapper.test;

import com.superychen.base.mybatis.entity.test.UserInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TestDiyMapper {

    UserInfo findByUserName(@Param("username") String username);

}
