package com.leothos.savemyplanet.injections;

import android.content.Context;

import com.leothos.savemyplanet.data.database.MyProductDataBase;
import com.leothos.savemyplanet.data.repositories.MyProductDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static MyProductDataRepository provideProductDataSource(Context context) {
        MyProductDataBase database = MyProductDataBase.getInstance(context);
        return new MyProductDataRepository(database.myProductDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        MyProductDataRepository dataSourceProduct = provideProductDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceProduct, executor);
    }
}
