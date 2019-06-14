package com.leothos.savemyplanet;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.leothos.savemyplanet.data.database.MyProductDataBase;
import com.leothos.savemyplanet.data.database.entities.MyProduct;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MyProductDaoTest {

    // DATA SET FOR TEST
    private static MyProduct NEW_PRODUCT0 = new MyProduct("Nutella", "Ferreo", "Pâte à tartiner",
            "https://static.openfoodfacts.org/images/products/301/762/040/1473/front_fr.20.400.jpg",
            "1kg",1, "d", "ingrédients", Calendar.getInstance().getTime(), "3017620401473");
    private static MyProduct NEW_PRODUCT1 = new MyProduct("Gazpacho", "Alvalle", "Soupe froide",
            "https://static.openfoodfacts.org/images/products/301/762/040/1473/front_fr.20.400.jpg",
            "1L",0, "b", "ingrédients",Calendar.getInstance().getTime(), "3017620401783");
    private static MyProduct NEW_PRODUCT2 = new MyProduct("Les ficelles", "Kambly", "Apéritif",
            "https://static.openfoodfacts.org/images/products/301/762/040/1473/front_fr.20.400.jpg",
            "100g",2, "e", "ingrédients",Calendar.getInstance().getTime(), "30171240401473");

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    //Data
    private MyProductDataBase database;

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                MyProductDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void getMyProductWhenNoItemInserted() throws InterruptedException {
        // TEST
        List<MyProduct> myProducts = LiveDataTestUtil.getValue(this.database.myProductDao().getAllProducts());
        assertTrue(myProducts.isEmpty());
    }


    @Test
    public void insertAndGetMyProducts() throws InterruptedException {
        // BEFORE : Adding

        this.database.myProductDao().insertProduct(NEW_PRODUCT0);
        this.database.myProductDao().insertProduct(NEW_PRODUCT1);
        this.database.myProductDao().insertProduct(NEW_PRODUCT2);

        // TEST
        List<MyProduct> myProducts = LiveDataTestUtil.getValue(this.database.myProductDao().getAllProducts());
        assertTrue(myProducts.size() == 3);
    }

    @Test
    public void insertAndUpdateMyProduct() throws InterruptedException {
        // BEFORE : Adding. Next, update item added & re-save it
        this.database.myProductDao().insertProduct(NEW_PRODUCT0);
        MyProduct myProduct = LiveDataTestUtil.getValue(this.database.myProductDao().getAllProducts()).get(0);
        myProduct.setProductName("Pate à tartiner");
        this.database.myProductDao().updateProduct(myProduct);

        //TEST
        List<MyProduct> myProducts = LiveDataTestUtil.getValue(this.database.myProductDao().getAllProducts());
        assertEquals("Pate à tartiner", myProducts.get(0).getProductName());
    }

    @Test
    public void insertAndDeleteRealEstate() throws InterruptedException {
        // BEFORE : added & delete it.
        this.database.myProductDao().insertProduct(NEW_PRODUCT0);
        MyProduct myProduct = LiveDataTestUtil.getValue(this.database.myProductDao().getAllProducts()).get(0);
        myProduct.setProductName("Pate à tartiner");
        this.database.myProductDao().deleteProduct(myProduct.getCodeId());

        //TEST
        List<MyProduct> myProducts = LiveDataTestUtil.getValue(this.database.myProductDao().getAllProducts());
        assertTrue(myProducts.isEmpty());
    }
}
