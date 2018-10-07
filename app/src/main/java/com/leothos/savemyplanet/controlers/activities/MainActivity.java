package com.leothos.savemyplanet.controlers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.controlers.fragments.Dashboard;
import com.leothos.savemyplanet.controlers.fragments.ProductListFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                this.configureFragmentContent(new Dashboard());
                item.setChecked(true);
                return true;
            case R.id.navigation_scan:
                this.startActivity(BarcodeCaptureActivity.class);
                return true;
            case R.id.navigation_history:
                this.configureFragmentContent(new ProductListFragment());
                item.setChecked(true);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        this.configureFragmentContent(new Dashboard());

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
