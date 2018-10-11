package com.leothos.savemyplanet;

import android.support.test.runner.AndroidJUnit4;

import com.leothos.savemyplanet.api.StreamApi;
import com.leothos.savemyplanet.models.OpenFoodFact;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ApiInstrumentedTest {

    @Test
    public void fetchMostPopularApi() throws Exception {

        Observable<OpenFoodFact> foodFactObservable = StreamApi.streamFetchOpenFoodFactApi("8410076421449");

        TestObserver<OpenFoodFact> testObserver = new TestObserver<>();

        foodFactObservable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();


        OpenFoodFact openFoodFact = testObserver.values().get(0);

        assertThat("Openfoodfact", openFoodFact.getStatus() == 1);
    }
}
