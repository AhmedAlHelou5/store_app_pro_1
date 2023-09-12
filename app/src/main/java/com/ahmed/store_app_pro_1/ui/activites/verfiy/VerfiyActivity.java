package com.ahmed.store_app_pro_1.ui.activites.verfiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityVerfiyBinding;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;

public class VerfiyActivity extends AppCompatActivity {
        ActivityVerfiyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerfiyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        if (TextUtils.isEmpty(binding.otpEt1.getText().toString()) && TextUtils.isEmpty(binding.otpEt2.getText().toString()) && TextUtils.isEmpty(binding.otpEt3.getText().toString()) && TextUtils.isEmpty(binding.otpEt4.getText().toString())) {
//            btnSendIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));
//        }
//        else {
//            btnSendIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));
//        }


        checkLengthEt(binding.otpEt1);
        checkLengthEt(binding.otpEt2);
        checkLengthEt(binding.otpEt3);
        checkLengthEt(binding.otpEt4);






        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent=new Intent(VerfiyActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });






    }


    void btnSendIsActive(boolean isEnable, Drawable drawable){
        binding.btnSendVerifyCode.setEnabled(isEnable);
        binding.btnSendVerifyCode.setClickable(isEnable);
        binding.btnSendVerifyCode.setBackground(drawable);

    }

    void  checkLengthEt(EditText view){
        view.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {
                    btnSendIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));
                }
                else {
                    if (binding.otpEt1.getText().toString().isEmpty()&& binding.otpEt2.getText().toString().isEmpty()&& binding.otpEt3.getText().toString().isEmpty()&& binding.otpEt4.getText().toString().isEmpty())
                    btnSendIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



}