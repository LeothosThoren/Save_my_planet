package com.leothos.savemyplanet.controlers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.controlers.fragments.Dashboard;
import com.leothos.savemyplanet.controlers.fragments.ProductListFragment;

import static com.leothos.savemyplanet.controlers.activities.BarcodeCaptureActivity.HISTORY_ID;
import static com.leothos.savemyplanet.controlers.activities.BarcodeCaptureActivity.HOME_ID;
import static com.leothos.savemyplanet.controlers.activities.BarcodeCaptureActivity.INTENT_PUT_EXTRA;

public class MainActivity extends AppCompatActivity {

    // Constant
    public static final int HISTORY_ITEM = 2;
    // Var
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                this.configureFragmentContent(new Dashboard());
                return true;
            case R.id.navigation_scan:
                this.startActivity(BarcodeCaptureActivity.class);
                return true;
            case R.id.navigation_history:
                this.configureFragmentContent(new ProductListFragment());
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int id = getIntent().getIntExtra(INTENT_PUT_EXTRA, 0);
        this.configureBottomNavigationView(id);

    }

    // --------------
    // Configuration
    // --------------

    private void configureBottomNavigationView(int id) {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();

        if (id == HOME_ID) {
            this.configureFragmentContent(new Dashboard());
        } else if (id == HISTORY_ID) {
            this.configureFragmentContent(new ProductListFragment());

            MenuItem menuItem = menu.getItem(HISTORY_ITEM);
            menuItem.setChecked(true);
        }
    }

    private void startActivity(Class activity) {
        Intent i = new Intent(this, activity);
        startActivity(i);
    }

    // Launch fragments
    private void configureFragmentContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment).commit();
    }


}
