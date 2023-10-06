package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvAllProductForDetailsProductBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentCategoryTabsBinding;
import com.ahmed.store_app_pro_1.databinding.ItemCategoryWithTabsBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.details.DetailsActivity;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoryWithTabsAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.AllProductDetailsAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.models.product.MainProductModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryTabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryTabsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_CATEGORY_ID = "category_id";

    // TODO: Rename and change types of parameters
    private String category;
    private int categoryId;

    public CategoryTabsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CategoryTabsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryTabsFragment newInstance(String param1,int param2) {
        CategoryTabsFragment fragment = new CategoryTabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, param1);
        args.putInt(ARG_CATEGORY_ID, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
            categoryId = getArguments().getInt(ARG_CATEGORY_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCategoryTabsBinding binding = FragmentCategoryTabsBinding.inflate(getLayoutInflater(), container, false);
        ItemCategoryWithTabsBinding itemBinding =
                ItemCategoryWithTabsBinding.inflate(getLayoutInflater(), container, false);
        final boolean[] isProgressVisible = {false};
        isProgressVisible[0] = true;

        binding.idPBLoading.setVisibility(View.VISIBLE);


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Log.e("TAG", "onCreateView: SubcategoryId "+categoryId);



        Map<String, Object> searchId = new HashMap<>();
        searchId.put("category_id", categoryId);
//        searchId.put("search_name", category);
        ArrayList<ProductModel> products = new ArrayList<>();

            Call<MainProductModel> callId = apiInterface.GetProductByCategoryId(searchId);
            callId.enqueue(new Callback<MainProductModel>() {
                @Override
                public void onResponse(Call<MainProductModel> call, Response<MainProductModel> response) {
                    if (response.isSuccessful()){
                        assert response.body() != null;
                        isProgressVisible[0] = false;

                        binding.idPBLoading.setVisibility(View.GONE);
                        Log.e("TAG", "onResponse: MainProductModel " + response.body());


                            for (int i=0;i<response.body().getData().size();i++){
                            if (response.body().getData().get(i).getCategoryId()==categoryId){
                                products.add(response.body().getData().get(i));
//                                Log.e("TAG products", "onResponse: "+products );
                                Log.e("TAG products", "onResponse: "+products.size() );
//                                System.out.println(products.size());
//
                            }
//
                            }






                        Log.e("TAG categoryId", "onResponse: "+categoryId );




                        AllCategoryWithTabsAdapter allCategoryWithTabsAdapter = new AllCategoryWithTabsAdapter(products,getActivity(), new OnItemClickListener() {
                            @Override
                            public void onItemClick(ProductModel product) {
                                Intent intent1 = new Intent(getActivity().getBaseContext(), DetailsActivity.class);
                                intent1.putExtra("productOffer",product);
                                getContext().startActivity(intent1);
                            }

                        });

//        binding.rvAllCategoriesInProductDetailsScreen.setAdapter(adapter);
                        binding.rvAllCategoriesInProductDetailsScreen.setAdapter(allCategoryWithTabsAdapter);
                        binding.rvAllCategoriesInProductDetailsScreen.setHasFixedSize(true);
                        binding.rvAllCategoriesInProductDetailsScreen.setLayoutManager(new
                                GridLayoutManager(getActivity(),
                                2,
                                RecyclerView.VERTICAL,false));
                    }
                }

                @Override
                public void onFailure(Call<MainProductModel> call, Throwable t) {
                    Toast.makeText(getActivity(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("TAG", "onFailure: " + t.getMessage());
                }
            });

        return binding.getRoot();
    }
}