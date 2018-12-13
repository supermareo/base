package com.superychen.base.common.util.captcha.filter.predefined;

import com.superychen.base.common.util.captcha.filter.AbstractFilterFactory;
import com.superychen.base.common.util.captcha.filter.library.DoubleRippleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;


public class DoubleRippleFilterFactory extends AbstractFilterFactory {

    private List<BufferedImageOp> filters;
    private DoubleRippleImageOp ripple;

    public DoubleRippleFilterFactory() {
        ripple = new DoubleRippleImageOp();
    }

    @Override
    public List<BufferedImageOp> getFilters() {
        if (filters == null) {
            filters = new ArrayList<>();
            filters.add(ripple);
        }
        return filters;
    }

}
