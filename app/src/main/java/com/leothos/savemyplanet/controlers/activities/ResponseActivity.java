/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leothos.savemyplanet.controlers.activities;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.api.StreamApi;
import com.leothos.savemyplanet.controlers.fragments.DisplayImageBigSize;
import com.leothos.savemyplanet.databinding.ActivityResponseBinding;
import com.leothos.savemyplanet.models.OpenFoodFact;
import com.leothos.savemyplanet.utils.AddNewProduct;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.leothos.savemyplanet.controlers.activities.BarcodeCaptureActivity.BarcodeObject;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class ResponseActivity extends AppCompatActivity {

    public static final String CUSTOM_DIALOG_IMAGE = "CUSTOM_DIALOG_IMAGE";
    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = ResponseActivity.class.getSimpleName();
    // DataBinding
    private ActivityResponseBinding mBinding;
    // Var
    private OpenFoodFact mOpenFoodFact = new OpenFoodFact();
    private Disposable mDisposable;
    // Data
    private int status = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_response);
        this.configureToolBar();

        String barcode = getIntent().getStringExtra(BarcodeObject);
        Log.d(TAG, "onCreate: barcode = " + barcode);

        if (barcode != null)
            this.executeHttpRequest(barcode);
        else
            this.executeHttpRequest("8410076421449");

    }


    // -------------
    // Configuration
    // -------------

    private void configureToolBar() {
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save:
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // -------------
    // Rx Java
    // -------------

    private void executeHttpRequest(String barcode) {
        //First showing progress bar when doing task in background
        this.updateUiOnStartHttpRequest();
        this.mDisposable = StreamApi.streamFetchOpenFoodFactApi(barcode)
                .subscribeWith(new DisposableObserver<OpenFoodFact>() {
                    @Override
                    public void onNext(OpenFoodFact openFoodFact) {
                        Log.d(TAG, "onNext: " + openFoodFact.getStatusVerbose());
                        status = openFoodFact.getStatus();
                        mOpenFoodFact = openFoodFact;
                        mBinding.setOpenFoodFact(mOpenFoodFact);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        Toast.makeText(ResponseActivity.this, "An error occurred! Please check your connectivity...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: do some actions");
                        updateUiOnStopHttpRequest(status);
                    }
                });
    }

    // Dispose subscription
    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed())
            this.mDisposable.dispose();
    }


    //Called for better performances
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -------------
    // Action
    // -------------

    private void openCustomDialog() {
        DisplayImageBigSize imageBigSize = new DisplayImageBigSize();
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY, mOpenFoodFact.getProduct().getImageFrontUrl());
        imageBigSize.setArguments(args);
        imageBigSize.setStyle(DialogFragment.STYLE_NO_TITLE, DialogFragment.STYLE_NO_INPUT);
        imageBigSize.show(getSupportFragmentManager(), CUSTOM_DIALOG_IMAGE);

    }

    // -------------
    // UI
    // -------------

    private void updateUiOnStartHttpRequest() {
        mBinding.cardviewLayout.setVisibility(View.VISIBLE);
        mBinding.scrollView.setVisibility(View.GONE);
    }

    private void updateUiOnStopHttpRequest(int status) {
        if (status == 0) {
            mBinding.progressBar.setVisibility(View.GONE);
            mBinding.noFoundImage.setVisibility(View.VISIBLE);
            Snackbar.make(findViewById(R.id.coordinator), R.string.product_no_found, Snackbar.LENGTH_INDEFINITE)
                    .setAction("ADD", new AddNewProduct(this))
                    .setActionTextColor(Color.RED)
                    .show();
        } else {
            mBinding.cardviewLayout.setVisibility(View.GONE);
            mBinding.scrollView.setVisibility(View.VISIBLE);
            mBinding.productPicture.setOnClickListener(v->this.openCustomDialog());
        }

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RC_BARCODE) {
//            if (resultCode == CommonStatusCodes.SUCCESS) {
//                if (data != null) {
//                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
//                    statusMessage.setText(R.string.barcode_success);
//                    barcodeValue.setText(barcode.displayValue);
//                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
//                } else {
//                    statusMessage.setText(R.string.barcode_failure);
//                    Log.d(TAG, "No barcode captured, intent data is null");
//                }
//            } else {
//                statusMessage.setText(String.format(getString(R.string.barcode_error),
//                        CommonStatusCodes.getStatusCodeString(resultCode)));
//            }
//        }
//        else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
}