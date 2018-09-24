package com.leothos.savemyplanet.utils;

public class BuisnessLogic {

    public static String getStatusText(int number, String ok, String medium, String bad) {
        if (number == 0) {
            return ok;
        } else if (number == 1) {
            return bad;
        }
        return medium;
    }
}
