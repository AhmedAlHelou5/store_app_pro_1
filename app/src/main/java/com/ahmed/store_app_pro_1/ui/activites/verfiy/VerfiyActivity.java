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

        checkLengthEt();
        focusEditText();

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

    void  checkLengthEt(){

        binding.otpEt1.addTextChangedListener(new TextWatcher() {

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
        binding.otpEt2.addTextChangedListener(new TextWatcher() {

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
        binding.otpEt3.addTextChangedListener(new TextWatcher() {

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
        binding.otpEt4.addTextChangedListener(new TextWatcher() {

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

    void focusEditText(){

        binding.otpEt1.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus){

                        binding.otpEt1.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt1.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {


                        binding.otpEt1.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt1.setTextColor(getResources().getColor(R.color.black));
                    }





                }
        );


        binding.otpEt2.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus){

                        binding.otpEt2.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt2.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {


                        binding.otpEt2.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt2.setTextColor(getResources().getColor(R.color.black));
                    }





                }
        );
        binding.otpEt3.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus){

                        binding.otpEt3.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt3.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {


                        binding.otpEt3.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt3.setTextColor(getResources().getColor(R.color.black));
                    }





                }
        );
        binding.otpEt4.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus){

                        binding.otpEt4.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt4.setTextColor(getResources().getColor(R.color.white));

                    }
                    else {


                        binding.otpEt4.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt4.setTextColor(getResources().getColor(R.color.black));
                    }





                }
        );




    }



}