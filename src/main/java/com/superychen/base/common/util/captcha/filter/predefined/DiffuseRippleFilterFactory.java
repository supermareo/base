package com.superychen.base.common.util.captcha.filter.predefined;

import com.superychen.base.common.util.captcha.filter.library.DiffuseImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;


public class DiffuseRippleFilterFactory extends RippleFilterFactory {

	private DiffuseImageOp diffuse = new DiffuseImageOp();

	@Override
	protected List<BufferedImageOp> getPreRippleFilters() {
		List<BufferedImageOp> list = new ArrayList<>();
		list.add(diffuse);
		return list;
	}
}
