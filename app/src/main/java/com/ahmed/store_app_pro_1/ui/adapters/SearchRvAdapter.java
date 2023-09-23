package com.ahmed.store_app_pro_1.ui.adapters;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvSearchScreenBinding;
import com.ahmed.store_app_pro_1.databinding.CustomOffersItemHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.listeners.OnSearchRvClickListener;
import com.ahmed.store_app_pro_1.ui.models.LastSearchModel;
import com.ahmed.store_app_pro_1.ui.models.LastSearchModel;

import java.util.ArrayList;

public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.OffersViewHolder> {


    ArrayList<LastSearchModel> searchList;
    OnSearchRvClickListener onSearchRvClickListener;

    public SearchRvAdapter(ArrayList<LastSearchModel> searchList, OnSearchRvClickListener onSearchRvClickListener) {
        this.searchList = searchList;
        this.onSearchRvClickListener = onSearchRvClickListener;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OffersViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_rv_search_screen,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        LastSearchModel offers = searchList.get(position);
        holder.bind(offers);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    class  OffersViewHolder extends RecyclerView.ViewHolder {
        CustomItemRvSearchScreenBinding binding;
        LastSearchModel searchModel;


        public OffersViewHolder(View itemView) {
            super(itemView);
            binding = CustomItemRvSearchScreenBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSearchRvClickListener.onItemClick(searchModel.getText().toString());

                    Log.d("TAG", "onClick: "+searchModel.getText().toString());


                }
            });

        }


        public void bind(LastSearchModel searchModel){
            this.searchModel = searchModel;
            binding.tvText.setText(searchModel.getText());


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
