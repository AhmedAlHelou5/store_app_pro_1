package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.help_center;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityHelpCenterBinding;

public class HelpCenterActivity extends AppCompatActivity {
    ActivityHelpCenterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });


        binding.lytVisible1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hiddenView = binding.lytHidden1.findViewById(R.id.lytHidden1);
                hiddenView.setVisibility( hiddenView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (hiddenView.getVisibility() == View.VISIBLE){
                    binding.lytVisible1.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                    binding.imgArrow1.setImageDrawable(getResources().getDrawable(R.drawable.arrow_top));
                    binding.tvWhatIsOurApplication.setTextColor(getResources().getColor(R.color.white));
                }
                else if(hiddenView.getVisibility() == View.GONE) {
                    binding.lytVisible1.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.imgArrow1.setImageDrawable(getResources().getDrawable(R.drawable.arrow_bottom));
                    binding.tvWhatIsOurApplication.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });


        binding.lytVisible2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View hiddenView = binding.lytHidden2.findViewById(R.id.lytHidden2);
                hiddenView.setVisibility( hiddenView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (hiddenView.getVisibility() == View.VISIBLE){
                    binding.lytVisible2.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                    binding.imgArrow2.setImageDrawable(getResources().getDrawable(R.drawable.arrow_top));
                    binding.tvWhatDoesOurAppDo.setTextColor(getResources().getColor(R.color.white));
                }
                else if(hiddenView.getVisibility() == View.GONE) {
                    binding.lytVisible2.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.imgArrow2.setImageDrawable(getResources().getDrawable(R.drawable.arrow_bottom));
                    binding.tvWhatDoesOurAppDo.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });




    }
}