package com.ahmed.store_app_pro_1.ui.activites.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityCartBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.payment.PaymentActivity;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductCartAdapter;
import com.ahmed.store_app_pro_1.ui.models.CartModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    ProductCartAdapter allOffersAdapter;
    ArrayList<CartModel> cartModels;
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

        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, PaymentActivity.class));
        });


        setSupportActionBar(binding.toolbar);








        cartModels = Utils.FillCart();

        if (cartModels.size()==0){
            binding.cardNotEmpty.setVisibility(View.GONE);



        }else{
            binding.cardEmptyCart.setVisibility(View.GONE);

        }

        binding.btnBackToHome.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, HomeActivity.class));
        });


        allOffersAdapter = new ProductCartAdapter(cartModels);


        binding.rvItemsCart.setAdapter(allOffersAdapter);
        binding.rvItemsCart.setHasFixedSize(true);
        binding.rvItemsCart.setClipToPadding(false);
        binding.rvItemsCart.setClipChildren(false);

        binding.rvItemsCart.setLayoutManager(new
                LinearLayoutManager(this,
                RecyclerView.VERTICAL,false));


        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvItemsCart);



    }





    CartModel deleteData;

    final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();

            deleteData = cartModels.get(position);
            cartModels.remove(position);

            allOffersAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.bg_swipe_cart))
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .setIconHorizontalMargin(30)
                    .addCornerRadius(1, 15)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };





}