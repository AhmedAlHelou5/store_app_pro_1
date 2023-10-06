package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.privacy_policy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityPrivacyPolicyBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.models.about.AboutAllModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyActivity extends AppCompatActivity {
    ActivityPrivacyPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.tvPrivacyPolicyText.setMovementMethod(new ScrollingMovementMethod());



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

                    binding.tvPrivacyPolicyText.setText(response.body().getData().getTermsConditions());

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











    }
}