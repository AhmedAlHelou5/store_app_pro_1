package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomAllCategoriesItemRvBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.AllCategoriesViewHolder> {


    ArrayList<CategoryModel> CategoryModels;
    OnCategoryClickListener onCategoryClickListener;
    Context context;

    public AllCategoriesAdapter(ArrayList<CategoryModel> CategoryModels, Context context,OnCategoryClickListener onCategoryClickListener) {
        this.CategoryModels = CategoryModels;
        this.context = context;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public AllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllCategoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_all_categories_item_rv,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesViewHolder holder, int position) {
        CategoryModel categories = CategoryModels.get(position);
        holder.bind(categories);
    }

    @Override
    public int getItemCount() {
        return CategoryModels.size();
    }

    class  AllCategoriesViewHolder extends RecyclerView.ViewHolder {
        CustomAllCategoriesItemRvBinding  binding;
        CategoryModel product;


        public AllCategoriesViewHolder(View itemView) {
            super(itemView);
            binding = CustomAllCategoriesItemRvBinding.bind(itemView);
            itemView.setOnClickListener(view -> {
                onCategoryClickListener.OnCategoryClick(product);




            });

        }


        public void bind(CategoryModel product){
            this.product = product;
            Glide.with(context)
                    .load(product.getIconUrl())
                    .into(binding.imageCategory);
            binding.tvTitleCategory.setText(product.getName());

        }




    }





}
