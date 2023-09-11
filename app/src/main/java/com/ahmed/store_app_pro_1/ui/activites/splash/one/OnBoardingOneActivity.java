package com.ahmed.store_app_pro_1.ui.activites.splash.one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ahmed.store_app_pro_1.databinding.ActivityOnBoardingOneBinding;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;

public class OnBoardingOneActivity extends AppCompatActivity {
//    ActivityOnBoardingOneBinding binding;
    ActivityOnBoardingOneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnBoardingOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnNext.setOnClickListener(view -> {
            Intent intent=new Intent(OnBoardingOneActivity.this, OnBoardingTwoActivity.class);
            startActivity(intent);
            finish();
        });
    }
}