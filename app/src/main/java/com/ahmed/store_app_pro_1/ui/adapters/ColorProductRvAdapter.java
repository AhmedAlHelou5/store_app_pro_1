package com.ahmed.store_app_pro_1.ui.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomColorItemProductDetailScreenBinding;
import com.ahmed.store_app_pro_1.databinding.CustomHomeItemRvBinding;
import com.ahmed.store_app_pro_1.ui.models.ColorProductModel;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColorProductRvAdapter extends RecyclerView.Adapter<ColorProductRvAdapter.ProductViewHolder> {


    List<Integer> colorProductList;

    public ColorProductRvAdapter(List<Integer> colorProductList) {
        this.colorProductList = colorProductList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_color_item__product_detail_screen,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        List<Integer> colorProductModel = Collections.singletonList(colorProductList.get(position));
        holder.bind(colorProductModel);
    }

    @Override
    public int getItemCount() {
        return colorProductList.size();
    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomColorItemProductDetailScreenBinding binding;
        List<Integer> colorProductModel;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomColorItemProductDetailScreenBinding.bind(itemView);

        }


        public void bind(List<Integer> colorProductModel){
            this.colorProductModel = colorProductModel;

            binding.imageColor.setImageResource(colorProductModel.get(0));

//            binding.colorItem.;


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
