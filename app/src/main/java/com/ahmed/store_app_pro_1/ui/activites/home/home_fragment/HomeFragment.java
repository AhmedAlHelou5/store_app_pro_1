package com.ahmed.store_app_pro_1.ui.activites.home.home_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.CustomItemImgaeSliderHomeFragmentBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentHomeBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.HomeActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.cart_fragment.CartFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.ProfileFragment;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PopularHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;
import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;
import com.ahmed.store_app_pro_1.ui.models.PopularModel;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;
import com.ahmed.store_app_pro_1.ui.models.SliderImageHomeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Handler handler = new Handler();
    Runnable runnable;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentHomeBinding binding = FragmentHomeBinding.inflate(getLayoutInflater(),
                container,false);
        CustomItemImgaeSliderHomeFragmentBinding customItemBinding = CustomItemImgaeSliderHomeFragmentBinding.inflate(getLayoutInflater(),
                container,false);
//                ArrayList<SliderImageHomeModel> images = new ArrayList<>();
//        images.add(new SliderImageHomeModel( R.drawable.image_slider1));
//        images.add(new SliderImageHomeModel(R.drawable.image2));
//        images.add(new SliderImageHomeModel(R.drawable.image_slider1));
//        images.add(new SliderImageHomeModel(R.drawable.image2));
//        images.add(new SliderImageHomeModel(R.drawable.image_slider1));
//        images.add(new SliderImageHomeModel(R.drawable.image2));
        ArrayList<SliderImageHomeModel> images = Utils.FillImages();
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









        ArrayList<CategoriesModel> categoriesModels = Utils.FillCategories();
        AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(categoriesModels);
//
        binding.rvAllCategories.setAdapter(allCategoriesAdapter);
        binding.rvAllCategories.setHasFixedSize(true);
        binding.rvAllCategories.setClipToPadding(false);
        binding.rvAllCategories.setClipChildren(false);

        binding.tvShowAllCategoriesHomeFragment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CategoryFragment categoryFragment = new CategoryFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, categoryFragment).commit();

                        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
                        bottomNavigationView.setSelectedItemId(R.id.category_menu);



                    }
                }
        );



        binding.rvAllCategories.setLayoutManager(new
                LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL,false));



        ArrayList<OfferModel> offerModels = Utils.FillOffers();
        OffersHomeAdapter allOffersAdapter = new OffersHomeAdapter(offerModels);


        binding.rvAllOffers.setAdapter(allOffersAdapter);
        binding.rvAllOffers.setHasFixedSize(true);
        binding.rvAllOffers.setClipToPadding(false);
        binding.rvAllOffers.setClipChildren(false);

        binding.rvAllOffers.setLayoutManager(new
                LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL,false));




        ArrayList<PopularModel> popularModels = Utils.FillPopulars();
        PopularHomeAdapter allPopularAdapter = new PopularHomeAdapter(popularModels);


        binding.rvMostPopular.setAdapter(allPopularAdapter);
        binding.rvMostPopular.setHasFixedSize(true);
        binding.rvMostPopular.setClipToPadding(false);
        binding.rvMostPopular.setClipChildren(false);

        binding.rvMostPopular.setLayoutManager(new
                LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL,false));


        ArrayList<ProductModel> productModels = Utils.FillProducts();
        ProductHomeAdapter productHomeAdapter = new ProductHomeAdapter(productModels);


        binding.rvProductHome.setAdapter(productHomeAdapter);
        binding.rvProductHome.setHasFixedSize(true);
        binding.rvProductHome.setClipToPadding(false);
        binding.rvProductHome.setClipChildren(false);

        binding.rvProductHome.setLayoutManager(new
                LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL,false));













        return binding.getRoot();

}



}