package com.superychen.base.shiro;

import com.google.gson.Gson;
import com.superychen.base.mybatis.entity.test.SysPermission;
import com.superychen.base.mybatis.entity.test.SysRole;
import com.superychen.base.mybatis.entity.test.UserInfo;
import com.superychen.base.service.UserInfoService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Realm是一个Dao，通过它来验证用户身份和权限。这里Shiro不做权限的管理工作，需要我们自己管理用户权限，
 * 只需要从我们的数据源中把用户和用户的角色权限信息取出来交给Shiro即可。
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    private UserInfoService userInfoService;

    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("开始权限配置");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        log.info("primaryPrincipal={}", principals.getPrimaryPrincipal());
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoles()) {
            log.info("has role {}", new Gson().toJson(role));
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                log.info("has permission {}", new Gson().toJson(p));
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始身份验证");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        log.info("userInfo={}", new Gson().toJson(userInfo));
        if (userInfo == null) {
            return null;
        }
        if (userInfo.getState() == 1) { //账户冻结
            throw new LockedAccountException();
        }
        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        return new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
    }

}