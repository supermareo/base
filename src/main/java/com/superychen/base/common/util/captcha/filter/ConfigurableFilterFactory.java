package com.superychen.base.common.util.captcha.filter;

import java.awt.image.BufferedImageOp;
import java.util.List;

import lombok.Data;

@Data
public class ConfigurableFilterFactory extends AbstractFilterFactory {

    private List<BufferedImageOp> filters;

}
