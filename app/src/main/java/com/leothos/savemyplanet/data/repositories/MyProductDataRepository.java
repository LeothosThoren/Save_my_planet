package com.leothos.savemyplanet.data.repositories;

import android.arch.lifecycle.LiveData;

import com.leothos.savemyplanet.data.database.dao.MyProductDao;
import com.leothos.savemyplanet.data.database.entities.MyProduct;

import java.util.List;

public class MyProductDataRepository {

    private final MyProductDao myProductDao;

    public MyProductDataRepository(MyProductDao myProductDao) {
        this.myProductDao = myProductDao;
    }

    // --- GET ---

    public LiveData<List<MyProduct>> getAllProducts() {
        return this.myProductDao.getAllProducts();
    }

    // --- SEARCH ---

    public LiveData<List<MyProduct>> searchProducts(String productName, String brand,
                                                    Integer palmOilIndMin, Integer palmOilIndMax) {
        return this.myProductDao.searchProducts(productName, brand, palmOilIndMin, palmOilIndMax);
    }

    // --- CREATE ---

    public void insertProduct(MyProduct myProduct) {
        myProductDao.insertProduct(myProduct);
    }

    // --- UPDATE ---

    public void updateProduct(MyProduct myProduct) {
        myProductDao.updateProduct(myProduct);
    }

    // --- DELETE ---

    public void deleteProduct(String codeId) {
        myProductDao.deleteProduct(codeId);
    }

}
