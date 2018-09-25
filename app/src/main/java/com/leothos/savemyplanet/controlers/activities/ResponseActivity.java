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

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.api.StreamApi;
import com.leothos.savemyplanet.databinding.ActivityResponseBinding;
import com.leothos.savemyplanet.models.OpenFoodFact;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.leothos.savemyplanet.controlers.activities.BarcodeCaptureActivity.BarcodeObject;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class ResponseActivity extends AppCompatActivity {

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = ResponseActivity.class.getSimpleName();
    //DataBinding
    private ActivityResponseBinding mBinding;
    //Var
    private OpenFoodFact mOpenFoodFact = new OpenFoodFact();
    private Disposable mDisposable;

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
        setSupportActionBar(this.mBinding.t);
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
//        this. updateUiOnStartHttpRequest();
        this.mDisposable = StreamApi.streamFetchOpenFoodFactApi(barcode)
                .subscribeWith(new DisposableObserver<OpenFoodFact>() {
                    @Override
                    public void onNext(OpenFoodFact openFoodFact) {
                        Log.d(TAG, "onNext: " + openFoodFact.getStatusVerbose());
                        mOpenFoodFact = openFoodFact;
                        mBinding.setOpenFoodFact(mOpenFoodFact);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        Log.e(TAG, "onError: ", e);
                        Toast.makeText(ResponseActivity.this, "Pas trouvé!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: do some actions");
//                        updateUiOnStopHttpRequest();

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
    // UI
    // -------------
//
//    private void updateUiOnStartHttpRequest() {
//        mBinding.activityMainProgressBar.setVisibility(View.VISIBLE);
//    }
//
//    private void updateUiOnStopHttpRequest() {
//        mBinding.activityMainProgressBar.setVisibility(View.GONE);
//    }


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
