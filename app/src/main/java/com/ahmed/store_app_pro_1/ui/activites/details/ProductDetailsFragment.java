package com.ahmed.store_app_pro_1.ui.activites.details;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvAllProductForDetailsProductBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentProductDetailsBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.AllProductDetailsAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PopularHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductHomeAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemOfferClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.home.SliderHomeModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_CATEGORY_ID = "category_id";

    // TODO: Rename and change types of parameters
    private String category;
    private String categoryId;

//    private String mParam2;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProductDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailsFragment newInstance(String param1, String param2) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, param1);
        args.putString(ARG_CATEGORY_ID, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentProductDetailsBinding binding = FragmentProductDetailsBinding.inflate(getLayoutInflater(), container, false);

        CustomItemRvAllProductForDetailsProductBinding itemBinding =
                CustomItemRvAllProductForDetailsProductBinding.inflate(getLayoutInflater(), container, false);

//        ArrayList<ProductModel> product = Utils.FillProducts();

        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Map<String, Object> search = new HashMap<>();
        search.put("", "application/json");

//        Call<ProductModel> call = apiInterface.GetProductByCategory();
//
//
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
//
//
////  categories rv
//                    AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(categories,getActivity());
//
//                    binding.rvAllCategoriesInProductDetailsScreen.setAdapter(allCategoriesAdapter);
//                    binding.rvAllCategoriesInProductDetailsScreen.setHasFixedSize(true);
//                    binding.rvAllCategoriesInProductDetailsScreen.setClipToPadding(false);
//                    binding.rvAllCategoriesInProductDetailsScreen.setClipChildren(false);
//                    binding.rvAllCategoriesInProductDetailsScreen.setLayoutManager(new
//                            LinearLayoutManager(getActivity(),
//                            RecyclerView.HORIZONTAL,false));
//
//
//
//
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
//                Toast.makeText(getActivity(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.e("TAG", "onFailure: " + t.getMessage());
//
//
//            }
//        }));
//




        ArrayList<ProductModel> products = new ArrayList<>();
        if (category.contains("الكل")){





//            products = Utils.FillProducts();
            binding.tvTitleCategory.setText("كل المنتجات");

        }
       else {
//            products=Utils.getProducts(category);
            binding.tvTitleCategory.setText(category);
        }

















//        AllProductDetailsAdapter adapter = new AllProductDetailsAdapter(products);
        AllProductDetailsAdapter AllProductDetailsAdapter = new AllProductDetailsAdapter(products,getActivity());

//        binding.rvAllCategoriesInProductDetailsScreen.setAdapter(adapter);
        binding.rvAllCategoriesInProductDetailsScreen.setAdapter(AllProductDetailsAdapter);
        binding.rvAllCategoriesInProductDetailsScreen.setHasFixedSize(true);
        binding.rvAllCategoriesInProductDetailsScreen.setLayoutManager(new
                LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL,false));








        return binding.getRoot();
    }









}