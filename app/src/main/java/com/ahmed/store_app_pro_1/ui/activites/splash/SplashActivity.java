package com.ahmed.store_app_pro_1.ui.activites.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ahmed.store_app_pro_1.databinding.ActivitySplashBinding;
import com.ahmed.store_app_pro_1.ui.activites.splash.one.OnBoardingOneActivity;

public class SplashActivity extends AppCompatActivity {
     ActivitySplashBinding binding;
     Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, OnBoardingOneActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);



    }
}