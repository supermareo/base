package com.superychen.base.common.util.captcha.filter;

import java.awt.image.BufferedImage;

public interface FilterFactory {

    BufferedImage applyFilters(BufferedImage source);

}
