package com.superychen.base.common.error.handler;

import com.superychen.base.common.error.BaseErrorEnum;
import com.superychen.base.common.error.exception.CaptchaExpiredException;
import com.superychen.base.common.error.exception.IncorrectCaptchaException;
import com.superychen.base.common.error.exception.ParamInvalidException;
import com.superychen.base.common.error.exception.UnAuthException;
import com.superychen.base.common.error.exception.UserAlreadyExistException;
import com.superychen.base.common.model.ResultBean;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IncorrectCredentialsException.class})
    @ResponseBody
    public ResultBean exceptionHandler(IncorrectCredentialsException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(LockedAccountException.class)
    @ResponseBody
    public ResultBean exceptionHandler(LockedAccountException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResultBean exceptionHandler(AuthenticationException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(UnAuthException.class)
    @ResponseBody
    public ResultBean exceptionHandler(UnAuthException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResultBean exceptionHandler(UnauthorizedException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(IncorrectCaptchaException.class)
    @ResponseBody
    public ResultBean exceptionHandler(IncorrectCaptchaException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(CaptchaExpiredException.class)
    @ResponseBody
    public ResultBean exceptionHandler(CaptchaExpiredException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(ParamInvalidException.class)
    @ResponseBody
    public ResultBean exceptionHandler(ParamInvalidException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public ResultBean exceptionHandler(UserAlreadyExistException exception) {
        return handleErrorInfo(exception.getMessage(), exception);
    }

    private ResultBean handleErrorInfo(String message, Exception exception) {
        BaseErrorEnum errorEnum = BaseErrorEnum.match(exception);
        //如果是未知异常
        if (errorEnum == BaseErrorEnum.UNKNOWN) {
            return new ResultBean(errorEnum.getCode(), message);
        }
        //如果是已经异常
        return new ResultBean(errorEnum.getCode(), errorEnum.getMsg());
    }

}
