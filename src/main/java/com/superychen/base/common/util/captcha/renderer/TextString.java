package com.superychen.base.common.util.captcha.renderer;

import java.util.ArrayList;

public class TextString {

    private ArrayList<TextCharacter> characters = new ArrayList<TextCharacter>();

    public void clear() {
        characters.clear();
    }

    void addCharacter(TextCharacter tc) {
        characters.add(tc);
    }

    ArrayList<TextCharacter> getCharacters() {
        return characters;
    }

    public double getWidth() {
        double minx = 0;
        double maxx = 0;
        boolean first = true;
        for (TextCharacter tc : characters) {
            if (first) {
                minx = tc.getX();
                maxx = tc.getX() + tc.getWidth();
                first = false;
            } else {
                if (minx > tc.getX()) {
                    minx = tc.getX();
                }
                if (maxx < tc.getX() + tc.getWidth()) {
                    maxx = tc.getX() + tc.getWidth();
                }
            }

        }
        return maxx - minx;
    }

    public double getHeight() {
        double miny = 0;
        double maxy = 0;
        boolean first = true;
        for (TextCharacter tc : characters) {
            if (first) {
                miny = tc.getY();
                maxy = tc.getY() + tc.getHeight();
                first = false;
            } else {
                if (miny > tc.getY()) {
                    miny = tc.getY();
                }
                if (maxy < tc.getY() + tc.getHeight()) {
                    maxy = tc.getY() + tc.getHeight();
                }
            }

        }
        return maxy - miny;
    }

}
