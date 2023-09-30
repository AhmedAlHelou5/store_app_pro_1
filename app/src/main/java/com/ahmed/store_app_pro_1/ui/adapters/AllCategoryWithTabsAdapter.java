package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ItemCategoryWithTabsBinding;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllCategoryWithTabsAdapter extends RecyclerView.Adapter<AllCategoryWithTabsAdapter.ProductViewHolder> {


    ArrayList<ProductModel> productList;
    Context context;

    public AllCategoryWithTabsAdapter(ArrayList<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_with_tabs,
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
        ItemCategoryWithTabsBinding binding;
        ProductModel productList;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = ItemCategoryWithTabsBinding.bind(itemView);

        }


        public void bind(ProductModel productList){
            this.productList = productList;
            Glide.with(context)
                    .load(productList.getImages().get(0).getImageUrl())
                    .into(binding.imageCategory);
            binding.title.setText(productList.getName());
            binding.description.setText(productList.getDescription());
            binding.newPrice.setText(String.valueOf(productList.getPrice()));




//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
