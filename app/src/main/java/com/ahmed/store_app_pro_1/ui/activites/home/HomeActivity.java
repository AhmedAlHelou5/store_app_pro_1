package com.ahmed.store_app_pro_1.ui.activites.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityHomeBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.cart_fragment.CartFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.category_fragment.CategoryFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.home_fragment.HomeFragment;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        Window window = getWindow();
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");

        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        setSupportActionBar(binding.toolbar);

        binding.bottomNavigation.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);


        binding.bottomNavigation.setOnItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.home_menu:
                            replaceFragment(new HomeFragment());
                            binding.toolbarTvTitleHomeActivity.setText("الرئيسية");
                            break;
                        case R.id.category_menu:
                            replaceFragment(new CategoryFragment());
                            binding.toolbarTvTitleHomeActivity.setText("التصنيفات");

                            break;
                        case R.id.cart_menu:
                            replaceFragment(new CartFragment());
                            binding.toolbarTvTitleHomeActivity.setText("السلة");

                            break;
                        case R.id.profile_menu:
                            replaceFragment(new ProfileFragment());
                            binding.toolbarTvTitleHomeActivity.setText("حسابي");


                            break;
                    }
                    return true;



                }
        );


    }

    void  replaceFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }


}