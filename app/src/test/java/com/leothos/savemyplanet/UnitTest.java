package com.leothos.savemyplanet;

import com.leothos.savemyplanet.utils.BusinessLogic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void getStatusText() {
        assertEquals("No palm oil", BusinessLogic.getStatusText(0, "No palm oil",
                "May have palm oil", "With palm oil"));

        assertEquals("With palm oil", BusinessLogic.getStatusText(1, "No palm oil",
                "May have palm oil", "With palm oil"));
    }

    @Test
    public void getNutriScore() {
        assertEquals (1, BusinessLogic.getNutriScore("a", 1, 2, 3, 4, 5), 0);
        assertEquals (3, BusinessLogic.getNutriScore("c", 1, 2, 3, 4, 5), 0);
        assertEquals (5, BusinessLogic.getNutriScore("e", 1, 2, 3, 4, 5), 0);
    }
}