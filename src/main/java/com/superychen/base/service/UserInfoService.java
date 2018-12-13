package com.superychen.base.service;

import com.superychen.base.common.error.exception.UserAlreadyExistException;
import com.superychen.base.controller.model.RegisterReq;
import com.superychen.base.mybatis.entity.test.SysRole;
import com.superychen.base.mybatis.entity.test.SysUserRole;
import com.superychen.base.mybatis.entity.test.UserInfo;
import com.superychen.base.mybatis.mapper.test.SysRoleMapper;
import com.superychen.base.mybatis.mapper.test.SysUserRoleMapper;
import com.superychen.base.mybatis.mapper.test.TestDiyMapper;
import com.superychen.base.mybatis.mapper.test.UserInfoMapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private TestDiyMapper testDiyMapper;

    public UserInfo findByUsername(String username) {
        return testDiyMapper.findByUserName(username);
    }

    @Transactional
    public void register(RegisterReq req) {
        log.info("register enter, req={}", req);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(req.getUsername());

        int count = userInfoMapper.selectCount(userInfo);
        if (count > 0) {
            log.info("register fail, account {} already exists", req.getUsername());
            throw new UserAlreadyExistException();
        }

        userInfo.setName(StringUtils.isBlank(req.getName()) ? req.getUsername() : req.getName());
        userInfo.setSalt(UUID.randomUUID().toString());

        String hashAlgorithmName = "MD5";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        String newPwd = new SimpleHash(hashAlgorithmName, req.getPassword(), credentialsSalt, hashIterations).toHex();
        userInfo.setPassword(newPwd);

        //创建用户
        long userId = userInfoMapper.insert(userInfo);
        log.info("register user, account {} userId {}", req.getUsername(), userId);

        //初始化时，默认roleId为1的为普通用户，将第一次注册的用户权限设置为普通用户
        SysRole query = new SysRole();
        query.setRole("user");
        SysRole role = sysRoleMapper.selectOne(query);
        SysUserRole sysUserRole = new SysUserRole(userId, role.getId());
        SysUserRole exist = sysUserRoleMapper.selectOne(sysUserRole);
        if (exist == null) {
            sysUserRoleMapper.insert(sysUserRole);
        }
        log.info("register user complete, account {} userId {} roleId {}", userInfo.getUsername(), userId, role.getId());
    }

}
