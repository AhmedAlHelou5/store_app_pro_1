package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityOrderDetailsBinding;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );

        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        setSupportActionBar(binding.toolbar);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        double total = extras.getDouble("total");
//        double totalAfterDiscount = extras.getDouble("totalAfterDiscount");
        double discount = extras.getDouble("discount");
        int id = extras.getInt("id");

        binding.tvPrice.setText(String.valueOf(total));
        binding.tvDiscountNum.setText(String.valueOf(discount));
        binding.tvNumOrder.setText(String.valueOf(id));


        double discountTotal = Double.parseDouble(String.valueOf(binding.tvPrice.getText()))
                - Double.parseDouble(String.valueOf(binding.tvDiscountNum.getText()));






        binding.tvPriceTotalTop.setText(String.valueOf(discountTotal));

        binding.tvTotalPriceAfterDiscount.setText(String.valueOf(discountTotal));









    }
}