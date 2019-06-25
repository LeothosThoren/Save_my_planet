package com.leothos.savemyplanet.views.controlers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.views.controlers.fragments.Dashboard;
import com.leothos.savemyplanet.views.controlers.fragments.ProductListFragment;

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

        int id = getIntent().getIntExtra(BarcodeCaptureActivity.INTENT_PUT_EXTRA, 0);
        this.configureBottomNavigationView(id);

    }

    // --------------
    // Configuration
    // --------------

    private void configureBottomNavigationView(int id) {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();

        if (id == BarcodeCaptureActivity.HOME_ID) {
            this.configureFragmentContent(new Dashboard());
        } else if (id == BarcodeCaptureActivity.HISTORY_ID) {
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

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    @Override
    public boolean onKeyDown(int key_code, KeyEvent key_event) {
        if (key_code == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(key_code, key_event);
            return true;
        }

        return false;
    }
}