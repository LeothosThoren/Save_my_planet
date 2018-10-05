package com.leothos.savemyplanet.controlers.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
public class Dashboard extends Fragment implements KenBurnsView.TransitionListener{

    private static final int TRANSITIONS_TO_SWITCH = 2;
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

        mBinding.presentation1.setTransitionListener(this);
        mBinding.presentation2.setTransitionListener(this);
        
        return view;
    }

    @Override
    public void onTransitionStart(Transition transition) {

    }

    @Override
    public void onTransitionEnd(Transition transition) {
        mTransitionsCount++;
        if (mTransitionsCount == TRANSITIONS_TO_SWITCH) {
            mBinding.viewSwitcher.showNext();
            mTransitionsCount = 0;
        }
    }
}
