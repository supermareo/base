package com.superychen.base.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptchaResp {

    private String cid;
    private String captcha;

}
