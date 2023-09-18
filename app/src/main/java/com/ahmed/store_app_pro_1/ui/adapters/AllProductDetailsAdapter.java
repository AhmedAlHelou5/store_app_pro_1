package com.ahmed.store_app_pro_1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomHomeItemRvBinding;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvAllProductForDetailsProductBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;

import java.util.ArrayList;

public class AllProductDetailsAdapter extends RecyclerView.Adapter<AllProductDetailsAdapter.ProductViewHolder> {


    ArrayList<ProductModel> productList;

    public AllProductDetailsAdapter(ArrayList<ProductModel> productList) {
        this.productList = productList;
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

        }


        public void bind(ProductModel productList){
            this.productList = productList;
            binding.imageCategory.setImageResource(productList.getImage());
            binding.tvTitle.setText(productList.getTitle());
            binding.tvPrice.setText(productList.getPrice());
            binding.tvDescriptionProduct.setText(productList.getDescription());


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
