package com.ahmed.store_app_pro_1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomAllCategoriesItemFragmentBinding;
import com.ahmed.store_app_pro_1.databinding.CustomAllCategoriesItemRvBinding;
import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;

import java.util.ArrayList;

public class AllCategoriesFragmentAdapter extends RecyclerView.Adapter<AllCategoriesFragmentAdapter.AllCategoriesViewHolder> {


    ArrayList<CategoriesModel> categoriesModels;

    public AllCategoriesFragmentAdapter(ArrayList<CategoriesModel> categoriesModels) {
        this.categoriesModels = categoriesModels;
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
        CategoriesModel categories = categoriesModels.get(position);
        holder.bind(categories);
    }

    @Override
    public int getItemCount() {
        return categoriesModels.size();
    }

    class  AllCategoriesViewHolder extends RecyclerView.ViewHolder {
        CustomAllCategoriesItemFragmentBinding binding;
        CategoriesModel product;


        public AllCategoriesViewHolder(View itemView) {
            super(itemView);
            binding = CustomAllCategoriesItemFragmentBinding.bind(itemView);

        }


        public void bind(CategoriesModel product){
            this.product = product;
            binding.imageCategory.setImageResource(product.getImage());
            binding.tvTitleCategory.setText(product.getName());
//        binding.recColorItem.setAdapter();

        }




    }





}
