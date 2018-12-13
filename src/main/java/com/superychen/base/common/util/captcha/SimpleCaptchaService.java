package com.superychen.base.common.util.captcha;

import com.superychen.base.common.util.captcha.background.SingleColorBackgroundFactory;
import com.superychen.base.common.util.captcha.color.SingleColorFactory;
import com.superychen.base.common.util.captcha.filter.FilterFactory;
import com.superychen.base.common.util.captcha.font.RandomFontFactory;
import com.superychen.base.common.util.captcha.renderer.BestFitTextRenderer;
import com.superychen.base.common.util.captcha.word.AdaptiveRandomWordFactory;

import java.awt.Color;

public class SimpleCaptchaService extends AbstractCaptchaService {

    public SimpleCaptchaService(int width, int height, Color textColor, Color backgroundColor, int fontSize, FilterFactory ff) {
        backgroundFactory = new SingleColorBackgroundFactory(backgroundColor);
        wordFactory = new AdaptiveRandomWordFactory();
        fontFactory = new RandomFontFactory();
        textRenderer = new BestFitTextRenderer();
        colorFactory = new SingleColorFactory(textColor);
        filterFactory = ff;
        this.width = width;
        this.height = height;
    }

    public SimpleCaptchaService(int width, int height, Color textColor, Color backgroundColor, int fontSize, String[] fontNames, FilterFactory ff) {
        backgroundFactory = new SingleColorBackgroundFactory(backgroundColor);
        wordFactory = new AdaptiveRandomWordFactory();
        fontFactory = new RandomFontFactory(fontNames);
        textRenderer = new BestFitTextRenderer();
        colorFactory = new SingleColorFactory(textColor);
        filterFactory = ff;
        this.width = width;
        this.height = height;
    }

}
