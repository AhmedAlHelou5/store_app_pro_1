package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.help_center;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityHelpCenterBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.models.about.AboutAllModel;
import com.ahmed.store_app_pro_1.ui.models.faqs.MainFaqsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpCenterActivity extends AppCompatActivity {
    ActivityHelpCenterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

//        final boolean[] isProgressVisible = {false};
//        isProgressVisible[0] = true;
//        binding.idPBLoading.setVisibility(View.VISIBLE);
//

        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Call<MainFaqsModel> call = apiInterface.GetFaqs();

//        ArrayList<AboutAllModel> categories = new ArrayList<>();

        call.enqueue((new Callback<MainFaqsModel>() {
            @Override
            public void onResponse(Call<MainFaqsModel> call, Response<MainFaqsModel> response) {

                if (response.isSuccessful() ){
                    assert response.body() != null;
                    binding.tvWhatDoesOurAppDoAllText.setText(response.body().getData().get(1).getDescription());
                    binding.tvWhatDoesOurAppDo.setText(response.body().getData().get(1).getTitle());
                    binding.tvWhatIsOurApplicationAllText.setText(response.body().getData().get(0).getDescription());
                    binding.tvWhatIsOurApplication.setText(response.body().getData().get(0).getTitle());




                }
                else {
                    Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());


                }



            }

            @Override
            public void onFailure(Call<MainFaqsModel> call, Throwable t) {
                Toast.makeText(getBaseContext(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));











        binding.lytVisible1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hiddenView = binding.lytHidden1.findViewById(R.id.lytHidden1);
                hiddenView.setVisibility( hiddenView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (hiddenView.getVisibility() == View.VISIBLE){
                    binding.lytVisible1.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                    binding.imgArrow1.setImageDrawable(getResources().getDrawable(R.drawable.arrow_top));
                    binding.tvWhatIsOurApplication.setTextColor(getResources().getColor(R.color.white));
                }
                else if(hiddenView.getVisibility() == View.GONE) {
                    binding.lytVisible1.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.imgArrow1.setImageDrawable(getResources().getDrawable(R.drawable.arrow_bottom));
                    binding.tvWhatIsOurApplication.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });


        binding.lytVisible2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hiddenView = binding.lytHidden2.findViewById(R.id.lytHidden2);
                hiddenView.setVisibility( hiddenView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (hiddenView.getVisibility() == View.VISIBLE){
                    binding.lytVisible2.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                    binding.imgArrow2.setImageDrawable(getResources().getDrawable(R.drawable.arrow_top));
                    binding.tvWhatDoesOurAppDo.setTextColor(getResources().getColor(R.color.white));
                }
                else if(hiddenView.getVisibility() == View.GONE) {
                    binding.lytVisible2.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.imgArrow2.setImageDrawable(getResources().getDrawable(R.drawable.arrow_bottom));
                    binding.tvWhatDoesOurAppDo.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });




    }
}