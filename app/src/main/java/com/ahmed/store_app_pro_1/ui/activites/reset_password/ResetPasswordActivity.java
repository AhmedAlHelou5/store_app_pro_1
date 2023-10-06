package com.ahmed.store_app_pro_1.ui.activites.reset_password;

import static com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity.isValidPassword;

import androidx.annotation.NonNull;
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
import com.ahmed.store_app_pro_1.databinding.ActivityResetPasswordBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.forget_password.ForgetPasswordActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.verfiy.VerfiyActivity;
import com.ahmed.store_app_pro_1.ui.models.users.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    boolean isProgressVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("phone");
        String SMSCode = intent.getStringExtra("code");

//        if (binding.idPBLoading.getVisibility() == View.VISIBLE) {
//            btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));
//
//        }
        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent2=new Intent(ResetPasswordActivity.this, ForgetPasswordActivity.class);
            startActivity(intent2);
            finish();
        });



        binding.btnSendForgetPassword.setOnClickListener(view -> {


        if (isValidFeilds()){
            isProgressVisible = true;
            btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

            binding.idPBLoading.setVisibility(View.VISIBLE);
            ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
            String newPass=binding.edTextPassword1.getText().toString().trim();
            String ConfirmNewPass=binding.edTextPassword2.getText().toString().trim();





            Map<String, Object> map = new HashMap<>();
            map.put("phone", phoneNumber);
            map.put("code", SMSCode);
            map.put("new_password", newPass);
            map.put("new_password_confirmation", ConfirmNewPass);

            Log.e("TAG", "onCreate map map: "+map );




            Call<User> call = apiInterface.forgetPassword(map);


//            Log.e("TAG", "call " + user);


            call.enqueue((new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        Toast.makeText(ResetPasswordActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();
                        Log.e("TAG", "onResponse:getFcm " + response.body().getData());
                        Log.e("TAG", "onResponse:getFcm " + response.body().getData().getPhone());

                        Log.e("TAG", "onResponse: " + response.body());
                        Log.e("TAG", "onResponse: " + response.body().getMessage());
                        Log.e("TAG", "onResponse: " + response.body().getStatus());

                        Log.e("TAG", "onCreate map map: "+response.body() );


                        showSnackBar(response.body().getMessage());

                            Intent intent= new Intent(ResetPasswordActivity.this, LoginActivity.class);
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




                    } else {
                        Log.e("TAG", "onResponse: null " + response.body());
                        showSnackBar("حدث خطأ اثناء اتصالك بالانترنت");

                        btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));

                        isProgressVisible = false;

                        binding.idPBLoading.setVisibility(View.GONE);
                    }



                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(ResetPasswordActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
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











            
//           startActivity(new Intent(ResetPasswordActivity.this, HomeActivity.class));
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






    public boolean isValidFeilds() {
        ArrayList<Boolean> isValid = new ArrayList<>();



        if (isValidPassword(binding.edTextPassword1.getText().toString()) == false) {

            binding.edTextPassword1.setError(getString(R.string.msg_week_passwrod));
            Log.e("TAG", "password.length() <= 6: ");

            isValid.add(false);
        }


        if (isValidPassword(binding.edTextPassword2.getText().toString()) == false) {

            binding.edTextPassword2.setError(getString(R.string.msg_week_passwrod));
            Log.e("TAG", "password.length() <= 6: ");

            isValid.add(false);
        }



//        if (TextUtils.isEmpty(binding.edTextPassword1.getText().toString())) {
//            Log.e("TAG", "TextUtils.isEmpty(password): ");
//
//            binding.edTextPassword1.setError(getString(R.string.required));
//            isValid.add(false);
//
//        }


        if (isValid.contains(false)) {
            return false;
        }
        return true;


    }






}