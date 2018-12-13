package com.superychen.base.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginReq extends CaptchaReq {

    private String username;
    private String password;

}
