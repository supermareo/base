package com.superychen.base.common.util.captcha.color;

import java.awt.Color;

import lombok.Data;

@Data
public class SingleColorFactory implements ColorFactory {

    private Color color;

    public SingleColorFactory() {
        color = Color.BLACK;
    }

    public SingleColorFactory(Color color) {
        this.color = color;
    }

    public Color getColor(int index) {
        return color;
    }

}
