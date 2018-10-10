package com.leothos.savemyplanet.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.leothos.savemyplanet.R;

public class SnackAction implements View.OnClickListener {

    private Activity mActivity;

    public SnackAction(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onClick(View v) {
        mActivity.finish();
    }
}
