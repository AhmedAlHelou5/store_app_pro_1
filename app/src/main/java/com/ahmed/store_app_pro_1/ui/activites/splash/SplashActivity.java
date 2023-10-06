package com.ahmed.store_app_pro_1.ui.activites.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.databinding.ActivitySplashBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.one.OnBoardingOneActivity;

public class SplashActivity extends AppCompatActivity {
     ActivitySplashBinding binding;
     Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences prefs = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean("remember", false);
        String token = prefs.getString(Constans.TOKEN, "");


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                if (rememberMe==false) {
//                    Intent intent=new Intent(SplashActivity.this, OnBoardingOneActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else{

                    if (token.equals("")||token==null && rememberMe==false) {
                        Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else{


                        Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

//                }

//
//                Intent intent=new Intent(SplashActivity.this, OnBoardingOneActivity.class);
//                startActivity(intent);
//                finish();
            }
        },3000);



    }
}