package com.superychen.base.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterReq extends CaptchaReq {

    private String username;
    private String name;
    private String password;

}
