package com.ahmed.store_app_pro_1.ui.activites.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityDetailsBinding;

import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.cart.CartActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryTabsFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.home_fragment.HomeFragment;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesFragmentAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ColorProductRvAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;

import com.ahmed.store_app_pro_1.ui.adapters.PopularHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderImagesProductAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemOfferClickListener;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryMainModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.favorite.AddProductToFavoriteModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.home.SliderHomeModel;
import com.ahmed.store_app_pro_1.ui.models.images.ImageModel;
import com.ahmed.store_app_pro_1.ui.models.orders.OrderItemModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    Handler handler = new Handler();
    Runnable runnable;
//    String [] tabs = {"الكل","بدالات","عجلات","متنوع","قطع غيار","دراجات هوائية","دراجات نارية",};

    boolean isProgressVisible = false;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        window.setStatusBarColor(getResources().getColor(R.color.transparent));
        binding.tvDescriptionProduct.setMovementMethod(new ScrollingMovementMethod());

        binding.cardViewBack.setOnClickListener(v -> {
            finish();
        });


        binding.buttonAddToCart.setOnClickListener(v -> {
            startActivity(new Intent(DetailsActivity.this, CartActivity.class));

        });

        Intent intent = getIntent();
        ProductModel productModel = (ProductModel) intent.getSerializableExtra("productOffer");
        System.out.println(productModel.toString());


        binding.tvTitle.setText(productModel.getName());
        binding.tvPrice.setText(String.valueOf(productModel.getOfferPrice()));
        binding.tvDescriptionProduct.setText(productModel.getDescription());


        binding.tvCount.setText("0");


        binding.buttonAddToCart.setEnabled(false);
        binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));



        // put in shared preferences value
//        SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
//        editor.putString("name", "Elena");
//        editor.apply();



        //get shared preferences value
        SharedPreferences prefs = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE);
        String token = prefs.getString(Constans.TOKEN, "");


        binding.cardViewLike.setOnClickListener(v -> {

            Map<String, Object> map = new HashMap<>();
            map.put("product_id", productModel.getId());
            Log.e("TAG", "onCreate: " + map);


//            Call<AddProductToFavoriteModel> call = apiInterface.AddProductToFavorite(map,"Bearer "+token);
            Call<AddProductToFavoriteModel> call = apiInterface.AddProductToFavorite(map,"Bearer "+token);

            call.enqueue((new Callback<AddProductToFavoriteModel>() {
                @Override
                public void onResponse(Call<AddProductToFavoriteModel> call, Response<AddProductToFavoriteModel> response) {

                    if (response.isSuccessful()) {

                        assert response.body() != null;

                        if (response.body().isData()){
                            binding.cardViewLike.setCardBackgroundColor(getResources().getColor(R.color.bg_color_favorite_one));
                        }else {
                            binding.cardViewLike.setCardBackgroundColor(getResources().getColor(R.color.secondary_color));
                        }


                        Toast.makeText(getBaseContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());
                        Toast.makeText(getBaseContext(), "Like null", Toast.LENGTH_SHORT).show();


                    }

                }
                @Override
                public void onFailure(Call<AddProductToFavoriteModel> call, Throwable t) {
                    Toast.makeText(DetailsActivity.this, "Like onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("TAG", "onFailure: " + t.getMessage());


                }
            }));








        });











        AtomicInteger num = new AtomicInteger();
        final int get_quantity = Integer.parseInt(binding.tvCount.getText().toString());
        final double get_price = Double.parseDouble(binding.tvPrice.getText().toString());
        final double get_total = get_quantity * get_price;
        if (get_total == 0) {
            binding.tvPriceAddToCart.setText("0.00");
            binding.buttonAddToCart.setEnabled(false);
            binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));
        }


        binding.btnAdd.setOnClickListener(v -> {
            num.getAndIncrement();
            binding.tvCount.setText(String.valueOf(num.get()));
            double total = num.get() * get_price;

            binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));
            binding.buttonAddToCart.setEnabled(true);
            binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));


        });


        binding.btnMin.setOnClickListener(v -> {
            if (num.get() > 0) {
                num.getAndDecrement();
                binding.tvCount.setText(String.valueOf(num.get()));
                double total = num.get() * get_price;
                binding.tvPriceAddToCart.setText(total + "");
                binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));


            }
            if (num.get() == 0) {
                binding.tvPriceAddToCart.setText("0.00");

                binding.buttonAddToCart.setEnabled(false);
                binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

            } else {
                binding.buttonAddToCart.setEnabled(true);
                binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));

            }
        });


        SliderImagesProductAdapter sliderAdapter = new SliderImagesProductAdapter(productModel.getImages(), getBaseContext());

        binding.viewPager.setAdapter(sliderAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.setCurrentItem(0);
        binding.viewPager.setClipChildren(false);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
        binding.viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.20f);
            }
        });
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = binding.viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= productModel.getImages().size()) pos = 0;
                binding.viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
