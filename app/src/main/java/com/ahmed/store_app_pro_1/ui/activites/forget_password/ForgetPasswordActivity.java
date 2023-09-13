package com.ahmed.store_app_pro_1.ui.activites.forget_password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityForgetPasswordBinding;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.reset_password.ResetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.verfiy.VerfiyActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent=new Intent(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        binding.btnSendForgetPassword.setOnClickListener(view -> {
            Intent intent=new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
            finish();
        });



    }




}