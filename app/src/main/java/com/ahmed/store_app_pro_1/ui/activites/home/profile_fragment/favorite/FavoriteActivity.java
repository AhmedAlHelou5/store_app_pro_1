package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityFavoriteBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.details.DetailsActivity;
import com.ahmed.store_app_pro_1.ui.adapters.FavoriteAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.models.favorite.AddProductToFavoriteModel;
import com.ahmed.store_app_pro_1.ui.models.favorite.GetProductFavoriteModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {
    ActivityFavoriteBinding binding;
    ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        // put in shared preferences value
//        SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
//        editor.putString("name", "Elena");
//        editor.apply();



        //get shared preferences value
        SharedPreferences prefs = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE);
        String token = prefs.getString(Constans.TOKEN, "");

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });





            ArrayList<ProductModel> product = new ArrayList<>();
//        Call<GetProductFavoriteModel> call = apiInterface.GetProductFromFavorite(token);
        Call<GetProductFavoriteModel> call = apiInterface.GetProductFromFavorite("Bearer 225|9gPWE0KDBkpjQozsL4jTbv1Z28IiBFEa01wZI2RR");

        call.enqueue((new Callback<GetProductFavoriteModel>() {
            @Override
            public void onResponse(Call<GetProductFavoriteModel> call, Response<GetProductFavoriteModel> response) {

                if (response.isSuccessful()) {

                    assert response.body() != null;
                    product.addAll(response.body().getData());



                    FavoriteAdapter adapter = new FavoriteAdapter(product, getBaseContext(), new OnItemClickListener() {
                        @Override
                        public void onItemClick(ProductModel product) {
                            Toast.makeText(getBaseContext(), ""+product.getName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                            intent.putExtra("productOffer",product);
                            startActivity(intent);
                        }
                    });

                    binding.rvFavoriteScreen.setAdapter(adapter);
                    binding.rvFavoriteScreen.setHasFixedSize(true);
                    binding.rvFavoriteScreen.setLayoutManager(new
                            GridLayoutManager(getBaseContext(),
                            2,
                            RecyclerView.VERTICAL,false));









                    Toast.makeText(getBaseContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());
                    Toast.makeText(getBaseContext(), "Like null", Toast.LENGTH_SHORT).show();


                }

            }
            @Override
            public void onFailure(Call<GetProductFavoriteModel> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Like onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));





//        ArrayList<ProductModel> product = Utils.FillProducts();










    }
}