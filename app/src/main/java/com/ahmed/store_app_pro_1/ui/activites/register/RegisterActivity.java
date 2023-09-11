package com.ahmed.store_app_pro_1.ui.activites.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityRegisterBinding;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.privacy.PrivacyActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.one.OnBoardingOneActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private int passwordNotVisible=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent=new Intent(RegisterActivity.this, OnBoardingTwoActivity.class);
            startActivity(intent);
            finish();
        });


        binding.tvLogin.setOnClickListener(view -> {
            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
//            finish();
        });



        binding.linerCheckBoxAccept.setOnClickListener(view -> {
            Intent intent=new Intent(RegisterActivity.this, PrivacyActivity.class);
            startActivity(intent);


        }
        );



    }

    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

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

