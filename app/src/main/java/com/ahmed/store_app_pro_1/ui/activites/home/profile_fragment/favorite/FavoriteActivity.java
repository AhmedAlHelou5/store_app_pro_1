package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityFavoriteBinding;
import com.ahmed.store_app_pro_1.ui.adapters.FavoriteAdapter;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    ActivityFavoriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });





//        ArrayList<ProductModel> product = Utils.FillProducts();
//
//        FavoriteAdapter adapter = new FavoriteAdapter(Utils.getFavoritesModel());
//
//        binding.rvFavoriteScreen.setAdapter(adapter);
        binding.rvFavoriteScreen.setHasFixedSize(true);
        binding.rvFavoriteScreen.setLayoutManager(new
                GridLayoutManager(getBaseContext(),
                2,
                RecyclerView.VERTICAL,false));








    }
}