package com.leothos.savemyplanet.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.leothos.savemyplanet.ViewModel.ProductViewModel;
import com.leothos.savemyplanet.repositories.MyProductDataRepository;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MyProductDataRepository myProductDataSource;
    private final Executor executor;

    public ViewModelFactory(MyProductDataRepository myProductDataSource, Executor executor) {
        this.myProductDataSource = myProductDataSource;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(myProductDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
