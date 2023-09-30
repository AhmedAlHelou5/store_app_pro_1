package com.ahmed.store_app_pro_1.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomColorItemProductDetailScreenBinding;
import com.ahmed.store_app_pro_1.ui.models.colors.ColorModel;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class ColorProductRvAdapter extends RecyclerView.Adapter<ColorProductRvAdapter.ProductViewHolder> {


    List<ColorModel> colorProductList;
    Context context;

    public ColorProductRvAdapter(List<ColorModel> colorProductList, Context context) {
        this.colorProductList = colorProductList;
        this.context = context;
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
        List<ColorModel> colorProductModel = Collections.singletonList(colorProductList.get(position));
        holder.bind(colorProductModel);
    }

    @Override
    public int getItemCount() {
        return colorProductList.size();
    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomColorItemProductDetailScreenBinding binding;
        List<ColorModel> colorProductModel;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomColorItemProductDetailScreenBinding.bind(itemView);

        }


        @SuppressLint("ResourceType")
        public void bind(List<ColorModel> colorProductModel){
            this.colorProductModel = colorProductModel;

            if (colorProductModel.get(0).getCode() != null){
               binding.imageColor.setColorFilter(Color.parseColor(colorProductModel.get(0).getCode()));
            }



        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
