package com.leothos.savemyplanet.api;

import com.leothos.savemyplanet.models.OpenFoodFact;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StreamApi {

    public static Observable<OpenFoodFact> streamFetchOpenFoodFactApi(String barcode) {
        ServiceApi serviceApi = ServiceApi.RETROFIT.create(ServiceApi.class);
        return serviceApi.getProduct(barcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
