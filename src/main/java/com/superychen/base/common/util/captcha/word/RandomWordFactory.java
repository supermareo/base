package com.superychen.base.common.util.captcha.word;

import java.util.Random;

import lombok.Data;

@Data
public class RandomWordFactory implements WordFactory {

    String characters;
    int minLength;
    int maxLength;

    public RandomWordFactory() {
        characters = "absdegkmnopwx23456789";
        minLength = 6;
        maxLength = 6;
    }

    public String getNextWord() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        int l = minLength + (maxLength > minLength ? rnd.nextInt(maxLength - minLength) : 0);
        for (int i = 0; i < l; i++) {
            int j = rnd.nextInt(characters.length());
            sb.append(characters.charAt(j));
        }
        return sb.toString();
    }


}
