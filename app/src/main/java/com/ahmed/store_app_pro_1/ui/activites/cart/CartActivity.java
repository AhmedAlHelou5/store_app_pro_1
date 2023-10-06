package com.ahmed.store_app_pro_1.ui.activites.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityCartBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryActivity;
import com.ahmed.store_app_pro_1.ui.activites.payment.PaymentActivity;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesFragmentAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductCartAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.CartModel;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.orders.OrderItemModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.ahmed.store_app_pro_1.ui.models.product.SearchProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    ProductCartAdapter allOffersAdapter;
    ArrayList<OrderItemModel> orderModels1 = new ArrayList<>();
    ArrayList<OrderItemModel> products = new ArrayList<>();



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


        SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Intent intent = getIntent();
       ArrayList<OrderItemModel> orderItemModel = (ArrayList<OrderItemModel>) intent.getSerializableExtra("orderModel");




//        Gson gson=new Gson();
//        String json = sp.getString( Constans.SP_CART_FINAL,"");
//
//






//
//        orderModels1=gson.fromJson(json,new TypeToken<ArrayList<OrderItemModel>>() {
//        }.getType());
//        if (orderModels1==null){
//            orderModels1=new ArrayList<>();
//        }else {
//

        Log.e("TAG", "onCreate:orderItemModel " + orderItemModel.size());


        allOffersAdapter = new ProductCartAdapter(orderItemModel,getBaseContext());


        binding.rvItemsCart.setAdapter(allOffersAdapter);
        binding.rvItemsCart.setHasFixedSize(true);
        binding.rvItemsCart.setClipToPadding(false);
        binding.rvItemsCart.setClipChildren(false);

        binding.rvItemsCart.setLayoutManager(new
                LinearLayoutManager(getBaseContext(),
                RecyclerView.VERTICAL,false));


        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvItemsCart);
        allOffersAdapter.notifyDataSetChanged();




//            ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
//            Map<String,Object> map = new HashMap<>();


//            for (int i=0;i<orderModels1.size();i++){
////                cartModels.get(i).setQuantity(orderModels1.get(i).getQuantity());
//
//
//
//                map.put("product_id",orderModels1.get(i).getProductId());
//
//
//













//                Call<SearchProductModel> call = apiInterface.GetProductById(map);


//                call.enqueue((new Callback<SearchProductModel>() {
//                    @Override
//                    public void onResponse(Call<SearchProductModel> call, Response<SearchProductModel> response) {
//
//                        if (response.isSuccessful() ){
//                            assert response.body() != null;
////                            isProgressVisible[0] = false;
////                            binding.idPBLoading.setVisibility(View.GONE);
//
//                                   ProductModel productModel = response.body().getData().getProduct();
//                            products.add(new OrderItemModel(productModel.getId(), productModel.getName(),
//                                    productModel.getOfferPrice(), productModel.getImages(), 1
//                                    ));
//
//
//                            Log.e("TAG  (ArrayList<CategoryModel>) response.body() ", "onResponse:getFcm  products  "+products.size());
//
//
//
//                            SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREF, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sp.edit();
//
//                            Gson gson1 = new Gson();
//                            String json1 = gson1.toJson(orderModels1);
//                            editor.putString(Constans.SP_CART_FINAL, json1);
//                            editor.apply();
//
////  categories rv
////                            AllCategoriesFragmentAdapter allCategoriesAdapter = new AllCategoriesFragmentAdapter(products,getBaseContext(), new OnCategoryClickListener() {
////                                @Override
////                                public void OnCategoryClick(CategoryModel category) {
////                                    Intent intent = new Intent(getBaseContext(), CategoryActivity.class);
////                                    intent.putExtra("category", category.getName());
////                                    intent.putExtra("categoryId", category.getId());
////                                  startActivity(intent);
////
////                                    Log.e("TAG categoryId", "OnCategoryClick: " + category.getName());
////                                    Log.e("TAG categoryId", "OnCategoryClick: " + category.getId());
////
////
////
////
////                                }
////                            });
//
////                    AllCategoriesFragmentAdapter allCategoriesAdapter = new AllCategoriesFragmentAdapter(categoriesModels);
////
////                            binding.rvAllCategoriesFragment.setAdapter(allCategoriesAdapter);
////                            binding.rvAllCategoriesFragment.setHasFixedSize(true);
////                            binding.rvAllCategoriesFragment.setClipToPadding(false);
////                            binding.rvAllCategoriesFragment.setClipChildren(false);
////                            binding.rvAllCategoriesFragment.setLayoutManager(new
////                                    GridLayoutManager(getActivity(),
////                                    2,
////                                    RecyclerView.VERTICAL,false));
//
//                        }
//                        else {
//                            Log.e("TAG", "onResponse: null " + response.body());
////                            Log.e("TAG", "onResponse: null " + response.body().getMessage());
//
//
//                        }
//
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<SearchProductModel> call, Throwable t) {
//                        Toast.makeText(getBaseContext(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
//                        Log.e("TAG", "onFailure: " + t.getMessage());
//
//
//                    }
//                }));






//            }
//        }






//        cartModels = Utils.FillCart();

        if (orderItemModel.size()==0){
            binding.cardNotEmpty.setVisibility(View.GONE);



        }else{
            binding.cardEmptyCart.setVisibility(View.GONE);

        }

        binding.btnBackToHome.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, HomeActivity.class));
        });





    }





    OrderItemModel deleteData;

    final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();

            if (orderModels1.size() > 0) {
                deleteData = orderModels1.get(position);
                orderModels1.remove(deleteData);
//                orderModels1.remove(position);
//                orderModels1.notify();


                allOffersAdapter.notifyDataSetChanged();


//                SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREF, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//
//                Gson gson1 = new Gson();
//                String json1 = gson1.toJson(orderModels1);
//                editor.putString(Constans.SP_CART_FINAL, json1);
//                editor.apply();


                Toast.makeText(CartActivity.this, "orderModels1.remove(deleteData) success", Toast.LENGTH_SHORT).show();

            }


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