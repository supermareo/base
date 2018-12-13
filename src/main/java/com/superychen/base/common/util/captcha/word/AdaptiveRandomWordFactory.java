package com.superychen.base.common.util.captcha.word;

import java.util.Random;

import lombok.Data;

@Data
public class AdaptiveRandomWordFactory extends RandomWordFactory {

    private String wideCharacters;

    public AdaptiveRandomWordFactory() {
        characters = "absdegkmnopwx23456789";
        wideCharacters = "mw";
    }

    @Override
    public String getNextWord() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder chars = new StringBuilder(characters);
        int l = minLength + (maxLength > minLength ? rnd.nextInt(maxLength - minLength) : 0);
        for (int i = 0; i < l; i++) {
            int j = rnd.nextInt(chars.length());
            char c = chars.charAt(j);
            if (wideCharacters.indexOf(c) != -1) {
                for (int k = 0; k < wideCharacters.length(); k++) {
                    int idx = chars.indexOf(String.valueOf(wideCharacters.charAt(k)));
                    if (idx != -1) {
                        chars.deleteCharAt(idx);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
