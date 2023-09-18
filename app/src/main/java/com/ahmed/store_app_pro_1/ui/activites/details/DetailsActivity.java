package com.ahmed.store_app_pro_1.ui.activites.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityDetailsBinding;

import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;

import com.ahmed.store_app_pro_1.ui.models.SliderImageHomeModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    Handler handler = new Handler();
    Runnable runnable;
    String [] tabs = {"الكل","بدالات","عجلات","متنوع","قطع غيار","دراجات هوائية","دراجات نارية",};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.transparent) );
        binding.tvDescriptionProduct.setMovementMethod(new ScrollingMovementMethod());
        ArrayList<SliderImageHomeModel> images = Utils.FillImagesForProduct();
        SliderAdapter sliderAdapter = new SliderAdapter(images);



        binding.viewPager.setAdapter(sliderAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.setCurrentItem(0);
        binding.viewPager.setClipChildren(false);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem( binding.viewPager.getCurrentItem() + 1);
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
                if (pos >= images.size()) pos = 0;
                binding.viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
        Utils.FillProducts();



//
        ArrayList<Fragment> fragments = new ArrayList<>();


        if (tabs[0].equals("الكل") ){
            fragments.add(ProductDetailsFragment .newInstance(tabs[0]));

        }

        fragments.add(ProductDetailsFragment.newInstance(tabs[1]));
        fragments.add(ProductDetailsFragment.newInstance(tabs[2]));
        fragments.add( ProductDetailsFragment.newInstance(tabs[3]));
        fragments.add( ProductDetailsFragment.newInstance(tabs[4]));
        fragments.add( ProductDetailsFragment.newInstance(tabs[5]));
        fragments.add( ProductDetailsFragment.newInstance(tabs[6]));
        PagerAdapter adapter = new  PagerAdapter(this,
                fragments);
        binding.pagerProducts.setAdapter(adapter);
        new TabLayoutMediator(binding.tabsCategories,
                binding.pagerProducts, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();
//




        for(int i=0; i < binding.tabsCategories.getTabCount(); i++) {
            View tab = ((ViewGroup) binding.tabsCategories.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(10, 0, 10, 0);
            tab.requestLayout();
        }








    }
}