package com.ahmed.store_app_pro_1.ui.activites.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityCartBinding;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductCartAdapter;
import com.ahmed.store_app_pro_1.ui.models.CartModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );

        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });


        setSupportActionBar(binding.toolbar);


        ArrayList<CartModel> cartModels = Utils.FillCart();

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        ArrayList<Integer> imageList = extras.getIntegerArrayList("images");
//        ArrayList<Integer> colors = extras.getIntegerArrayList("colors");
//        String title = extras.getString("title");
//        String price = extras.getString("price");
//        String description = extras.getString("description");
//        boolean like = extras.getBoolean("isLike");
//        int image = extras.getInt("image");
//        int count = extras.getInt("count");



        ProductCartAdapter allOffersAdapter = new ProductCartAdapter(cartModels);


        binding.rvItemsCart.setAdapter(allOffersAdapter);
        binding.rvItemsCart.setHasFixedSize(true);
        binding.rvItemsCart.setClipToPadding(false);
        binding.rvItemsCart.setClipChildren(false);

        binding.rvItemsCart.setLayoutManager(new
                LinearLayoutManager(this,
                RecyclerView.VERTICAL,false));





    }
}