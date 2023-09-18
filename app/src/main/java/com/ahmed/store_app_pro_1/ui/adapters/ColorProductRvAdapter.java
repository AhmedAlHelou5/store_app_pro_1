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

public class ColorProductRvAdapter extends RecyclerView.Adapter<ColorProductRvAdapter.ProductViewHolder> {


    ArrayList<ProductModel> colorProductList;

    public ColorProductRvAdapter(ArrayList<ProductModel> colorProductList) {
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
        ProductModel colorProductModel = colorProductList.get(position);
        holder.bind(colorProductModel);
    }

    @Override
    public int getItemCount() {
        return colorProductList.size();
    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomColorItemProductDetailScreenBinding binding;
        ProductModel colorProductModel;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomColorItemProductDetailScreenBinding.bind(itemView);

        }


        public void bind(ProductModel colorProductModel){
            this.colorProductModel = colorProductModel;

            binding.imageColor.setImageDrawable((Drawable) colorProductModel.getColors());

//            binding.colorItem.;


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
