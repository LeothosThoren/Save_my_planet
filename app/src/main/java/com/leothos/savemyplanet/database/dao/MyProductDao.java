package com.leothos.savemyplanet.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.leothos.savemyplanet.entities.MyProduct;

import java.util.List;

@Dao
public interface MyProductDao {

    @Query("SELECT * FROM MyProduct ORDER BY timestamp ASC")
    LiveData<List<MyProduct>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertProduct(MyProduct myProduct);

    @Update
    int updateProduct(MyProduct myProduct);

    @Query("DELETE FROM MyProduct WHERE codeId = :productId")
    int deleteProduct(String productId);
}
