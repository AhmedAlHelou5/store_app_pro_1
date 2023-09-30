package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityCategoryBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.details.DetailsActivity;
import com.ahmed.store_app_pro_1.ui.activites.details.ProductDetailsFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.search.SearchActivity;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoryWithTabsAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity  {
    ActivityCategoryBinding binding;

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



        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String categoryId = intent.getStringExtra("categoryId");
        binding.toolbarTvTitleCategory.setText(category);




//        ArrayList<Fragment> fragments = new ArrayList<>();
//        ArrayList<String> tabs = new ArrayList<>();

        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);



        Call<HomeModel> call = apiInterface.GetHomeData();
        Call<CategoryModel> call2 = apiInterface.GetCategory();



//        call.enqueue((new Callback<HomeModel>() {
//            @Override
//            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
//
//                if (response.isSuccessful() ){
//                    assert response.body() != null;
//
//                    ArrayList<CategoryModel> categories = (ArrayList<CategoryModel>) response.body().getData().getCategories();
//                    ArrayList<ProductModel> products = new ArrayList<>();
//                    products.addAll(response.body().getData().getProducts());
//
//                    ArrayList<String> tags = new ArrayList<>();
//                    ArrayList<Integer> tagsId = new ArrayList<>();
//
//                    tags.add(0,"الكل");
//                    tags.add(1,categories.get(0).getName());
//                    tags.add(2,categories.get(1).getName());
//                    tags.add(3,categories.get(2).getName());
//                    tags.add(4,categories.get(3).getName());
//
//                    tagsId.add(0,0);
//                    tagsId.add(1,categories.get(0).getId());
//                    tagsId.add(2,categories.get(1).getId());
//                    tagsId.add(3,categories.get(2).getId());
//                    tagsId.add(4,categories.get(3).getId());
//
//
//
//
//
//
//
//
//
//
//
//
//
//                    ArrayList<Fragment> fragments = new ArrayList<>();
//
//
//                    if (tags.get(0).equals("الكل")){
//                        fragments.add(ProductDetailsFragment.newInstance(tags.get(0),tagsId.get(0)+""));
//
//                    }
//
//                    fragments.add(ProductDetailsFragment.newInstance(tags.get(1),tagsId.get(1)+""));
//                    fragments.add(ProductDetailsFragment.newInstance(tags.get(2),   tagsId.get(2)+""));
//                    fragments.add( ProductDetailsFragment.newInstance(tags.get(3),   tagsId.get(3)+""));
//                    fragments.add( ProductDetailsFragment.newInstance(tags.get(4),   tagsId.get(4)+""));
//
//                    AllCategoryWithTabsAdapter adapter = new  AllCategoryWithTabsAdapter( products ,
//                            getBaseContext());
//                    binding.pagerProducts.setAdapter(adapter);
//                    new TabLayoutMediator(binding.tabsCategories,
//                            binding.pagerProducts, new TabLayoutMediator.TabConfigurationStrategy() {
//                        @Override
//                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                            tab.setText(tags.get(position));
//                        }
//                    }).attach();
//                    //
//
//
//
//
//                    for(int i=0; i < binding.tabsCategories.getTabCount(); i++) {
//                        View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
//                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
//                        p.setMargins(10, 0, 10, 0);
//                        tab.requestLayout();
//
//
//                    }
//                }
//                else {
//                    Log.e("TAG", "onResponse: null " + response.body());
////                            Log.e("TAG", "onResponse: null " + response.body().getMessage());
//
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<HomeModel> call, Throwable t) {
//                Toast.makeText(CategoryActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.e("TAG", "onFailure: " + t.getMessage());
//
//
//            }
//        }));




    }

}