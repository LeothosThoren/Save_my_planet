package com.leothos.savemyplanet.utils;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;

import java.util.HashMap;

public class BusinessLogic {

    public static String getStatusText(Integer number, String ok, String warning, String bad) {
        if (number == 0) {
            return ok;
        } else if (number == 1) {
            return bad;
        }
        return warning;
    }


    public static Drawable getStatusDrawable(Integer number, Drawable ok, Drawable warning, Drawable bad) {
        if (number == 0) {
            return ok;
        } else if (number == 1) {
            return bad;
        }
        return warning;
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

    public static Spanned displayHtml(String source) {
        return Html.fromHtml(source);
    }

}
