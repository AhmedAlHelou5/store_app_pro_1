package com.ahmed.store_app_pro_1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvCartBinding;
import com.ahmed.store_app_pro_1.ui.models.CartModel;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductViewHolder> {


    ArrayList<CartModel> cartList;

    public ProductCartAdapter(ArrayList<CartModel> cartList) {
        this.cartList = cartList;
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
        CartModel popularModel = cartList.get(position);
        holder.bind(popularModel);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class  ProductViewHolder extends RecyclerView.ViewHolder {
        CustomItemRvCartBinding binding;
        CartModel productList;

        public ProductViewHolder(View itemView) {
            super(itemView);
            binding = CustomItemRvCartBinding.bind(itemView);

        }


        public void bind(CartModel productList){
            this.productList = productList;


            binding.imageCategory.setImageResource(productList.getImage());
            binding.tvTitle.setText(productList.getTitle());
            binding.tvPrice.setText(productList.getPrice());
            binding.tvCount.setText(String.valueOf(productList.getQuantity()));



            AtomicInteger num = new AtomicInteger();
            final AtomicInteger get_quantity = new AtomicInteger(Integer.parseInt(binding.tvCount.getText().toString()));
            final double get_price= Double.parseDouble( binding.tvPrice.getText().toString());
            final double get_total= get_quantity.get() *get_price;
            if (get_quantity.get() ==0){


            }




            binding.btnAdd.setOnClickListener(v -> {
                get_quantity.getAndIncrement();
                binding.tvCount.setText(String.valueOf(get_quantity.get()));
//                double total=num.get()*get_price;

//                binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));



            });

            binding.btnMin.setOnClickListener(v -> {
                if (get_quantity.get() > 0){
                    get_quantity.getAndDecrement();
                    binding.tvCount.setText(String.valueOf(get_quantity.get()));
//                    double total=num.get()*get_price;
//                    binding.tvPriceAddToCart.setText(total+"");
//                    binding.tvPriceAddToCart.setText(String.format(Locale.ENGLISH, "%.2f", total));


                }
                if (num.get()==0){




//                binding.tvPriceAddToCart.setText(get_price+"");



//                    binding.tvPriceAddToCart.setText(get_price+"");
                }


            });


//        binding.recColorItem.setAdapter();

        }




    }


//textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);



}
