package com.leothos.savemyplanet.controlers.fragments;


import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.databinding.FragmentWebViewDialogBinding;
import com.leothos.savemyplanet.utils.JsInterface;

import static com.leothos.savemyplanet.controlers.fragments.Dashboard.BUNDLE_INT;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewDialog extends DialogFragment implements JsInterface {

    // Constant
    public static final String INFORMATION_URL = "https://www.sauvonslaforet.org/themes/l-huile-de-palme#start";
    public static final String PETITION_URL = "https://www.sauvonslaforet.org/petitions/1074/pas-de-plantation-dhuile-de-palme-dans-cette-foret";
    private static final String DONATE_URL = "https://www.sauvonslaforet.org/dons/98/soutenir-sauvons-la-foret";
    private static final String TAG = "WebViewDialog";
    //Databinding
    private FragmentWebViewDialogBinding mWebViewBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mWebViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view_dialog, container, false);
        View view = mWebViewBinding.getRoot();

        this.inputHandler();
        return view;
    }

    @Override
    public void onResume() {
        if (getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }


    // ----------
    // Action
    // ----------

    private void inputHandler() {
        // Widget button
        int id = getArguments() != null ? getArguments().getInt(BUNDLE_INT) : 0;
        switch (id) {
            case R.id.information:
                this.webViewPlayer(INFORMATION_URL, INFORMATION_JS());
                break;
            case R.id.donate:
                this.webViewPlayer(DONATE_URL, DONATION_JS());
                break;
            case R.id.petition:
                this.webViewPlayer(PETITION_URL, PETITION_JS());
        }

        // Close button
        mWebViewBinding.closeWebView.setOnClickListener(v -> {
            getDialog().dismiss();
        });
    }


    // ----------
    // Ui
    // ----------

    //WebView Configuration
    @SuppressLint("SetJavaScriptEnabled")
    private void webViewPlayer(final String uri, final String js) {
        mWebViewBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d(TAG, "onPageStarted: ok");
                mWebViewBinding.wvProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "onPageFinished: ok");
                view.loadUrl(js);
                mWebViewBinding.webView.setVisibility(View.VISIBLE);
                mWebViewBinding.wvProgressbar.setVisibility(View.GONE);
            }
        });

        mWebViewBinding.webView.getSettings().setJavaScriptEnabled(true);
        mWebViewBinding.webView.loadUrl(uri);

    }


}
