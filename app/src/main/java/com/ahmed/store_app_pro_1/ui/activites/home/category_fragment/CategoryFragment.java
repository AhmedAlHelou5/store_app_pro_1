package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.FragmentCategoryBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesFragmentAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.OffersHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.PopularHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.ProductHomeAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.SliderAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnMostSolidClickListener;
import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;
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
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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

        FragmentCategoryBinding binding = FragmentCategoryBinding.inflate(getLayoutInflater(), container, false);


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Call<HomeModel> call = apiInterface.GetHomeData();


        call.enqueue((new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {

                if (response.isSuccessful() ){
                    assert response.body() != null;
//                    Toast.makeText(getActivity(), "Success body" + response.body(), Toast.LENGTH_LONG).show();

                    ArrayList<CategoryModel> categories = (ArrayList<CategoryModel>) response.body().getData().getCategories();

                    Log.e("TAG", "onResponse:getFcm "+response.body().getData());

//  categories rv
                    AllCategoriesFragmentAdapter allCategoriesAdapter = new AllCategoriesFragmentAdapter(categories,getActivity());

//                    AllCategoriesFragmentAdapter allCategoriesAdapter = new AllCategoriesFragmentAdapter(categoriesModels);
//
                    binding.rvAllCategoriesFragment.setAdapter(allCategoriesAdapter);
                    binding.rvAllCategoriesFragment.setHasFixedSize(true);
                    binding.rvAllCategoriesFragment.setClipToPadding(false);
                    binding.rvAllCategoriesFragment.setClipChildren(false);
                    binding.rvAllCategoriesFragment.setLayoutManager(new
                            GridLayoutManager(getActivity(),
                            2,
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










        return binding.getRoot();
    }
}