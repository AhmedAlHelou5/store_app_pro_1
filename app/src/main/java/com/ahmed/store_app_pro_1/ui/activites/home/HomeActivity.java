package com.ahmed.store_app_pro_1.ui.activites.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ahmed.store_app_pro_1.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}