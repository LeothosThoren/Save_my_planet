package com.leothos.savemyplanet.utils;

import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class BusinessLogic {

    public static String getStatusText(int number, String ok, String warning, String bad) {
        if (number == 0) {
            return ok;
        } else if (number == 1) {
            return bad;
        } else {
            return warning;
        }

    }


    public static Drawable getStatusDrawable(int number, Drawable ok, Drawable warning, Drawable bad) {
        if (number == 0) {
            return ok;
        } else if (number == 1) {
            return bad;
        } else {
            return warning;
        }

    }

    public static Drawable getNutriScore(String score, Drawable a, Drawable b, Drawable c, Drawable d, Drawable e) {
        HashMap<String, Drawable> hashMap = new HashMap<>();
        hashMap.put("a", a);
        hashMap.put("b", b);
        hashMap.put("c", c);
        hashMap.put("d", d);
        hashMap.put("e", e);

        return hashMap.get(score) != null ? hashMap.get(score) : e;
    }


}
