package com.ahmed.store_app_pro_1.ui.activites.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.home_fragment.HomeFragment;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ColorProductRvAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PagerAdapter;

import com.ahmed.store_app_pro_1.ui.adapters.PopularHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderImagesProductAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemOfferClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.home.SliderHomeModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity  {
    ActivityDetailsBinding binding;
    Handler handler = new Handler();
    Runnable runnable;
//    String [] tabs = {"الكل","بدالات","عجلات","متنوع","قطع غيار","دراجات هوائية","دراجات نارية",};



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
        ProductModel productModel = ( ProductModel) intent.getSerializableExtra("productOffer");
        System.out.println(productModel.toString());





        binding.tvTitle.setText(productModel.getName());
        binding.tvPrice.setText(String.valueOf(productModel.getOfferPrice()));
        binding.tvDescriptionProduct.setText(productModel.getDescription());



        binding.tvCount.setText("0");


        binding.buttonAddToCart.setEnabled(false);
        binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));



        AtomicInteger num = new AtomicInteger();
        final int get_quantity = Integer.parseInt( binding.tvCount.getText().toString());
        final double get_price= Double.parseDouble( binding.tvPrice.getText().toString());
        final double get_total=get_quantity*get_price;
        if (get_total==0){
            binding.tvPriceAddToCart.setText("0.00");
            binding.buttonAddToCart.setEnabled(false);
            binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));
        }








        binding.btnAdd.setOnClickListener(v -> {
            num.getAndIncrement();
            binding.tvCount.setText(String.valueOf(num.get()));
            double total=num.get()*get_price;

            binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));
            binding.buttonAddToCart.setEnabled(true);
            binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));


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
               binding.tvPriceAddToCart.setText("0.00");

               binding.buttonAddToCart.setEnabled(false);
               binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

                  }
       else {
           binding.buttonAddToCart.setEnabled(true);
           binding.buttonAddToCart.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_next));

       }
        });



        binding.buttonAddToCart.setOnClickListener(v -> {
            Intent intent1 = new Intent(DetailsActivity.this, CartActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("itemToCart", productModel);
            bundle.putInt("count",Integer.parseInt( binding.tvCount.getText().toString()));
            intent.putExtras(bundle);
            startActivity(intent1);
        });





        SliderImagesProductAdapter sliderAdapter = new SliderImagesProductAdapter(productModel.getImages(),getBaseContext());

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
                if (pos >= productModel.getImages().size()) pos = 0;
                binding.viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
//        Utils.FillProducts();


        if (productModel.getColors().size()==0){
            binding.recColorItem.setVisibility(View.GONE);
            binding.tvColorNothing.setVisibility(View.VISIBLE);
            binding.tvColorNothing.setText("لا يوجد لون");
        }
        else {
            binding.tvColorNothing.setVisibility(View.GONE);
            binding.recColorItem.setVisibility(View.VISIBLE);


            ColorProductRvAdapter allOffersAdapter = new ColorProductRvAdapter(productModel.getColors(),getBaseContext());


            binding.recColorItem.setAdapter(allOffersAdapter);
            binding.recColorItem.setHasFixedSize(true);
            binding.recColorItem.setClipToPadding(false);
            binding.recColorItem.setClipChildren(false);

            binding.recColorItem.setLayoutManager(new
                    LinearLayoutManager(this,
                    RecyclerView.HORIZONTAL,false));


        }


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Call<HomeModel> call = apiInterface.GetHomeData();


//        call.enqueue((new Callback<HomeModel>() {
//            @Override
//            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
//
//                if (response.isSuccessful() ){
//                    assert response.body() != null;
//
//                    ArrayList<CategoryModel> categories = (ArrayList<CategoryModel>) response.body().getData().getCategories();
//
//                    ArrayList<String> tags = new ArrayList<>();
//
//                    tags.add(0,"الكل");
//                    tags.add(1,categories.get(0).getName());
//                    tags.add(2,categories.get(1).getName());
//                    tags.add(3,categories.get(2).getName());
//                    tags.add(4,categories.get(3).getName());
//
//
//
//
//                    ArrayList<Fragment> fragments = new ArrayList<>();
//
//
//                    if (tags.get(0).equals("الكل")){
//                        fragments.add(ProductDetailsFragment.newInstance(tags.get(0),categories.get(0).getId()+""));
//
//                    }
//
//                    fragments.add(ProductDetailsFragment.newInstance(tags.get(1),categories.get(1).getId()+""));
//                    fragments.add(ProductDetailsFragment.newInstance(tags.get(2),categories.get(2).getId()+""));
//                    fragments.add( ProductDetailsFragment.newInstance(tags.get(3),categories.get(3).getId()+""));
//                    fragments.add( ProductDetailsFragment.newInstance(tags.get(4),categories.get(4).getId()+""));
//
//                    PagerAdapter adapter = new  PagerAdapter(DetailsActivity.this,
//                            fragments);
//                    binding.pagerProducts.setAdapter(adapter);
//                    new TabLayoutMediator(binding.tabsCategories,
//                            binding.pagerProducts, new TabLayoutMediator.TabConfigurationStrategy() {
//                        @Override
//                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                            tab.setText(tags.get(position));
//                        }
//                    }).attach();
//            //
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
//                Toast.makeText(DetailsActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.e("TAG", "onFailure: " + t.getMessage());
//
//
//            }
//        }));


//        binding.tvColorNothing.setVisibility(View.GONE);


        //

//        }








    }


}