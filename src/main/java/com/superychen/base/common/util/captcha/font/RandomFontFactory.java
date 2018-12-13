package com.superychen.base.common.util.captcha.font;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lombok.Data;

@Data
public class RandomFontFactory implements FontFactory {

    private List<String> families;
    private int minSize;
    private int maxSize;
    private boolean randomStyle;

    public RandomFontFactory() {
        families = new ArrayList<>();
        families.add("Verdana");
        families.add("Tahoma");
        minSize = 45;
        maxSize = 45;
    }

    private RandomFontFactory(List<String> families) {
        this();
        this.families = families;
    }

    public RandomFontFactory(String... families) {
        this();
        this.families = Arrays.asList(families);
    }

    public RandomFontFactory(int size, List<String> families) {
        this(families);
        minSize = maxSize = size;
    }

    public RandomFontFactory(int size, String[] families) {
        this(families);
        minSize = maxSize = size;
    }

    public Font getFont(int index) {
        Random r = new Random();
        String family = families.get(r.nextInt(families.size()));
        boolean bold = r.nextBoolean() && randomStyle;
        int size = minSize;
        if (maxSize - minSize > 0) {
            size += r.nextInt(maxSize - minSize);
        }
        return new Font(family, bold ? Font.BOLD : Font.PLAIN, size);
    }

}
