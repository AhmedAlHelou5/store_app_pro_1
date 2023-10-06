package com.ahmed.store_app_pro_1.ui.activites.forget_password;

import static com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity.isValidPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityForgetPasswordBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.reset_password.ResetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.verfiy.VerfiyActivity;
import com.ahmed.store_app_pro_1.ui.models.users.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    boolean isProgressVisible = false;


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
//        if (binding.idPBLoading.getVisibility() == View.VISIBLE) {
//
//        }

        binding.btnSendForgetPassword.setOnClickListener(view -> {


            if (isValidFeilds()){
                isProgressVisible = true;
                btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

                binding.idPBLoading.setVisibility(View.VISIBLE);
                ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


                Log.d("TAG", "onClick: " + binding.edTextPhone.getText().toString().trim());

                Call<User> call = apiInterface.resendCodeOTP(binding.edTextPhone.getText().toString().trim());


                call.enqueue((new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(ForgetPasswordActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();

                        if (response.isSuccessful() && response.body() != null) {
                            Log.e("TAG", "onResponse: " + response.body().getMessage());

                            // put in shared preferences value
//                            SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
//                            editor.putString(Constans.PHONE, response.body().getData().getPhone());
//                            editor.putString(Constans.CODE, response.body().getMessage());
//                            editor.putBoolean(Constans.FORGET_CODE_CHECK,true);
//                            editor.apply();

                            Intent intent=new Intent(ForgetPasswordActivity.this, VerfiyActivity.class);
                            intent.putExtra("phone", binding.edTextPhone.getText().toString().trim());
                            intent.putExtra("code",response.body().getMessage());
                            intent.putExtra(Constans.FORGET_CODE_CHECK,true);
                            isProgressVisible = false;

                            binding.idPBLoading.setVisibility(View.GONE);
                            startActivity(intent);


                            showSnackBar(response.body().getMessage());


                        } else {
                            Log.e("TAG", "onResponse: null " + response.body());
                            showSnackBar("حدث خطأ اثناء اتصالك بالانترنت");
                            btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));


                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(ForgetPasswordActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        showSnackBar("تأكد من اتصالك بالانترنت");
                        btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));

                        isProgressVisible = false;

                        binding.idPBLoading.setVisibility(View.GONE);
                    }
                }));




            }







        });



    }




    void btnNewRegisterIsActive(boolean isEnable, Drawable drawable) {
        binding.btnSendForgetPassword.setEnabled(isEnable);
        binding.btnSendForgetPassword.setClickable(isEnable);
        binding.btnSendForgetPassword.setBackground(drawable);

    }









    private void showSnackBar(String SMSCode) {

        Snackbar snack = Snackbar.make(binding.getRoot(), SMSCode, 10000);
        View view1 = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
        params.gravity = Gravity.TOP;
        view1.setLayoutParams(params);
        snack.show();
    }


    public boolean isValidFeilds() {
        ArrayList<Boolean> isValid = new ArrayList<>();


        if (!binding.edTextPhone.getText().toString().startsWith("972")) {
            binding.edTextPhone.setError(getString(R.string.msg_phone_number_start_with));
            isValid.add(false);
            Log.e("TAG", "!phoneNumber.startsWith(\"972\": ");

        }


        if (TextUtils.isEmpty(binding.edTextPhone.getText().toString())) {
            binding.edTextPhone.setError(getString(R.string.required));
            Log.e("TAG", "TextUtils.isEmpty(phoneNumber): ");

            isValid.add(false);

        }


        if (isValid.contains(false)) {
            return false;
        }
        return true;


    }



}