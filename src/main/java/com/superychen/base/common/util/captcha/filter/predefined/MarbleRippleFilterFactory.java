package com.superychen.base.common.util.captcha.filter.predefined;

import com.superychen.base.common.util.captcha.filter.library.MarbleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;


public class MarbleRippleFilterFactory extends RippleFilterFactory {

    private MarbleImageOp marble = new MarbleImageOp();

    @Override
    protected List<BufferedImageOp> getPreRippleFilters() {
        List<BufferedImageOp> list = new ArrayList<>();
        list.add(marble);
        return list;
    }

}

