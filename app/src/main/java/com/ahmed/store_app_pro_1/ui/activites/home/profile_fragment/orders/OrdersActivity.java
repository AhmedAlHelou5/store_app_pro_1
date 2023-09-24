package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityOrdersBinding;
import com.ahmed.store_app_pro_1.ui.activites.details.DetailsActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.ProfileFragment;
import com.ahmed.store_app_pro_1.ui.adapters.OrdersAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnOrderClickListener;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    ActivityOrdersBinding  binding;
    OrdersAdapter ordersAdapter;
    ArrayList<OrderModel> orderModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );

        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        setSupportActionBar(binding.toolbar);

        orderModels = Utils.FillOrder();



        binding.btnBack.setOnClickListener(v -> {
            finish();
        });


        ordersAdapter = new OrdersAdapter(orderModels, new OnOrderClickListener() {
            @Override
            public void onItemClick(OrderModel order) {
                Intent intent = new Intent(getBaseContext(), OrderDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", order.getId());
                bundle.putDouble("total", order.getTotal());
                bundle.putDouble("totalAfterDiscount", order.getTotalAfterDicount());
                bundle.putDouble("discount", order.getDicount());
                intent.putExtras(bundle);
                startActivity(intent);



            }
        });



        binding.rvItemsOrders.setAdapter(ordersAdapter);
        binding.rvItemsOrders.setHasFixedSize(true);
        binding.rvItemsOrders.setClipToPadding(false);
        binding.rvItemsOrders.setClipChildren(false);

        binding.rvItemsOrders.setLayoutManager(new
                LinearLayoutManager(this,
                RecyclerView.VERTICAL,false));




    }
}