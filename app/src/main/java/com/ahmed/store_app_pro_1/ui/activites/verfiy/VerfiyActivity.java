package com.ahmed.store_app_pro_1.ui.activites.verfiy;

import static com.ahmed.store_app_pro_1.Constans.BASE_URL;
import static com.ahmed.store_app_pro_1.Constans.MEDIA_TYPE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityVerfiyBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.reset_password.ResetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.models.users.User;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerfiyActivity extends AppCompatActivity {
    ActivityVerfiyBinding binding;
    CountDownTimer countDownTimer;
    boolean isProgressVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerfiyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("phone");
        String SMSCode = intent.getStringExtra("code");
        boolean checkForgetIntent=intent.getBooleanExtra(Constans.FORGET_CODE_CHECK,false);
        binding.tvVerfiyNumber.setText(phoneNumber);

        showSnackBar(SMSCode);

        if (binding.idPBLoading.getVisibility() == View.GONE) {
            btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

        }
        binding.iconBackOnBoarding.setOnClickListener(view ->

        {

            finish();
        });

        String number  = SMSCode.replaceAll("[^0-9]", "");
        Toast.makeText(this, ""+number, Toast.LENGTH_LONG).show();
        Log.e("TAG", "onCreate: "+number );



        binding.btnSendVerifyCode.setOnClickListener(view ->
        {

            String code1= binding.otpEt1.getText().toString().trim();
            String code2= binding.otpEt2.getText().toString().trim();
            String code3= binding.otpEt3.getText().toString().trim();
            String code4= binding.otpEt4.getText().toString().trim();
            String codeAll= code4+code3+code2+code1;
            Log.e("TAG", "onCreate: "+codeAll );
            btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));


            if (number.equals(codeAll)){
                isProgressVisible = true;

                binding.idPBLoading.setVisibility(View.VISIBLE);

                if (checkForgetIntent==false)   {

                    ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

                    Map<String, Object> map = new HashMap<>();
                    map.put("phone", phoneNumber);
                    map.put("code", codeAll);

                    Log.d("TAG", "onClick: " + map);
                    Call<User> call = apiInterface.verifyCodeAndLogin(map);


                    call.enqueue((new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(VerfiyActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();

                            if (response.isSuccessful() && response.body() != null) {
                                Log.e("TAG verifyCodeAndLogin", "onResponse:getFcm " + response.body().getData());
                                Log.e("TAG verifyCodeAndLogin", "onResponse:getFcm " + response.body().getData().getPhone());
                                Log.e("TAG verifyCodeAndLogin", "onResponse:phone " + response.body().getData().getPhone());
                                Log.e("TAG verifyCodeAndLogin", "onResponse:token " + response.body().getData().getToken());
                                Log.e("TAG verifyCodeAndLogin", "onResponse:name " + response.body().getData().getName());


                                Log.e("TAG", "onResponse: " + response.body());


//                            SharedPreferences prefs = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE);
//                            boolean checkForgetIntent = prefs.getBoolean(Constans.FORGET_CODE_CHECK, false);
//
                                    Intent intent = new Intent(VerfiyActivity.this, HomeActivity.class);

                                    // put in shared preferences value
                                    SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
                                    editor.putString(Constans.PHONE, response.body().getData().getPhone());
                                    editor.putString(Constans.TOKEN, response.body().getData().getToken());
                                    editor.putString(Constans.NAME, response.body().getData().getName());
                                    editor.putBoolean(Constans.VERIFIED, response.body().getData().isVerified());
                                    editor.apply();
                                isProgressVisible = false;

                                binding.idPBLoading.setVisibility(View.GONE);
                                    startActivity(intent);
                                    finish();





                            } else {
                                Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());
                                showSnackBar("حدث خطأ اثناء اتصالك بالانترنت");
                                isProgressVisible = false;
                                btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));

                                binding.idPBLoading.setVisibility(View.GONE);

                            }


                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(VerfiyActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("TAG", "onFailure: " + t.getMessage());
                            showSnackBar("تأكد من اتصالك بالانترنت");
                            isProgressVisible = false;
                            btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));

                            binding.idPBLoading.setVisibility(View.GONE);

                        }
                    }));


                }
                else {

                    Intent intent2 = new Intent(VerfiyActivity.this, ResetPasswordActivity.class);
                    intent2.putExtra("phone", phoneNumber);
                    intent2.putExtra("code", codeAll.toString());
                    startActivity(intent2);
                    finish();


                }





            }
            else {

                checkLengthEt();

                focusEditText();


                showSnackBar("Wrong Code");

            }








        });

        checkLengthEt();

        focusEditText();



        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat(
                "mm:ss");
        countDownTimer = new

        CountDownTimer(120000, 1000) {

                    @SuppressLint({"DefaultLocale", "SetTextI18n"})
                    public void onTick(long millisUntilFinished) {
//                binding.tvNumCountDown.setText(" " + millisUntilFinished / 1000);
                        int seconds = (int) (millisUntilFinished / 1000);
                        int minutes = seconds / 60;
                        seconds = seconds % 60;
                        binding.tvNumCountDown.setText(String.format("%02d", minutes)
                                + ":" + String.format("%02d", seconds));
                    }

                    public void onFinish() {
                        binding.tvNumCountDown.setText("");
                        binding.tvResend.setClickable(true);
                        binding.tvResend.setEnabled(true);
                        binding.tvResend.setOnClickListener(view ->

                        {

                            ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


                            Log.d("TAG", "onClick: " + phoneNumber);
                            Call<User> call = apiInterface.resendCodeOTP(phoneNumber);


                            call.enqueue((new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    Toast.makeText(VerfiyActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();

                                    if (response.isSuccessful() && response.body() != null) {
                                        Log.e("TAG", "onResponse: " + response.body().getMessage());

                                        // put in shared preferences value
                                        SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
                                        editor.putString("phone", phoneNumber);
                                        editor.putString("code", response.body().getMessage());
                                        editor.apply();

                                        showSnackBar(response.body().getMessage());


                                    } else {
                                        Log.e("TAG", "onResponse: null " + response.body());


                                    }


                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Toast.makeText(VerfiyActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.e("TAG", "onFailure: " + t.getMessage());


                                }
                            }));


                            countDownTimer.cancel();
                            countDownTimer.onFinish();

                            reverseTimer(120, binding.tvNumCountDown);

                        });

                    }
                }.

                start();
        if (!binding.tvNumCountDown.getText().toString().isEmpty() || !binding.tvNumCountDown.getText().toString().equals("")) {
            binding.tvResend.setClickable(false);
            binding.tvResend.setEnabled(false);
            Handler handler = new Handler();
            binding.tvWaitUntilCountEnd.setText("إنتظر حتى انتهاء الوقت المقدر لارسال الرسالة");

            Runnable r = new Runnable() {
                public void run() {
                    binding.tvWaitUntilCountEnd.setText("");
                }
            };
            handler.postDelayed(r, 3000);

        }



    }


    void btnNewRegisterIsActive(boolean isEnable, Drawable drawable) {
        binding.btnSendVerifyCode.setEnabled(isEnable);
        binding.btnSendVerifyCode.setClickable(isEnable);
        binding.btnSendVerifyCode.setBackground(drawable);

    }

    private void showSnackBar(String SMSCode) {

        Snackbar snack = Snackbar.make(binding.getRoot(), SMSCode, 10000);
        View view1 = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
        params.gravity = Gravity.TOP;
        view1.setLayoutParams(params);
        snack.show();
    }


    public void reverseTimer(int Seconds, final TextView tv) {
//        countDownTimer.cancel();
//        countDownTimer.onFinish();


        countDownTimer = new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                binding.tvNumCountDown.setText("");

            }
        }.start();
    }

    void btnSendIsActive(boolean isEnable, Drawable drawable) {
        binding.btnSendVerifyCode.setEnabled(isEnable);
        binding.btnSendVerifyCode.setClickable(isEnable);
        binding.btnSendVerifyCode.setBackground(drawable);

    }

    void checkLengthEt() {

        binding.otpEt1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @SuppressLint({"UseCompatLoadingForDrawables", "SuspiciousIndentation"})
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.length() >= 1) {
                    btnSendIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));
                } else {
                    if (binding.otpEt1.getText().toString().isEmpty() && binding.otpEt2.getText().toString().isEmpty() && binding.otpEt3.getText().toString().isEmpty() && binding.otpEt4.getText().toString().isEmpty())
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

                } else {
                    if (binding.otpEt1.getText().toString().isEmpty() && binding.otpEt2.getText().toString().isEmpty() && binding.otpEt3.getText().toString().isEmpty() && binding.otpEt4.getText().toString().isEmpty())
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

                } else {
                    if (binding.otpEt1.getText().toString().isEmpty() && binding.otpEt2.getText().toString().isEmpty() && binding.otpEt3.getText().toString().isEmpty() && binding.otpEt4.getText().toString().isEmpty())
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


                } else {
                    if (binding.otpEt1.getText().toString().isEmpty() && binding.otpEt2.getText().toString().isEmpty() && binding.otpEt3.getText().toString().isEmpty() && binding.otpEt4.getText().toString().isEmpty())
                        btnSendIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    void focusEditText() {

        binding.otpEt1.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus) {

                        binding.otpEt1.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt1.setTextColor(getResources().getColor(R.color.white));

                    } else {


                        binding.otpEt1.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt1.setTextColor(getResources().getColor(R.color.black));
                    }


                }
        );


        binding.otpEt2.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus) {

                        binding.otpEt2.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt2.setTextColor(getResources().getColor(R.color.white));

                    } else {


                        binding.otpEt2.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt2.setTextColor(getResources().getColor(R.color.black));
                    }


                }
        );
        binding.otpEt3.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus) {

                        binding.otpEt3.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt3.setTextColor(getResources().getColor(R.color.white));

                    } else {


                        binding.otpEt3.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt3.setTextColor(getResources().getColor(R.color.black));
                    }


                }
        );
        binding.otpEt4.setOnFocusChangeListener(
                (v, hasFocus) -> {


                    if (hasFocus) {

                        binding.otpEt4.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));
                        binding.otpEt4.setTextColor(getResources().getColor(R.color.white));

                    } else {


                        binding.otpEt4.setBackground(getResources().getDrawable(R.drawable.bg_btn_sign_google));
                        binding.otpEt4.setTextColor(getResources().getColor(R.color.black));
                    }


                }
        );


    }


}