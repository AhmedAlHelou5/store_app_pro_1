package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvCartBinding;
import com.ahmed.store_app_pro_1.ui.models.CartModel;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.ahmed.store_app_pro_1.ui.models.orders.OrderItemModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductViewHolder> {


    ArrayList<OrderItemModel> cartList;
    Context context;

    public ProductCartAdapter(ArrayList<OrderItemModel> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_rv_cart,
                        parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        OrderItemModel popularModel = cartList.get(position);
        holder.bind(popularModel);

    }

    @Override
    public int getItemCount() {

        return cartList.size();

    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomItemRvCartBinding binding;
        OrderItemModel productList;

        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomItemRvCartBinding.bind(itemView);

        }


        public void bind(OrderItemModel productList){
            this.productList = productList;


            Glide.with(context)
                    .load(productList.getImages().get(0).getImageUrl())
                    .into(binding.imageCategory);
            binding.tvTitle.setText(productList.getName());
//            binding.tvPrice.setText(String.valueOf(productList.getPrice()));
            binding.tvCount.setText(String.valueOf(productList.getQuantity()));
            binding.tvPrice.setText(String.valueOf(productList.getPrice()));

//            notifyDataSetChanged();


            final AtomicInteger get_quantity = new AtomicInteger(Integer.parseInt(binding.tvCount.getText().toString()));
//            int price = Integer.parseInt(binding.tvPrice.getText().toString());
//            int total = Integer.parseInt(binding.tvCount.getText().toString())  * price;





            binding.btnAdd.setOnClickListener(v -> {
                get_quantity.getAndIncrement();
                binding.tvCount.setText(String.valueOf(get_quantity.get()));
//                binding.tvPrice.setText(String.valueOf(total));



            });

            binding.btnMin.setOnClickListener(v -> {
                if (get_quantity.get() > 0){
                    get_quantity.getAndDecrement();
                    binding.tvCount.setText(String.valueOf(get_quantity.get()));
//                    binding.tvPrice.setText(String.valueOf(total));

                }



            });



        }




    }





}
