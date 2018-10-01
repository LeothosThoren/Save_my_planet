package com.leothos.savemyplanet.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.leothos.savemyplanet.entities.MyProduct;
import com.leothos.savemyplanet.repositories.MyProductDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class ProductViewModel extends ViewModel {

    // REPOSITORY
    private final MyProductDataRepository mProductDataRepository;
    private final Executor mExecutor;

    public ProductViewModel(MyProductDataRepository productDataRepository, Executor executor) {
        mProductDataRepository = productDataRepository;
        mExecutor = executor;
    }

    // --- GET ---

    public LiveData<List<MyProduct>> getAllProducts() {
        return mProductDataRepository.getAllProducts();
    }

    // --- CREATE ---

    public void insertProduct(MyProduct myProduct) {
        mExecutor.execute(() -> mProductDataRepository.insertProduct(myProduct));
    }

    // --- UPDATE ---

    public void updateProduct(MyProduct myProduct) {
        mExecutor.execute(() -> mProductDataRepository.updateProduct(myProduct));
    }

    // --- DELETE ---

    public void deleteProduct(String codeId) {
        mExecutor.execute(() -> mProductDataRepository.deleteProduct(codeId));
    }

}
