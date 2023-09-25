package com.ahmed.store_app_pro_1.ui.activites.verfiy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityVerfiyBinding;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.reset_password.ResetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VerfiyActivity extends AppCompatActivity {
        ActivityVerfiyBinding binding;
    CountDownTimer countDownTimer;
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




//        if (binding.otpEt1.getText().toString().trim().length()==1){
//            binding.otpEt2.requestFocus();
//        }


        binding.btnSendVerifyCode.setOnClickListener(view -> {

             Intent intent=new Intent(VerfiyActivity.this, ResetPasswordActivity.class);
             startActivity(intent);
             finish();
        });
        SimpleDateFormat formatter = new SimpleDateFormat(
                "mm:ss");
       countDownTimer=  new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
//                binding.tvNumCountDown.setText(" " + millisUntilFinished / 1000);
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                binding.tvNumCountDown.setText(  String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));            }

            public void onFinish() {
                binding.tvNumCountDown.setText("");

            }
        }.start();


        binding.tvResend.setOnClickListener(view -> {
//            countDownTimer.cancel();
            countDownTimer.cancel();
            countDownTimer.onFinish();

            reverseTimer(120, binding.tvNumCountDown);

        });



    }
    public void reverseTimer(int Seconds, final TextView tv) {
//        countDownTimer.cancel();
//        countDownTimer.onFinish();


        countDownTimer=new  CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                tv.setText(  String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                binding.tvNumCountDown.setText("");

            }
        }.start();
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
                if (s.length() == 1)
                binding.otpEt2.requestFocus();


            }
        });
        binding.otpEt2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @SuppressLint("SuspiciousIndentation")
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

                if (s.length() == 1)
                    binding.otpEt3.requestFocus();

            }
        });
        binding.otpEt3.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @SuppressLint("SuspiciousIndentation")
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
                if (s.length() == 1)
                    binding.otpEt4.requestFocus();


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