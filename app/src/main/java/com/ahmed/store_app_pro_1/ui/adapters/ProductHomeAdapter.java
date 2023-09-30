package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomHomeItemRvBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductViewHolder> {


    ArrayList<ProductModel> productList;
    OnItemClickListener onItemClickListener;
    Context context;

    public ProductHomeAdapter(ArrayList<ProductModel> productList, OnItemClickListener onItemClickListener, Context context) {
        this.productList = productList;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(productList);
                }
            });

        }


        public void bind(ProductModel productList){
            this.productList = productList;
            Glide.with(context)
                    .load(productList.getImages().get(productList.getImages().size()-1).getImageUrl())
                    .into(binding.imageCategory);
            binding.title.setText(productList.getName());
            binding.tvDescriptionProduct.setText(productList.getDescription());
            binding.price.setText(String.valueOf(productList.getPrice()));


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
