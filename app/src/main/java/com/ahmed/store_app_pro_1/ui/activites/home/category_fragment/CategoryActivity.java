package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityCategoryBinding;
import com.ahmed.store_app_pro_1.ui.activites.details.ProductDetailsFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.search.SearchActivity;
import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

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




        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> tabs = Utils.FillTabs();


        if (tabs.get(0).equals("الكل") ){
            fragments.add(CategoryTabsFragment.newInstance(tabs.get(0)));

        }


//        fragments.add(CategoryTabsFragment.newInstance(category));


        fragments.add(CategoryTabsFragment.newInstance(tabs.get(1)));
        fragments.add(CategoryTabsFragment.newInstance(tabs.get(2)));
        fragments.add( CategoryTabsFragment.newInstance(tabs.get(3)));
        fragments.add( CategoryTabsFragment.newInstance(tabs.get(4)));
        fragments.add( CategoryTabsFragment.newInstance(tabs.get(5)));
        fragments.add( CategoryTabsFragment.newInstance(tabs.get(6)));




        PagerAdapter adapter = new  PagerAdapter(this,
                fragments);
        binding.pagerProducts.setAdapter(adapter);
        new TabLayoutMediator(binding.tabsCategories,
                binding.pagerProducts, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs.get(position));
                if (tabs.get(position).equals(category.toString())) {
                    tab.select();
                    tab.setId(position);
                }



            }
        }).attach();


        if (category != null) {
            binding.toolbarTvTitleCategory.setText(category);
            binding.pagerProducts.setCurrentItem(tabs.indexOf(category));


        }


        Log.d("TAG category ", "onCreate: "+category);



        for(int i=0; i < binding.tabsCategories.getTabCount(); i++) {
            View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(10, 0, 10, 0);
            tab.requestLayout();


        }





    }

}