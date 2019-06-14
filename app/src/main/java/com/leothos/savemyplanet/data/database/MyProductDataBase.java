package com.leothos.savemyplanet.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.leothos.savemyplanet.data.database.dao.MyProductDao;
import com.leothos.savemyplanet.data.database.entities.MyProduct;

@Database(entities = {MyProduct.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class MyProductDataBase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile MyProductDataBase INSTANCE;

    // --- INSTANCE ---
    public static MyProductDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MyProductDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyProductDataBase.class, "SMPDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // --- DAO ---
    public abstract MyProductDao myProductDao();

}
