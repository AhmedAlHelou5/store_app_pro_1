package com.ahmed.store_app_pro_1.ui.activites.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.ahmed.store_app_pro_1.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
     ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



}
}