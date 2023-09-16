package com.ahmed.store_app_pro_1.ui.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomOffersItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.databinding.CustomPopularItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.models.PopularModel;

import java.util.ArrayList;

public class PopularHomeAdapter extends RecyclerView.Adapter<PopularHomeAdapter.PopularViewHolder> {


    ArrayList<PopularModel> popularList;

    public PopularHomeAdapter(ArrayList<PopularModel> popularList) {
        this.popularList = popularList;
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
        PopularModel popularModel = popularList.get(position);
        holder.bind(popularModel);
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    class  PopularViewHolder extends RecyclerView.ViewHolder {
        CustomPopularItemHomeFragmentBinding binding;
        PopularModel popularModel;


        public PopularViewHolder(View itemView) {
            super(itemView);
            binding = CustomPopularItemHomeFragmentBinding.bind(itemView);

        }


        public void bind(PopularModel popularModel){
            this.popularModel = popularModel;
            binding.imageCategory.setImageResource(popularModel.getImage());
            binding.title.setText(popularModel.getName());
            binding.description.setText(popularModel.getDescription());
            binding.newPrice.setText(popularModel.getPrice());


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
