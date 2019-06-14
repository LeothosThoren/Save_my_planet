package com.leothos.savemyplanet.controlers.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.databinding.FragmentDashboardBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment implements KenBurnsView.TransitionListener, View.OnClickListener {

    // Constant
    public static final String WEB_VIEW_DIALOG = "com.leothos.savemyplanet.controlers.fragments.WebViewDialog";
    public static final String YOUTUBE_DIALOG = "com.leothos.savemyplanet.controlers.fragments.YouTubeDialog";
    public static final String BUNDLE_INT = "BUNDLE_INT";
    private static final int TRANSITIONS_TO_SWITCH = 1;
    private static final String TAG = "Dashboard";
    // Var
    private int mTransitionsCount = 0;
    // Databinding
    private FragmentDashboardBinding mBinding;

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        View view = mBinding.getRoot();

        //Presentation
        mBinding.presentation1.setTransitionListener(this);
        mBinding.presentation2.setTransitionListener(this);
        //Grid
        mBinding.information.setOnClickListener(this);
        mBinding.youtube.setOnClickListener(this);
        mBinding.donate.setOnClickListener(this);
        mBinding.petition.setOnClickListener(this);
        return view;
    }


    // ------------
    // Action
    // ------------

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.information:
                this.openWebView(id);
                Log.d(TAG, "onClick: id" + id);
                break;
            case R.id.youtube:
                this.openYoutubeView();
                Log.d(TAG, "onClick: id" + id);
                break;
            case R.id.donate:
                this.openWebView(id);
                Log.d(TAG, "onClick: id" + id);
                break;
            case R.id.petition:
                this.openWebView(id);
                Log.d(TAG, "onClick: id" + id);
                break;
        }
    }

    // ------------
    // UI
    // ------------

    private void openWebView(int id) {
        WebViewDialog dialog = new WebViewDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_INT, id);
        dialog.setArguments(bundle);
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_FullScreen);
        if (getFragmentManager() != null) {
            dialog.show(getFragmentManager(), WEB_VIEW_DIALOG);
        }
    }

    private void openYoutubeView() {
        YouTubeDialog youTubeDialog = new YouTubeDialog();
        youTubeDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_FullScreen);
        if (getFragmentManager() != null) {
            youTubeDialog.show(getFragmentManager(), YOUTUBE_DIALOG);
        }
    }

    @Override
    public void onTransitionStart(Transition transition) {

    }

    //Handle the switch between the image
    @Override
    public void onTransitionEnd(Transition transition) {
        mTransitionsCount++;
        if (mTransitionsCount == TRANSITIONS_TO_SWITCH) {
            mBinding.viewSwitcher.showNext();
            mTransitionsCount = 0;
        }
    }


}
