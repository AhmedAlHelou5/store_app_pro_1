package com.ahmed.store_app_pro_1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ahmed.store_app_pro_1.databinding.CustomItemImgaeSliderHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.models.images.ImageModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SliderImagesProductAdapter extends PagerAdapter {
    List<ImageModel> images;
    private Context context;

    public SliderImagesProductAdapter(List<ImageModel> images, Context context) {
        this.images =  images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CustomItemImgaeSliderHomeFragmentBinding binding = CustomItemImgaeSliderHomeFragmentBinding.inflate(LayoutInflater.from(container.getContext()));
//        binding.imageView1.set(Integer.parseInt(images.get(position).getImageUrl()));
//        Glide.with(container).load(images.get(position).getImageUrl().toString()).into(binding.imageView1);
        String url = images.get(position).getImageUrl();
//        Toast.makeText(container.getContext(), "url"+url, Toast.LENGTH_SHORT).show();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(binding.imageView1);

        container.addView(binding.getRoot());

        return binding.getRoot();
    }


//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//
//    }
}
