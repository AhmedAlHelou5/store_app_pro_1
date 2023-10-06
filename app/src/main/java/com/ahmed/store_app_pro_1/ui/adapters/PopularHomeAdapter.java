package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomPopularItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;

import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class PopularHomeAdapter extends RecyclerView.Adapter<PopularHomeAdapter.PopularViewHolder> {


    ArrayList<ProductModel> popularList;
    OnItemClickListener onItemClickListener;
    Context context;

    public PopularHomeAdapter(ArrayList<ProductModel> popularList, OnItemClickListener onItemClickListener, Context context) {
        this.popularList = popularList;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_popular_item_home_fragment,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        ProductModel popularModel = popularList.get(position);
        holder.bind(popularModel);
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    class  PopularViewHolder extends RecyclerView.ViewHolder {
        CustomPopularItemHomeFragmentBinding binding;
        ProductModel popularModel;


        public PopularViewHolder(View itemView) {
            super(itemView);
            binding = CustomPopularItemHomeFragmentBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(popularModel);
                }
            });

        }


        public void bind(ProductModel popularModel){
            this.popularModel = popularModel;
            Glide.with(context)
                    .load(popularModel.getImages().get(0).getImageUrl())
                    .into(binding.imageCategory);
            binding.title.setText(popularModel.getName());
            binding.description.setText(popularModel.getDescription());
            binding.newPrice.setText(String.valueOf(popularModel.getOfferPrice()));


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
