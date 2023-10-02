package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Switch;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityCategoryBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentCategoryTabsBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.details.DetailsActivity;
import com.ahmed.store_app_pro_1.ui.activites.details.ProductDetailsFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.search.SearchActivity;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoryWithTabsAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryMainModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity  {
    ActivityCategoryBinding binding;
    boolean isProgressVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.toolbarSearchIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, SearchActivity.class));
        });
        isProgressVisible = true;

        binding.idPBLoading.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("category");
        int categoryId = intent.getIntExtra("categoryId", 0  );

//        binding.toolbarTvTitleCategory.setText(categoryName);

        binding.toolbarTvTitleCategory.setText(categoryName);



        Log.e("TAG categoryId", "onCreate: "+categoryId );
        Log.e("TAG categoryName", "onCreate: "+categoryName );


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
//        ArrayList<CategoryModel> categories=new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("category_main_id",categoryId);




        ArrayList<Fragment> fragments = new ArrayList<>();

        Call<CategoryMainModel> call = apiInterface.GetSubCategory(map);
//        ArrayList<String> tags=new ArrayList<>();
        ArrayList<CategoryModel> categoryModels=new ArrayList<>();
        call.enqueue((new Callback<CategoryMainModel>() {
            @Override
            public void onResponse(Call<CategoryMainModel> call, Response<CategoryMainModel> response) {

                if (response.isSuccessful() ){

                    assert response.body() != null;
                    isProgressVisible = false;

                    binding.idPBLoading.setVisibility(View.GONE);
                    categoryModels.addAll( response.body().getData());

                    for (int i=0;i<response.body().getData().size();i++){
                        fragments.add(CategoryTabsFragment.newInstance(categoryModels.get(i).getName(),categoryModels.get(i).getId()));

                    }



                    PagerAdapter adapter = new  PagerAdapter(CategoryActivity.this,
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





                    for(int i=0; i < binding.tabsCategories.getTabCount(); i++) {
                        View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
                        p.setMargins(10, 0, 10, 0);
                        tab.requestLayout();


                    }

                }
                else {
                    Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());


                }



            }

            @Override
            public void onFailure(Call<CategoryMainModel> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));



        binding.tabsCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.e("TAG categoryId", "onTabSelected: " + categoryModels.get(tab.getPosition()).getName());
                Log.e("TAG categoryId", "onTabSelected: " + categoryModels.get(tab.getPosition()).getId());





            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                binding.toolbarTvTitleCategory.setText(categoryModels.get(tab.getPosition()).getName());

            }
        });



        for(int i=0; i < binding.tabsCategories.getTabCount(); i++) {
            View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(10, 0, 10, 0);
            tab.requestLayout();


        }




        }








}