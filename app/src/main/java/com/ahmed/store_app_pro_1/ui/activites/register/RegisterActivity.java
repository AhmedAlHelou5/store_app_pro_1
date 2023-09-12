package com.ahmed.store_app_pro_1.ui.activites.register;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityRegisterBinding;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.privacy.PrivacyActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.one.OnBoardingOneActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnNewRegisterIsActive( false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == 1 || result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Log.d("TAG", "onActivityResult: " + intent);
                            if (intent != null) {
                                binding.checkBoxAccept.setActivated(true);
                                binding.checkBoxAccept.isChecked();
                                binding.checkBoxAccept.setChecked(true);
                                binding.tvCheckedTerms.setTextColor(getResources().getColor(R.color.primary_color));
                                Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));

                            }

                        }



                    }

    }
        );








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
           activityResultLauncher.launch(new Intent(RegisterActivity.this, PrivacyActivity.class));



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




    void btnNewRegisterIsActive(boolean isEnable, Drawable drawable){
        binding.btnNewRegister.setEnabled(isEnable);
        binding.btnNewRegister.setClickable(isEnable);
        binding.btnNewRegister.setBackground(drawable);

    }

}

