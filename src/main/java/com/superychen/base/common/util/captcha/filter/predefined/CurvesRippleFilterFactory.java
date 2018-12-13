package com.superychen.base.common.util.captcha.filter.predefined;

import com.superychen.base.common.util.captcha.color.ColorFactory;
import com.superychen.base.common.util.captcha.filter.library.CurvesImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class CurvesRippleFilterFactory extends RippleFilterFactory {

	private CurvesImageOp curves = new CurvesImageOp();

	public CurvesRippleFilterFactory() {
	}

	public CurvesRippleFilterFactory(ColorFactory colorFactory) {
		setColorFactory(colorFactory);
	}

	@Override
	protected List<BufferedImageOp> getPreRippleFilters() {
		List<BufferedImageOp> list = new ArrayList<BufferedImageOp>();
		list.add(curves);
		return list;
	}

	public void setStrokeMin(float strokeMin) {
		curves.setStrokeMin(strokeMin);
	}

	public void setStrokeMax(float strokeMax) {
		curves.setStrokeMax(strokeMax);
	}

	private void setColorFactory(ColorFactory colorFactory) {
		curves.setColorFactory(colorFactory);
	}

}
