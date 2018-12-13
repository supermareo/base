package com.superychen.base.common.error;

import com.superychen.base.common.error.exception.CaptchaExpiredException;
import com.superychen.base.common.error.exception.IncorrectCaptchaException;
import com.superychen.base.common.error.exception.ParamInvalidException;
import com.superychen.base.common.error.exception.UnAuthException;
import com.superychen.base.common.error.exception.UserAlreadyExistException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum BaseErrorEnum {

    UNKNOWN("-00000", "系统异常", null),
    METHOD_NOT_SUPPORT("-10000", "不支持的Http方法", HttpRequestMethodNotSupportedException.class),
    USERNAME_OR_PASSWORD_ERROR("-00001", "用户名或密码错误", IncorrectCredentialsException.class),
    USERNAME_OR_PASSWORD_ERROR2("-00001", "用户名或密码错误", AuthenticationException.class),
    ACCOUNT_LOCKED("-00002", "该用户已被冻结", IncorrectCredentialsException.class),
    USERNAME_EXISTS("-00003", "账号已存在", UserAlreadyExistException.class),
    UN_AUTH("-00004", "未登录", UnAuthException.class),
    NO_RIGHTS("-00005", "权限不足", UnauthorizedException.class),
    CAPTCHA_ERROR("-00006", "验证码错误", IncorrectCaptchaException.class),
    CAPTCHA_EXPIRED("-00007", "验证码已过期", CaptchaExpiredException.class),
    PARAM_INVALID("-00008", "参数错误", ParamInvalidException.class);

    String code;
    String msg;
    Class<? extends Exception> exception;

    public static BaseErrorEnum match(Exception exception) {
        if (exception == null) {
            return UNKNOWN;
        }
        Class<? extends Exception> clazz = exception.getClass();
        BaseErrorEnum errorEnum = Arrays.stream(BaseErrorEnum.values()).filter(e -> e.exception == clazz).findFirst().orElse(UNKNOWN);
        if (errorEnum == UNKNOWN) {
            log.error("unknown exception {}", exception);
        }
        return errorEnum;
    }

}
