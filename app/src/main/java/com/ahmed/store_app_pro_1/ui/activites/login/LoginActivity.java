package com.ahmed.store_app_pro_1.ui.activites.login;

import static com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity.isValidPassword;

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

import androidx.appcompat.app.AppCompatActivity;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityLoginBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.forget_password.ForgetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;
import com.ahmed.store_app_pro_1.ui.activites.verfiy.VerfiyActivity;
import com.ahmed.store_app_pro_1.ui.models.users.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
     ActivityLoginBinding binding;
    boolean isProgressVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences prefs = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean("remember", false);
        String token = prefs.getString(Constans.TOKEN, "");

        isProgressVisible = false;

        binding.idPBLoading.setVisibility(View.GONE);

        if (rememberMe && !TextUtils.isEmpty(token)) {
                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }


        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this, OnBoardingTwoActivity.class);
            startActivity(intent);
            finish();
        });
        if (binding.idPBLoading.getVisibility() == View.VISIBLE) {
            btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

        }

        binding.btnLogin.setOnClickListener(view -> {



            if (isValidFeilds()) {
                isProgressVisible = true;
                btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

                binding.idPBLoading.setVisibility(View.VISIBLE);
                Map<String, Object> map = new HashMap<>();
                map.put("phone", binding.edTextPhone.getText().toString());
                map.put("password", binding.edTextPassword1.getText().toString());


                ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


                Call<User> call = apiInterface.loginUser(map);


//            Log.e("TAG", "call " + user);


                call.enqueue((new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            Toast.makeText(LoginActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();
                            Log.e("TAG", "onResponse:getFcm " + response.body().getData());
                            Log.e("TAG", "onResponse:getFcm " + response.body().getData().getPhone());

                            Log.e("TAG", "onResponse: " + response.body());
                            Log.e("TAG", "onResponse: " + response.body().getMessage());
                            Log.e("TAG", "onResponse: " + response.body().getStatus());


                            if (!response.body().getData().isVerified()){

                                Intent intent= new Intent(LoginActivity.this, VerfiyActivity.class);
                                intent.putExtra(Constans.PHONE, binding.edTextPhone.getText().toString());
                                intent.putExtra(Constans.CODE, response.body().getMessage());
                                startActivity(intent);

                            }
                            else {
                                Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
                                SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
                                editor.putString(Constans.PHONE, response.body().getData().getPhone() );
                                editor.putString(Constans.TOKEN, response.body().getData().getToken() );
                                editor.putString(Constans.NAME, response.body().getData().getName() );
                                editor.putBoolean(Constans.VERIFIED, response.body().getData().isVerified() );
                                editor.apply();
                                isProgressVisible = false;

                                binding.idPBLoading.setVisibility(View.GONE);
                                startActivity(intent);
                                finish();

                            }


                        } else {
                            Log.e("TAG", "onResponse: null " + response.body());
                            showSnackBar("حدث خطأ اثناء اتصالك بالانترنت");
                            isProgressVisible = false;
                            btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));


                            binding.idPBLoading.setVisibility(View.GONE);

                        }

//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        showSnackBar("تأكد من اتصالك بالانترنت");
                        isProgressVisible = false;
                        btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));

                        binding.idPBLoading.setVisibility(View.GONE);

                    }
                }));

//                sendDataToServer(user);
//            Toast.makeText(LoginActivity.this, "sendDataToServer", Toast.LENGTH_SHORT).show();


            }




        });

        binding.tvRegister.setOnClickListener(
             view->{
                 Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                 startActivity(intent);
                 finish();
             }
        );


        binding.tvForgetPassword.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
            finish();
        });



        if (binding.checkBoxAccept.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
            editor.putBoolean("remember",true  );
            editor.apply();
        }
        else {
            SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
            editor.putBoolean("remember",false  );
            editor.apply();

        }





    }







    void btnNewRegisterIsActive(boolean isEnable, Drawable drawable) {
        binding.btnLogin.setEnabled(isEnable);
        binding.btnLogin.setClickable(isEnable);
        binding.btnLogin.setBackground(drawable);

    }








    private void showSnackBar(String SMSCode) {

        Snackbar snack = Snackbar.make(binding.getRoot(), SMSCode, 10000);
        View view1 = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
        params.gravity = Gravity.TOP;
        view1.setLayoutParams(params);
        snack.show();
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


    public boolean isValidFeilds() {
        ArrayList<Boolean> isValid = new ArrayList<>();


        if (!binding.edTextPhone.getText().toString().startsWith("972")) {
            binding.edTextPhone.setError(getString(R.string.msg_phone_number_start_with));
            isValid.add(false);
            Log.e("TAG", "!phoneNumber.startsWith(\"972\": ");

        }

        if (isValidPassword(binding.edTextPassword1.getText().toString()) == false) {

            binding.edTextPassword1.setError(getString(R.string.msg_week_passwrod));
            Log.e("TAG", "password.length() <= 6: ");

            isValid.add(false);
        }



        if (TextUtils.isEmpty(binding.edTextPhone.getText().toString())) {
            binding.edTextPhone.setError(getString(R.string.required));
            Log.e("TAG", "TextUtils.isEmpty(phoneNumber): ");

            isValid.add(false);

        }
        if (TextUtils.isEmpty(binding.edTextPassword1.getText().toString())) {
            Log.e("TAG", "TextUtils.isEmpty(password): ");

            binding.edTextPassword1.setError(getString(R.string.required));
            isValid.add(false);

        }


        if (isValid.contains(false)) {
            return false;
        }
        return true;


    }


}