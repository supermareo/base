package com.superychen.base.controller;

import com.superychen.base.common.error.exception.ParamInvalidException;
import com.superychen.base.common.error.exception.UnAuthException;
import com.superychen.base.common.model.ResultBean;
import com.superychen.base.controller.model.CaptchaResp;
import com.superychen.base.controller.model.LoginReq;
import com.superychen.base.controller.model.RegisterReq;
import com.superychen.base.service.CaptchaService;
import com.superychen.base.service.UserInfoService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/unAuth")
    private ResultBean<Void> unAuth() {
        throw new UnAuthException();
    }

    @PostMapping("/login")
    public ResultBean<String> login(@RequestBody LoginReq req) {
        log.info("login enter");
        if (StringUtils.isAnyBlank(req.getCid(), req.getCaptcha(), req.getUsername(), req.getPassword())) {
            throw new ParamInvalidException();
        }
        captchaService.checkCaptcha(req.getCid(), req.getCaptcha());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(req.getUsername(), req.getPassword());
        subject.login(token);
        return new ResultBean<>(subject.getSession().getId().toString());
    }

    @PostMapping("/register")
    public ResultBean<Void> register(@RequestBody RegisterReq req) {
        log.info("register enter");
        if (StringUtils.isAnyBlank(req.getCid(), req.getCaptcha(), req.getUsername(), req.getPassword())) {
            throw new ParamInvalidException();
        }
        captchaService.checkCaptcha(req.getCid(), req.getCaptcha());
        userInfoService.register(req);
        return new ResultBean<>(null);
    }

    @GetMapping("/captcha")
    public ResultBean<CaptchaResp> captcha() {
        CaptchaResp captcha = captchaService.generateCaptcha();
        return new ResultBean<>(captcha);
    }

}
