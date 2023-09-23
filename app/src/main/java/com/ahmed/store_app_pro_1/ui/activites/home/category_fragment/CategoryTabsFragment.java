package com.ahmed.store_app_pro_1.ui.activites.home.category_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvAllProductForDetailsProductBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentCategoryTabsBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentProductDetailsBinding;
import com.ahmed.store_app_pro_1.ui.adapters.AllCategoryWithTabsAdapter;
import com.ahmed.store_app_pro_1.ui.adapters.AllProductDetailsAdapter;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryTabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryTabsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY = "category";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String category;

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
    public static CategoryTabsFragment newInstance(String param1) {
        CategoryTabsFragment fragment = new CategoryTabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCategoryTabsBinding binding = FragmentCategoryTabsBinding.inflate(getLayoutInflater(), container, false);

        CustomItemRvAllProductForDetailsProductBinding itemBinding =
                CustomItemRvAllProductForDetailsProductBinding.inflate(getLayoutInflater(), container, false);

        ArrayList<ProductModel> product = Utils.FillProducts();






        ArrayList<ProductModel> products ;
        if (category.contains("الكل")||category.contains("أخرى")) {
            products = Utils.FillProducts();

        }
        else {
            products=Utils.getProducts(category);
        }

        AllCategoryWithTabsAdapter adapter = new AllCategoryWithTabsAdapter(products);
//        AllProductDetailsAdapter AllProductDetailsAdapter = new AllProductDetailsAdapter(allproduct);

        binding.rvAllCategoriesInProductDetailsScreen.setAdapter(adapter);
//        binding.rvAllCategoriesInProductDetailsScreen.setAdapter(AllProductDetailsAdapter);
        binding.rvAllCategoriesInProductDetailsScreen.setHasFixedSize(true);
        binding.rvAllCategoriesInProductDetailsScreen.setLayoutManager(new
                GridLayoutManager(this.getContext(),
                2,
                RecyclerView.VERTICAL,false));






        return binding.getRoot();
    }
}