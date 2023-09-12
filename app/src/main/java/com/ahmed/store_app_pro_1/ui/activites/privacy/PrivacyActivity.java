package com.ahmed.store_app_pro_1.ui.activites.privacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityPrivacyBinding;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;

public class PrivacyActivity extends AppCompatActivity {
    ActivityPrivacyBinding binding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");

        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        setSupportActionBar(binding.toolbar);
        binding.tvTermsAllText.setMovementMethod(new ScrollingMovementMethod());



        binding.btnAccept.setOnClickListener(view -> {
            Intent intent=new Intent(PrivacyActivity.this, RegisterActivity.class);
            intent.putExtra("result", 1);
            setResult(RESULT_OK, intent);
             PrivacyActivity.super.onBackPressed();
        });






    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_privacy,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_back){

            Intent intent=new Intent(PrivacyActivity.this, RegisterActivity.class);
            intent.putExtra("result", 0);
            setResult(RESULT_CANCELED, intent);
            startActivity(intent);
            finish();
//            Toast.makeText(this, "Click Search Icon.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }





}


