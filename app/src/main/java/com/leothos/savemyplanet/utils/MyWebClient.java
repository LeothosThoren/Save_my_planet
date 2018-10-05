package com.leothos.savemyplanet.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String js) {
        view.loadUrl(js);
    }
}
