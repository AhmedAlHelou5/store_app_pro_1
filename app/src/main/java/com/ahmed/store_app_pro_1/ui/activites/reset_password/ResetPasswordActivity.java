package com.ahmed.store_app_pro_1.ui.activites.reset_password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityResetPasswordBinding;
import com.ahmed.store_app_pro_1.ui.activites.forget_password.ForgetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent=new Intent(ResetPasswordActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
            finish();
        });


        binding.btnSendForgetPassword.setOnClickListener(view -> {
           startActivity(new Intent(ResetPasswordActivity.this, HomeActivity.class));
        });



    }


    public void ShowHidePass1(View view){

        if(view.getId()== R.id.show_pass_btn1){

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

    public void ShowHidePass2(View view){

        if(view.getId()==R.id.show_pass_btn2){

            if(binding.edTextPassword2.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
                binding.edTextPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Hide Password
                binding.edTextPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }










}