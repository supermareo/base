package com.superychen.base.common.util.captcha;

import java.awt.image.BufferedImage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Captcha {

    private String challenge;
    private BufferedImage image;

}
