package com.ahmed.store_app_pro_1.ui.activites.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityDetailsBinding;

import com.ahmed.store_app_pro_1.ui.activites.cart.CartActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.home_fragment.HomeFragment;
import com.ahmed.store_app_pro_1.ui.adapters.ColorProductRvAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;

import com.ahmed.store_app_pro_1.ui.models.ProductModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class DetailsActivity extends AppCompatActivity implements HomeFragment.OnFragmentClickListener {
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

        binding.cardViewBack.setOnClickListener(v -> {
            finish();
        });



        binding.buttonAddToCart.setOnClickListener(v -> {
            startActivity(new Intent(DetailsActivity.this, CartActivity.class));

        });



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ArrayList<Integer> imageList = extras.getIntegerArrayList("images");
        ArrayList<Integer> colors = extras.getIntegerArrayList("colors");
        String title = extras.getString("title");
        String price = extras.getString("price");
        String description = extras.getString("description");
        boolean like = extras.getBoolean("isLike");
        int image = extras.getInt("image");






        binding.tvTitle.setText(title);
        binding.tvPrice.setText(price);
        binding.tvPriceAddToCart.setText(price);
        binding.tvDescriptionProduct.setText(description);

//        ArrayList<SliderImageHomeModel> images = Utils.FillImagesForProduct();
        SliderAdapter sliderAdapter = new SliderAdapter(imageList);

        binding.tvCount.setText("0");


        AtomicInteger num = new AtomicInteger();
        final int get_quantity = Integer.parseInt( binding.tvCount.getText().toString());
        final double get_price= Double.parseDouble( binding.tvPrice.getText().toString());
        final double get_total=get_quantity*get_price;
        if (get_total==0){
            binding.tvPriceAddToCart.setText(get_price+"");
        }


        binding.btnAdd.setOnClickListener(v -> {
            num.getAndIncrement();
            binding.tvCount.setText(String.valueOf(num.get()));
            double total=num.get()*get_price;

            binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));



        });

        binding.btnMin.setOnClickListener(v -> {
           if (num.get() > 0){
            num.getAndDecrement();
            binding.tvCount.setText(String.valueOf(num.get()));
               double total=num.get()*get_price;
               binding.tvPriceAddToCart.setText(total+"");
               binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));


           }
       if (num.get()==0){
           binding.tvPriceAddToCart.setText(get_price+"");
       }


        });



        binding.buttonAddToCart.setOnClickListener(v -> {
            Intent intent1 = new Intent(DetailsActivity.this, CartActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title",title);
            bundle.putString("price", price);
            bundle.putString("description", description);
            bundle.putBoolean("isLike", like);
            bundle.putInt("count",Integer.parseInt( binding.tvCount.getText().toString()));
            intent.putExtras(bundle);
            startActivity(intent1);
        });






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
                if (pos >= imageList.size()) pos = 0;
                binding.viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
        Utils.FillProducts();



        ColorProductRvAdapter allOffersAdapter = new ColorProductRvAdapter(colors);


        binding.recColorItem.setAdapter(allOffersAdapter);
        binding.recColorItem.setHasFixedSize(true);
        binding.recColorItem.setClipToPadding(false);
        binding.recColorItem.setClipChildren(false);

        binding.recColorItem.setLayoutManager(new
                LinearLayoutManager(this,
                RecyclerView.HORIZONTAL,false));











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

    @Override
    public void OnFragmentInteraction(ProductModel product) {


//        binding.tvDescriptionProduct.setText(product.getDescription());
//        binding.tvTitle.setText(product.getTitle());
//        binding.tvPrice.setText(product.getPrice());

//        ProductModel offerModels = product.getColors();
//        ColorProductRvAdapter allOffersAdapter = new ColorProductRvAdapter(product.getColors());
//
//
//        binding.recColorItem.setAdapter(allOffersAdapter);
//        binding.recColorItem.setHasFixedSize(true);
//        binding.recColorItem.setClipToPadding(false);
//        binding.recColorItem.setClipChildren(false);
//
//        binding.recColorItem.setLayoutManager(new
//                LinearLayoutManager(this,
//                RecyclerView.HORIZONTAL,false));
//






    }
}