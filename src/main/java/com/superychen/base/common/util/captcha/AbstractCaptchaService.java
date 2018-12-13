package com.superychen.base.common.util.captcha;

import com.superychen.base.common.util.captcha.background.BackgroundFactory;
import com.superychen.base.common.util.captcha.color.ColorFactory;
import com.superychen.base.common.util.captcha.filter.FilterFactory;
import com.superychen.base.common.util.captcha.font.FontFactory;
import com.superychen.base.common.util.captcha.renderer.TextRenderer;
import com.superychen.base.common.util.captcha.word.WordFactory;

import java.awt.image.BufferedImage;

public abstract class AbstractCaptchaService implements CaptchaService {

    FontFactory fontFactory;
    WordFactory wordFactory;
    ColorFactory colorFactory;
    BackgroundFactory backgroundFactory;
    TextRenderer textRenderer;
    FilterFactory filterFactory;

    protected int width;
    protected int height;

    public void setFontFactory(FontFactory fontFactory) {
        this.fontFactory = fontFactory;
    }

    public void setWordFactory(WordFactory wordFactory) {
        this.wordFactory = wordFactory;
    }

    public void setColorFactory(ColorFactory colorFactory) {
        this.colorFactory = colorFactory;
    }

    public void setBackgroundFactory(BackgroundFactory backgroundFactory) {
        this.backgroundFactory = backgroundFactory;
    }

    public void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    public void setFilterFactory(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    public FontFactory getFontFactory() {
        return fontFactory;
    }

    public WordFactory getWordFactory() {
        return wordFactory;
    }

    public ColorFactory getColorFactory() {
        return colorFactory;
    }

    public BackgroundFactory getBackgroundFactory() {
        return backgroundFactory;
    }

    public TextRenderer getTextRenderer() {
        return textRenderer;
    }

    public FilterFactory getFilterFactory() {
        return filterFactory;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Captcha getCaptcha() {
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        backgroundFactory.fillBackground(bufImage);
        String word = wordFactory.getNextWord();
        textRenderer.draw(word, bufImage, fontFactory, colorFactory);
        bufImage = filterFactory.applyFilters(bufImage);
        return new Captcha(word, bufImage);
    }

}
