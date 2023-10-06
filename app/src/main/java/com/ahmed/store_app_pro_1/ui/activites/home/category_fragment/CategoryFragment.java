package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.databinding.FragmentCategoryBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoriesFragmentAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryMainModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;

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
        final boolean[] isProgressVisible = {false};
        isProgressVisible[0] = true;
        binding.idPBLoading.setVisibility(View.VISIBLE);


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Call<CategoryMainModel> call = apiInterface.GetCategory();

        ArrayList<CategoryModel> categories = new ArrayList<>();

        call.enqueue((new Callback<CategoryMainModel>() {
            @Override
            public void onResponse(Call<CategoryMainModel> call, Response<CategoryMainModel> response) {

                if (response.isSuccessful() ){
                    assert response.body() != null;
                    isProgressVisible[0] = false;
                    binding.idPBLoading.setVisibility(View.GONE);

                    categories.addAll(response.body().getData());


                    Log.e("TAG  (ArrayList<CategoryModel>) response.body() ", "onResponse:getFcm "+response.body());

//  categories rv
                    AllCategoriesFragmentAdapter allCategoriesAdapter = new AllCategoriesFragmentAdapter(categories,getActivity(), new OnCategoryClickListener() {
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
            public void onFailure(Call<CategoryMainModel> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());


            }
        }));










        return binding.getRoot();
    }
}