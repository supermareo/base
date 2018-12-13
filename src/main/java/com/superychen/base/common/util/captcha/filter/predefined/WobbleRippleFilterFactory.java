package com.superychen.base.common.util.captcha.filter.predefined;

import com.superychen.base.common.util.captcha.filter.library.WobbleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;


public class WobbleRippleFilterFactory extends RippleFilterFactory {

    private WobbleImageOp wobble;

    public WobbleRippleFilterFactory() {
        wobble = new WobbleImageOp();
    }

    @Override
    protected List<BufferedImageOp> getPreRippleFilters() {
        List<BufferedImageOp> list = new ArrayList<BufferedImageOp>();
        list.add(wobble);
        return list;
    }

}
