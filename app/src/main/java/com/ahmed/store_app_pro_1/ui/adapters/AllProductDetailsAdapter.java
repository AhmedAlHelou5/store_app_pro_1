package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvAllProductForDetailsProductBinding;
import com.ahmed.store_app_pro_1.databinding.ItemCategoryWithTabsBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemDetailsProductAddToCartClickListener;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllProductDetailsAdapter extends RecyclerView.Adapter<AllProductDetailsAdapter.ProductViewHolder> {


    ArrayList<ProductModel> productList;
    OnItemClickListener onItemClickListener;
    OnItemDetailsProductAddToCartClickListener onItemDetailsProductAddToCartClickListener;
    Context context;

    public AllProductDetailsAdapter(ArrayList<ProductModel> productList, Context context, OnItemClickListener onItemClickListener, OnItemDetailsProductAddToCartClickListener onItemDetailsProductAddToCartClickListener) {
        this.productList = productList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.onItemDetailsProductAddToCartClickListener = onItemDetailsProductAddToCartClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_rv_all_product_for_details_product,
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
        CustomItemRvAllProductForDetailsProductBinding binding;
        ProductModel productList;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomItemRvAllProductForDetailsProductBinding.bind(itemView);
            itemView.findViewById(R.id.constraint_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(productList);
                }
            });

            itemView.findViewById(R.id.icon_add_to_cart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemDetailsProductAddToCartClickListener.onItemClick(productList);
                }
            });

        }


        public void bind(ProductModel productList){
            this.productList = productList;
            Glide.with(context)
                    .load(productList.getImages().get(0).getImageUrl())
                    .into(binding.imageCategory);
            binding.tvTitle.setText(productList.getName());
            binding.tvDescriptionProduct.setText(productList.getDescription());
            binding.tvPrice.setText(String.valueOf(productList.getPrice()));


        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
