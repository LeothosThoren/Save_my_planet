package com.leothos.savemyplanet.repositories;

import android.arch.lifecycle.LiveData;

import com.leothos.savemyplanet.database.dao.MyProductDao;
import com.leothos.savemyplanet.entities.MyProduct;

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

    // --- CREATE ---

    public void insertProduct(MyProduct myProduct) {
        myProductDao.insertProduct(myProduct);
    }

    // --- UPDATE ---

    public void updateProduct(MyProduct myProduct) {
        myProductDao.updateProduct(myProduct);
    }

    // --- DELETE ---

    public  void deleteProduct(String codeId) {
        myProductDao.deleteProduct(codeId);
    }

}
