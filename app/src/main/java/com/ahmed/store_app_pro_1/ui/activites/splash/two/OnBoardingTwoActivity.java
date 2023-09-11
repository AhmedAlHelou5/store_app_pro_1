package com.ahmed.store_app_pro_1.ui.activites.splash.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ahmed.store_app_pro_1.databinding.ActivityOnBoardingTwoBinding;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.one.OnBoardingOneActivity;

public class OnBoardingTwoActivity extends AppCompatActivity {
    ActivityOnBoardingTwoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent = new Intent(OnBoardingTwoActivity.this, OnBoardingOneActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(OnBoardingTwoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.btnStartNow.setOnClickListener(view -> {
            Intent intent = new Intent(OnBoardingTwoActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });




    }
}