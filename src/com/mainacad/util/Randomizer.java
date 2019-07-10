package com.mainacad.util;

import java.util.Random;

public class Randomizer {

    public static int getRandomNumber(int min, int max){
        int randomNumber = min + new Random().nextInt(max-min);
        return randomNumber;
    }

    public static String getRandomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] symbols = alphabet.toCharArray();
        String outText = "";
        for (int i = 0; i < length; i++) {
            outText += symbols[getRandomNumber(0, symbols.length - 1)];
        }
        return outText;
    }
}