//        Utils.FillProducts();


        if (productModel.getColors().size() == 0) {
            binding.recColorItem.setVisibility(View.GONE);
            binding.tvColorNothing.setVisibility(View.VISIBLE);
            binding.tvColorNothing.setText("لا يوجد لون");
        } else {
            binding.tvColorNothing.setVisibility(View.GONE);
            binding.recColorItem.setVisibility(View.VISIBLE);


            ColorProductRvAdapter allOffersAdapter = new ColorProductRvAdapter(productModel.getColors(), getBaseContext());


            binding.recColorItem.setAdapter(allOffersAdapter);
            binding.recColorItem.setHasFixedSize(true);
            binding.recColorItem.setClipToPadding(false);
            binding.recColorItem.setClipChildren(false);

            binding.recColorItem.setLayoutManager(new
                    LinearLayoutManager(this,
                    RecyclerView.HORIZONTAL, false));


        }


        isProgressVisible = true;

        binding.idPBLoading.setVisibility(View.VISIBLE);



        int categoryId = 0;


        if (productModel.getCategoryId() >= 20 && productModel.getCategoryId() <= 29) {
            categoryId = 19;
        } else if (productModel.getCategoryId() == 31) {
            categoryId = 30;
        } else if (productModel.getCategoryId() == 33) {
            categoryId = 32;
        } else if (productModel.getCategoryId() == 35) {
            categoryId = 34;
        }


        Map<String, Object> map = new HashMap<>();
        map.put("category_main_id", categoryId);
        Log.e("TAG", "onCreate: " + map);


        ArrayList<Fragment> fragments = new ArrayList<>();

        Call<CategoryMainModel> call = apiInterface.GetSubCategory(map);
//        ArrayList<String> tags=new ArrayList<>();
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        call.enqueue((new Callback<CategoryMainModel>() {
            @Override
            public void onResponse(Call<CategoryMainModel> call, Response<CategoryMainModel> response) {

                if (response.isSuccessful()) {

                    assert response.body() != null;
                    isProgressVisible = false;

                    binding.idPBLoading.setVisibility(View.GONE);
                    categoryModels.addAll(response.body().getData());

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        fragments.add(ProductDetailsFragment.newInstance(categoryModels.get(i).getName(), categoryModels.get(i).getId()));

                    }


                    PagerAdapter adapter = new PagerAdapter(DetailsActivity.this,
                            fragments);
                    binding.pagerProducts.setAdapter(adapter);


                    new TabLayoutMediator(binding.tabsCategories,
                            binding.pagerProducts, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText(categoryModels.get(position).getName());


//                            tab.setText(categories.get(position).getName());
                            Log.e("TAG categories", "onConfigureTab: " + tab);
//                            System.out.println(tab.getText());


                        }

                    }).attach();


                    for (int i = 0; i < binding.tabsCategories.getTabCount(); i++) {
                        View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
                        p.setMargins(10, 0, 10, 0);
                        tab.requestLayout();


                    }

                } else {
                    Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());


                }


            }

            @Override
            public void onFailure(Call<CategoryMainModel> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));


        for (int i = 0; i < binding.tabsCategories.getTabCount(); i++) {
            View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(10, 0, 10, 0);
            tab.requestLayout();


        }

//        ArrayList<OrderItemModel> orderModels = new ArrayList<>();

//
//        SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREF, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        Gson gson = new Gson();
//        String json = sp.getString(Constans.CART, "");

//        ArrayList<OrderItemModel> orderModels1 = new ArrayList<>();
//        orderModels1 = gson.fromJson(json, new TypeToken<ArrayList<OrderItemModel>>() {
//        }.getType());
//        if (orderModels1 == null) {
//            orderModels1 = new ArrayList<>();
//        } else {
//            for (int i = 0; i < orderModels1.size(); i++) {
//                orderModels.add(orderModels1.get(i));
//            }
//        }


//        String json =gson.toJson(orderModels);
//        editor.putString( Constans.CART,json);
//        editor.apply();

        ArrayList<OrderModel> orderModels2 = new ArrayList<>();
        binding.buttonAddToCart.setOnClickListener(v -> {



//            orderModels.add(new OrderItemModel(productModel.getId(), productModel.getName(),
//                    productModel.getOfferPrice(), productModel.getImages(), Integer.parseInt(binding.tvCount.getText().toString())));

//            orderModels2.add(new OrderModel(orderModels));


            Intent intent1 = new Intent(DetailsActivity.this, CartActivity.class);
            intent1.putExtra("orderModel", orderModels2);
            startActivity(intent1);


        });


    }


}