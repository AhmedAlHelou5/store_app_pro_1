package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomOffersItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemOfferClickListener;
import com.ahmed.store_app_pro_1.ui.models.home.OfferModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OffersHomeAdapter extends RecyclerView.Adapter<OffersHomeAdapter.OffersViewHolder> {


    ArrayList<ProductModel> offersList;
    OnItemOfferClickListener onItemClickListener;
    Context context;

    public OffersHomeAdapter(ArrayList<ProductModel> offers, Context context , OnItemOfferClickListener onItemClickListener) {
        this.offersList = offers;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OffersViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_offers_item_home_fragment,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        ProductModel offers = offersList.get(position);
        holder.bind(offers);
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class  OffersViewHolder extends RecyclerView.ViewHolder {
        CustomOffersItemHomeFragmentBinding binding;
        ProductModel offers;


        public OffersViewHolder(View itemView) {
            super(itemView);
            binding = CustomOffersItemHomeFragmentBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(offers);

                }
            }
            );

        }


        public void bind(ProductModel offers){
            this.offers = offers;
            Glide.with(context)
                    .load(offers.getImages().get(0).getImageUrl())
                    .into(binding.imageCategory);
            binding.title.setText(offers.getName());
            binding.description.setText(offers.getDescription());
            binding.oldPrice.setText(String.valueOf(offers.getPrice()));
            binding.newPrice.setText(String.valueOf(offers.getOfferPrice()));
            binding.oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
