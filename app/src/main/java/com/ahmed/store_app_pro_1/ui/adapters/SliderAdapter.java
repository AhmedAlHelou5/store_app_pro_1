package com.ahmed.store_app_pro_1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemImgaeSliderHomeFragmentBinding;
import com.ahmed.store_app_pro_1.ui.models.SliderImageHomeModel;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {
    ArrayList<SliderImageHomeModel> images;


    public SliderAdapter(ArrayList<SliderImageHomeModel> images) {
        this.images = images;
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
        binding.imageView1.setImageResource(images.get(position).getImage());
        container.addView(binding.getRoot());

        return binding.getRoot();
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
