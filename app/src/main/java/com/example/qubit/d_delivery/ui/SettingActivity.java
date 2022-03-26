package com.example.qubit.d_delivery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.qubit.d_delivery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {

    BottomNavigationView navBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");
    }
}