package com.leothos.savemyplanet.data.api;

import com.leothos.savemyplanet.BuildConfig;

public class YouTubeApi {

    public YouTubeApi() {
    }

    private static final String API_KEY = BuildConfig.YoutubeApiKey;

    public static String getApiKey() {
        return API_KEY;
    }
}
