package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.contact_us;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityContactUsBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.models.about.AboutAllModel;
import com.ahmed.store_app_pro_1.ui.models.about.ContactUsModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {
    ActivityContactUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });


        final boolean[] isProgressVisible = {false};

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        isProgressVisible[0] = true;
        binding.idPBLoading.setVisibility(View.GONE);

        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);



//        ArrayList<AboutAllModel> categories = new ArrayList<>();
        binding.btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<>();
                map.put("name",binding.edTextFullName.getText().toString());
                map.put("email",binding.edTextEmail.getText().toString());
                map.put("phone",binding.edTextPhone.getText().toString());
                map.put("message",binding.edTextFeedback.getText().toString());

                Call<ContactUsModel> call = apiInterface.ContactUs(map);

                call.enqueue((new Callback<ContactUsModel>() {
                    @Override
                    public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {
                        isProgressVisible[0] = true;
                        binding.idPBLoading.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            isProgressVisible[0] = false;
                            binding.idPBLoading.setVisibility(View.GONE);
                            builder.setMessage(response.body().getMessage())
                                    .setCancelable(false)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();

                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle(R.string.contact_us);
                            alert.show();
                        } else {
                            Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());


                        }


                    }

                    @Override
                    public void onFailure(Call<ContactUsModel> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("TAG", "onFailure: " + t.getMessage());


                    }
                }));

            } });











    }
}