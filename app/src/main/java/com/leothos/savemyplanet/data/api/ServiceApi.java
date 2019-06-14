package com.leothos.savemyplanet.data.api;

import com.leothos.savemyplanet.data.api.models.OpenFoodFact;


import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi {

    String baseUri = "https://ssl-api.openfoodfacts.org/api/v0/product/";

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(baseUri)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    //OpenFoodFact api
    @GET("{barcode}.json")
    Observable<OpenFoodFact> getProduct(@Path("barcode") String barcode);
}
