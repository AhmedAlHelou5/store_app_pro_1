package com.ahmed.store_app_pro_1.ui.activites.login;

import static com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity.isValidPassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityLoginBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.forget_password.ForgetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;
import com.ahmed.store_app_pro_1.ui.models.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
//            Intent intent=new Intent(LoginActivity.this, VerfiyActivity.class);
//            startActivity(intent);
//            if (isValidFeilds()) {
//
//            }
//
//
//
//
            Map<String, Object> map = new HashMap<>();
            map.put("phone", binding.edTextPhone.getText().toString());
            map.put("password", binding.edTextPassword1.getText().toString());


            ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


            Call<User> call = apiInterface.loginUser(map);


//            Log.e("TAG", "call " + user);


            call.enqueue((new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()&&response.body()!=null) {

                        Toast.makeText(LoginActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();
                        Log.e("TAG", "onResponse:getFcm " + response.body().getData());
                        Log.e("TAG", "onResponse:getFcm " + response.body().getData().getPhone());

                        Log.e("TAG", "onResponse: " + response.body());
                        Log.e("TAG", "onResponse: " + response.body().getMessage());
                        Log.e("TAG", "onResponse: " + response.body().getStatus());

                    }
                    else {
                        Log.e("TAG", "onResponse: null " + response.body().getStatus());

                    }

//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("TAG", "onFailure: " + t.getMessage());


                }
            }));

//                sendDataToServer(user);
//            Toast.makeText(LoginActivity.this, "sendDataToServer", Toast.LENGTH_SHORT).show();










        });

        binding.tvLogin.setOnClickListener(
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