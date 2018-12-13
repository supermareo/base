package com.superychen.base.common.util.captcha.filter.predefined;

import com.superychen.base.common.util.captcha.filter.AbstractFilterFactory;
import com.superychen.base.common.util.captcha.filter.library.RippleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;


public class RippleFilterFactory extends AbstractFilterFactory {

    private List<BufferedImageOp> filters;
    private RippleImageOp ripple;

    public RippleFilterFactory() {
        ripple = new RippleImageOp();
    }

    protected List<BufferedImageOp> getPreRippleFilters() {
        return new ArrayList<BufferedImageOp>();
    }

    private List<BufferedImageOp> getPostRippleFilters() {
        return new ArrayList<BufferedImageOp>();

    }

    @Override
    public List<BufferedImageOp> getFilters() {
        if (filters == null) {
            filters = new ArrayList<>();
            filters.addAll(getPreRippleFilters());
            filters.add(ripple);
            filters.addAll(getPostRippleFilters());
        }
        return filters;
    }


}
