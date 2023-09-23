package com.ahmed.store_app_pro_1.ui.activites.success_payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ahmed.store_app_pro_1.databinding.ActivitySuccessPaymentBinding;
import com.ahmed.store_app_pro_1.ui.activites.cart.CartActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;

public class SuccessPaymentActivity extends AppCompatActivity {
    ActivitySuccessPaymentBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySuccessPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnBackToHome.setOnClickListener(v -> {
            startActivity(new Intent(SuccessPaymentActivity.this, HomeActivity.class));
        });


    }
}