package com.ahmed.store_app_pro_1.ui.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomAllCategoriesItemRvBinding;
import com.ahmed.store_app_pro_1.databinding.CustomOffersItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;

import java.util.ArrayList;

public class OffersHomeAdapter extends RecyclerView.Adapter<OffersHomeAdapter.OffersViewHolder> {


    ArrayList<OfferModel> offersList;

    public OffersHomeAdapter(ArrayList<OfferModel> offers) {
        this.offersList = offers;
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
        OfferModel offers = offersList.get(position);
        holder.bind(offers);
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class  OffersViewHolder extends RecyclerView.ViewHolder {
        CustomOffersItemHomeFragmentBinding binding;
        OfferModel offers;


        public OffersViewHolder(View itemView) {
            super(itemView);
            binding = CustomOffersItemHomeFragmentBinding.bind(itemView);

        }


        public void bind(OfferModel offers){
            this.offers = offers;
            binding.imageCategory.setImageResource(offers.getImage());
            binding.title.setText(offers.getName());
            binding.description.setText(offers.getDescription());
            binding.oldPrice.setText(offers.getOldPrice());
            binding.newPrice.setText(offers.getNewPrice());
            binding.oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
