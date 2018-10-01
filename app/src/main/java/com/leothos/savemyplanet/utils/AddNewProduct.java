package com.leothos.savemyplanet.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class AddNewProduct implements View.OnClickListener {

    private Context mContext;

    public AddNewProduct(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "Test action", Toast.LENGTH_SHORT).show();
    }
}
