package com.ahmed.store_app_pro_1.ui.activites.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityLoginBinding;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;
import com.ahmed.store_app_pro_1.ui.activites.verfiy.VerfiyActivity;


public class LoginActivity extends AppCompatActivity {
     ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this, OnBoardingTwoActivity.class);
            startActivity(intent);
            finish();
        });


        binding.btnLogin.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this, VerfiyActivity.class);
            startActivity(intent);
        });

        binding.tvLogin.setOnClickListener(
             view->{
                 Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                 startActivity(intent);
                 finish();
             }
        );






    }

    public void ShowHidePass(View view){

        if(view.getId()== R.id.show_pass_btn){

            if(binding.edTextPassword1.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
                binding.edTextPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Hide Password
                binding.edTextPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }




}