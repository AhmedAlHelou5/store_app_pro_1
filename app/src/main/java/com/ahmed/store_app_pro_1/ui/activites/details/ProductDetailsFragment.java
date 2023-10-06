package com.ahmed.store_app_pro_1.ui.activites.details;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvAllProductForDetailsProductBinding;
import com.ahmed.store_app_pro_1.databinding.FragmentProductDetailsBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.cart.CartActivity;
import com.ahmed.store_app_pro_1.ui.adapters.AllProductDetailsAdapter;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemDetailsProductAddToCartClickListener;
import com.ahmed.store_app_pro_1.ui.models.orders.OrderItemModel;
import com.ahmed.store_app_pro_1.ui.models.product.MainProductModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.gson.Gson;

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
    private int categoryId;

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
    public static ProductDetailsFragment newInstance(String param1, int param2) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
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

        FragmentProductDetailsBinding binding = FragmentProductDetailsBinding.inflate(getLayoutInflater(), container, false);

        CustomItemRvAllProductForDetailsProductBinding itemBinding =
                CustomItemRvAllProductForDetailsProductBinding.inflate(getLayoutInflater(), container, false);
        final boolean[] isProgressVisible = {false};
        isProgressVisible[0] = true;

        binding.idPBLoading.setVisibility(View.VISIBLE);


        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);


        Log.e("TAG", "FragmentProductDetailsBinding "+categoryId);



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
//                        if (response.body().getData().get(i).getCategoryId()==categoryId){
                            products.add(response.body().getData().get(i));
//                                Log.e("TAG products", "onResponse: "+products );
                            Log.e("TAG products", "onResponse: "+products.size() );
//                                System.out.println(products.size());
//
//                        }
//
                    }






                    Log.e("TAG categoryId", "onResponse: "+categoryId );


                    ArrayList<OrderItemModel> orderModels = new ArrayList<>();

//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


                    AllProductDetailsAdapter AllProductDetailsAdapter = new AllProductDetailsAdapter(products, getActivity(), new OnItemClickListener() {
                        @Override
                        public void onItemClick(ProductModel product) {
                            Intent intent1 = new Intent(getActivity().getBaseContext(), DetailsActivity.class);
                            intent1.putExtra("productOffer", product);
                            getContext().startActivity(intent1);
                        }

                    }, new OnItemDetailsProductAddToCartClickListener() {
                        @Override
                        public void onItemClick(ProductModel productToCart) {
                            int  count = 0;

                            if (orderModels.contains(productToCart.getId())  ) {
                                orderModels.add(new OrderItemModel(productToCart.getId(), productToCart.getName(),
                                        productToCart.getOfferPrice(), productToCart.getImages(), count++));
                            }
                            orderModels.add(new OrderItemModel(productToCart.getId(), productToCart.getName(),
                                    productToCart.getOfferPrice(), productToCart.getImages(),count++));
                            SharedPreferences sp = getActivity().getSharedPreferences(Constans.SHARED_PREF, Context.MODE_PRIVATE);
                             SharedPreferences.Editor editor = sp.edit();
                            Gson gson=new Gson();
                            String json =gson.toJson(orderModels);
                            editor.putString( Constans.CART,json);
                            editor.apply();
                            editor.commit();


//                            intent.putExtra("orderModels", orderModels);



                            Toast.makeText(getActivity(), "Added Success", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "productsToCart.size(): " + orderModels.size());
                            Log.e("TAG", "productsToCart.size(): " + count);
//                            Log.e("TAG", "productsToCart.size(): " + orderModels.get(0).getProductId());
                            Log.e("TAG", "productsToCart.size(): " +    productToCart.getId());
                            Log.e("TAG", "productsToCart.size(): " + productToCart);

//                            System.out.println(count);


                        }
                    });









                    binding.rvAllCategoriesInProductDetailsScreen.setAdapter(AllProductDetailsAdapter);
                    binding.rvAllCategoriesInProductDetailsScreen.setHasFixedSize(true);
                    binding.rvAllCategoriesInProductDetailsScreen.setLayoutManager(new
                            LinearLayoutManager(getActivity(),
                            RecyclerView.VERTICAL,false));
                }
            }

            @Override
            public void onFailure(Call<MainProductModel> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });


        binding.tvTitleCategory.setText(category);
//        binding.tvDescriptionCategory.setText("");


        return binding.getRoot();
    }









}