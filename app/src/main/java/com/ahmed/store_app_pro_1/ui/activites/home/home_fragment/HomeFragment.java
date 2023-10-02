package com.ahmed.store_app_pro_1.ui.activites.home.home_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemImgaeSliderHomeFragmentBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentHomeBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.details.DetailsActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PopularHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemOfferClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnMostSolidClickListener;
import com.ahmed.store_app_pro_1.ui.models.home.SliderHomeModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.category.MostSoldProductModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.home.OfferModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private OnFragmentClickListener listener;


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
        final boolean[] isProgressVisible = {false};
        isProgressVisible[0] = true;
        binding.idPBLoading.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Call<HomeModel> call = apiInterface.GetHomeData();


        call.enqueue((new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {

                if (response.isSuccessful() ){
                    assert response.body() != null;
                    isProgressVisible[0] = false;
                    binding.idPBLoading.setVisibility(View.GONE);


                    ArrayList<SliderHomeModel> images = (ArrayList<SliderHomeModel>) response.body().getData().getSliders();
                    ArrayList<CategoryModel> categories = (ArrayList<CategoryModel>) response.body().getData().getCategories();
                    ArrayList<ProductModel> offers = (ArrayList<ProductModel>) response.body().getData().getOffers();
                    ArrayList<ProductModel> mostSoldProductModels =new ArrayList<>();
                    mostSoldProductModels.add(response.body().getData().getMostSoldProduct());
                    ArrayList<ProductModel> productModels = (ArrayList<ProductModel>) response.body().getData().getProducts();



                    Log.e("TAG", "onResponse:getFcm "+response.body().getData());

// images slider
                    SliderAdapter sliderAdapter = new SliderAdapter(images,getActivity());
                    Log.e("TAG", "onCreateView:  images.size()"+images.size() );
                    Log.e("TAG", "onCreateView:  images.size()"+images);

                    binding.viewPager.setAdapter(sliderAdapter);
                    binding.viewPager.setClipChildren(false);
                    binding.viewPager.setClipToPadding(false);
                    binding.viewPager.setOffscreenPageLimit(5);
                    binding.viewPager.setCurrentItem(0);
                    binding.tabLayout.setupWithViewPager(binding.viewPager);
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

//  categories rv
                    AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(categories, getActivity(), new OnCategoryClickListener() {
                        @Override
                        public void OnCategoryClick(CategoryModel category) {
                            Intent intent = new Intent(getActivity(), CategoryActivity.class);
                            intent.putExtra("category", category.getName());
                            intent.putExtra("categoryId", category.getId());
                            getActivity().startActivity(intent);

                            Log.e("TAG categoryId", "OnCategoryClick: " + category.getName());
                            Log.e("TAG categoryId", "OnCategoryClick: " + category.getId());

                        }
                    });

                    binding.rvAllCategories.setAdapter(allCategoriesAdapter);
                    binding.rvAllCategories.setHasFixedSize(true);
                    binding.rvAllCategories.setClipToPadding(false);
                    binding.rvAllCategories.setClipChildren(false);
                    binding.rvAllCategories.setLayoutManager(new
                            LinearLayoutManager(getActivity(),
                            RecyclerView.HORIZONTAL,false));


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
















////  offers RV
                    OffersHomeAdapter allOffersAdapter = new OffersHomeAdapter(offers,getActivity(), new OnItemOfferClickListener() {
                        @Override
                        public void onItemClick(ProductModel offerModel) {
                            Intent intent1 = new Intent(getActivity().getBaseContext(), DetailsActivity.class);
                            intent1.putExtra("productOffer",offerModel);
                            getContext().startActivity(intent1);

                        }
                    });


                    binding.rvAllOffers.setAdapter(allOffersAdapter);
                    binding.rvAllOffers.setHasFixedSize(true);
                    binding.rvAllOffers.setClipToPadding(false);
                    binding.rvAllOffers.setClipChildren(false);

                    binding.rvAllOffers.setLayoutManager(new
                            LinearLayoutManager(getActivity(),
                            RecyclerView.HORIZONTAL,false));



//    most popular rv
                    PopularHomeAdapter allPopularAdapter = new PopularHomeAdapter(mostSoldProductModels, new OnItemClickListener() {
                        @Override
                        public void onItemClick(ProductModel popularModel) {
                            Intent intent1 = new Intent(getActivity().getBaseContext(), DetailsActivity.class);
                            intent1.putExtra("productOffer",popularModel);
                            getContext().startActivity(intent1);

                        }

                    },getActivity());


                    binding.rvMostPopular.setAdapter(allPopularAdapter);
                    binding.rvMostPopular.setHasFixedSize(true);
                    binding.rvMostPopular.setClipToPadding(false);
                    binding.rvMostPopular.setClipChildren(false);

                    binding.rvMostPopular.setLayoutManager(new
                            LinearLayoutManager(getActivity(),
                            RecyclerView.HORIZONTAL,false));





// products rv



//                    ArrayList<ProductModel> productModels = Utils.FillProducts();
                    ProductHomeAdapter productHomeAdapter = new ProductHomeAdapter(productModels, new OnItemClickListener() {
                        @Override
                        public void onItemClick(ProductModel productModel) {
                       Intent intent1 = new Intent(getActivity().getBaseContext(), DetailsActivity.class);
                            intent1.putExtra("productOffer",productModel);
                            getContext().startActivity(intent1);




                        }
                    },getActivity());


                    binding.rvProductHome.setAdapter(productHomeAdapter);
                    binding.rvProductHome.setHasFixedSize(true);
                    binding.rvProductHome.setClipToPadding(false);
                    binding.rvProductHome.setClipChildren(false);
//                    binding.rvProductHome.setLayoutFrozen(true);


                    binding.rvProductHome.setLayoutManager(new
                            LinearLayoutManager(getActivity(),
                            RecyclerView.VERTICAL,false));














                }
                else {
                    Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());


                }



            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));

//                sendDataToServer(user);
//                Toast.makeText(RegisterActivity.this, "sendDataToServer", Toast.LENGTH_SHORT).show();










//        ArrayList<String> images = Utils.FillImages();









//        ArrayList<CategoriesModel> categoriesModels = Utils.FillCategories();
//        AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(categoriesModels);
//
//        binding.rvAllCategories.setAdapter(allCategoriesAdapter);
//        binding.rvAllCategories.setHasFixedSize(true);
//        binding.rvAllCategories.setClipToPadding(false);
//        binding.rvAllCategories.setClipChildren(false);
//
//        binding.tvShowAllCategoriesHomeFragment.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        CategoryFragment categoryFragment = new CategoryFragment();
//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, categoryFragment).commit();
//
//                        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
//                        bottomNavigationView.setSelectedItemId(R.id.category_menu);
//
//
//
//                    }
//                }
//        );













        return binding.getRoot();

}

    public interface OnFragmentClickListener{
        void OnFragmentInteraction(ProductModel product);
    }

}