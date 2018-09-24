package com.leothos.savemyplanet.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BindingAdapterUtils {
    //For http url
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).apply(RequestOptions.centerCropTransform()).into(imageView);
    }

    //For image drawable resource
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, int url) {
        Glide.with(imageView.getContext()).load(url).apply(RequestOptions.centerCropTransform()).into(imageView);
    }
}
