package com.ahmed.store_app_pro_1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomHomeItemRvBinding;
import com.ahmed.store_app_pro_1.databinding.CustomPopularItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.models.PopularModel;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;

import java.util.ArrayList;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductViewHolder> {


    ArrayList<ProductModel> productList;

    public ProductHomeAdapter(ArrayList<ProductModel> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_home_item_rv,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel popularModel = productList.get(position);
        holder.bind(popularModel);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomHomeItemRvBinding binding;
        ProductModel productList;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomHomeItemRvBinding.bind(itemView);

        }


        public void bind(ProductModel productList){
            this.productList = productList;
            binding.imageCategory.setImageResource(productList.getImage());
            binding.title.setText(productList.getTitle());
            binding.numKm.setText(productList.getNumKm());
            binding.numTime.setText(productList.getNumTime());


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
