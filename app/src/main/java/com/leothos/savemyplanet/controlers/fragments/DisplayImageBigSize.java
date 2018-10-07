package com.leothos.savemyplanet.controlers.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.databinding.FragmentDisplayImageBigSizeBinding;

import static com.leothos.savemyplanet.controlers.activities.ResponseActivity.BUNDLE_KEY_RESPONSE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayImageBigSize extends DialogFragment {

    private FragmentDisplayImageBigSizeBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_display_image_big_size, container, false);
        View view = mBinding.getRoot();
        // Retrieve data
        String url = getArguments() != null ? getArguments().getString(BUNDLE_KEY_RESPONSE) : null;
        mBinding.closeButton.setOnClickListener(v -> getDialog().dismiss());
        Glide.with(this).load(url).apply(RequestOptions.centerCropTransform()).into(mBinding.bigSizeImage);

        return view;
    }


}
