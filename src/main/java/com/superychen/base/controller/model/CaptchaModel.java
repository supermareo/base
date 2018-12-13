package com.superychen.base.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptchaModel {

    private String word;
    private String captcha;

}
