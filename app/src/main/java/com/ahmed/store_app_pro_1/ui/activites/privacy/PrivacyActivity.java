package com.ahmed.store_app_pro_1.ui.activites.privacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityPrivacyBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryActivity;
import com.ahmed.store_app_pro_1.ui.activites.register.RegisterActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesFragmentAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.about.AboutAllModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryMainModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        final boolean[] isProgressVisible = {false};
        isProgressVisible[0] = true;
        binding.idPBLoading.setVisibility(View.VISIBLE);


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Call<AboutAllModel> call = apiInterface.GetAbout();

//        ArrayList<AboutAllModel> categories = new ArrayList<>();

        call.enqueue((new Callback<AboutAllModel>() {
            @Override
            public void onResponse(Call<AboutAllModel> call, Response<AboutAllModel> response) {

                if (response.isSuccessful() ){
                    assert response.body() != null;
                    isProgressVisible[0] = false;
                    binding.idPBLoading.setVisibility(View.GONE);

                    binding.tvTermsAllText.setText(response.body().getData().getTermsConditions());

                }
                else {
                    Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());


                }



            }

            @Override
            public void onFailure(Call<AboutAllModel> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));


















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


