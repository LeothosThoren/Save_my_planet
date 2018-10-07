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

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.api.YouTubeApi;
import com.leothos.savemyplanet.databinding.FragmentWebViewDialogBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.leothos.savemyplanet.controlers.fragments.Dashboard.BUNDLE_INT;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewDialog extends DialogFragment {

    // Constant
    public static final String INFORMATION_URL = "https://www.nationalgeographic.fr/environnement/ce-quil-faut-savoir-sur-lhuile-de-palme";
    public static final String INFORMATION_JS = "";
    public static final String DONATE_JS = "javascript:document.getElementById('form').style.display = 'block';";
    public static final String PETITION_URL = "https://www.sauvonslaforet.org/petitions/1074/pas-de-plantation-dhuile-de-palme-dans-cette-foret";
    public static final String PETITION_JS = "javascript:document.getElementById('petition-form').style.display = 'block';";
    private static final String DONATE_URL = "https://www.sauvonslaforet.org/dons/98/soutenir-sauvons-la-foret";
    private static final String TAG = "WebViewDialog";
    //Databinding
    private FragmentWebViewDialogBinding mWebViewBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mWebViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view_dialog, container, false);
        View view = mWebViewBinding.getRoot();
        ButterKnife.bind(this, view);
        this.inputHandler();
        return view;
    }

    @Override
    public void onResume() {
        if (getDialog().getWindow() != null) {
            ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
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
                this.webViewPlayer(INFORMATION_URL, INFORMATION_JS);
                break;
//            case R.id.youtube:
////               this.youtubePlayer();
//                break;
            case R.id.donate:
                this.webViewPlayer(DONATE_URL, DONATE_JS);
                break;
            case R.id.petition:
                this.webViewPlayer(PETITION_URL, PETITION_JS);
        }

        // Close button
        mWebViewBinding.closeWebView.setOnClickListener(v -> {
//            mWebViewBinding.webViewSwitcher.reset();
            getDialog().dismiss();
        });
    }


    // ----------
    // Ui
    // ----------

    @SuppressLint("SetJavaScriptEnabled")
    private void webViewPlayer(final String uri, final String js) {
        mWebViewBinding.webView.loadUrl(uri);
        mWebViewBinding.webView.getSettings().setJavaScriptEnabled(true);
        mWebViewBinding.webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                view.loadUrl(js);
                mWebViewBinding.webView.loadUrl(uri);
            }
        });
//
    }


}
