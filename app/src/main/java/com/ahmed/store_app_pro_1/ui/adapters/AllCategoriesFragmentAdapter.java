package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomAllCategoriesItemFragmentBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryActivity;
import com.ahmed.store_app_pro_1.ui.listeners.OnCategoryClickListener;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllCategoriesFragmentAdapter extends RecyclerView.Adapter<AllCategoriesFragmentAdapter.AllCategoriesViewHolder> {


    ArrayList<CategoryModel> CategoryModels;
    OnCategoryClickListener onCategoryClickListener;
    Context context;


    public AllCategoriesFragmentAdapter(ArrayList<CategoryModel> CategoryModels, Context context, OnCategoryClickListener onCategoryClickListener) {
        this.CategoryModels = CategoryModels;
        this.context = context;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public AllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllCategoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_all_categories_item_fragment,
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
        CustomAllCategoriesItemFragmentBinding binding;
        CategoryModel categoryModel;


        public AllCategoriesViewHolder(View itemView) {
            super(itemView);
            binding = CustomAllCategoriesItemFragmentBinding.bind(itemView);
            itemView.setOnClickListener(view -> {
                onCategoryClickListener.OnCategoryClick(categoryModel);
//                Intent intent = new Intent(binding.getRoot().getContext(), CategoryActivity.class);
//                intent.putExtra("category", product.getName());
//                intent.putExtra("categoryId", product.getId());
//                binding.getRoot().getContext().startActivity(intent);


            });

        }


        public void bind(CategoryModel categoryModel){
            this.categoryModel = categoryModel;
            Glide.with(context)
                    .load(categoryModel.getIconUrl())
                    .into(binding.imageCategory);
            binding.tvTitleCategory.setText(categoryModel.getName());
//        binding.recColorItem.setAdapter();

        }




    }





}
