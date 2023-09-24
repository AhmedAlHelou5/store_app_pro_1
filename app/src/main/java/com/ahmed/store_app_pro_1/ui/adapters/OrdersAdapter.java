package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomHomeItemRvBinding;
import com.ahmed.store_app_pro_1.databinding.CustomItemOrderRvBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.orders.OrderDetailsActivity;
import com.ahmed.store_app_pro_1.ui.listeners.OnItemClickListener;
import com.ahmed.store_app_pro_1.ui.listeners.OnOrderClickListener;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ProductViewHolder> {


    ArrayList<OrderModel> orderList;
    OnOrderClickListener onOrderClickListener;

    public OrdersAdapter(ArrayList<OrderModel> orderList, OnOrderClickListener onOrderClickListener) {
        this.orderList = orderList;
        this.onOrderClickListener = onOrderClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_order_rv,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        OrderModel orderModel = orderList.get(position);
        holder.bind(orderModel);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomItemOrderRvBinding binding;
        OrderModel orderList;


        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomItemOrderRvBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrderClickListener.onItemClick(orderList);




                }
            });

        }


        public void bind(OrderModel orderList){
            this.orderList = orderList;

            if (orderList.getStatus().equals("done" )|| orderList.getStatus().equals("مكتمل")){
                binding.tvDone.setBackgroundResource(R.drawable.bg_liner_text_order_done);
            }
            else if (orderList.getStatus().equals("recive")||orderList.getStatus().equals("مستلم")){
                binding.tvDone.setBackgroundResource(R.drawable.bg_liner_text_order_recive);

            }
            else {
                binding.tvDone.setBackgroundResource(R.drawable.bg_liner_text_order_recive);

            }

            binding.tvNumOrder.setText(orderList.getId()+"");
            binding.tvCountry.setText(orderList.getCity());
            binding.tvDone.setText(orderList.getStatus());
            binding.tvTotalPrice.setText(orderList.getTotal()+"");


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
