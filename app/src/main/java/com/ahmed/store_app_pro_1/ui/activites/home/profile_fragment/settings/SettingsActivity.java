package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivitySettingsBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.contact_us.ContactUsActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.help_center.HelpCenterActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.privacy_policy.PrivacyPolicyActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;
    RadioButton arabic_text, hebrew_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getResources().getConfiguration().locale.getLanguage().equals("ar")) {
            binding.tvLanguageChangeSettings.setText("العربية");
        }
        else {
            binding.tvLanguageChangeSettings.setText("עברית");
        }

        SharedPreferences sharedPreferences = getSharedPreferences("lang", MODE_PRIVATE);
        String lang = sharedPreferences.getString("lang", "ar");
        if (lang.isEmpty()){
            setLocale("ar");

        }


        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );

        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        setSupportActionBar(binding.toolbar);
        Dialog dialog = new Dialog(SettingsActivity.this);

        binding.languageItemSettings.setOnClickListener(
                v -> {

                    dialog.setContentView(R.layout.change_lang_dialog);
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    dialog.setCanceledOnTouchOutside(true);


                    arabic_text = dialog.findViewById(R.id.tv_arabic_one);
                    hebrew_text = dialog.findViewById(R.id.tv_hebrew_two);


                    if (getResources().getConfiguration().locale.getLanguage().equals("ar")) {
                        arabic_text.setChecked(true);
                    }
                    else {
                        hebrew_text.setChecked(true);
                    }

                    arabic_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            dialog.dismiss();
                            Toast.makeText(SettingsActivity.this, "Arabic clicked", Toast.LENGTH_SHORT).show();

                       setLocale("ar");
                       binding.tvLanguageChangeSettings.setText("العربية");

                        }
                    });

                    hebrew_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            dialog.dismiss();
                            Toast.makeText(SettingsActivity.this, "Hebrew clicked", Toast.LENGTH_SHORT).show();
                            setLocale("iw");
                            binding.tvLanguageChangeSettings.setText("עברית");



                        }
                    });

                    dialog.show();



                }
        );


        binding.privacyPolicyItemSettings.setOnClickListener(
                v -> {
                    startActivity(new Intent(SettingsActivity.this, PrivacyPolicyActivity.class));
                }
        );
        binding.contactUsItemSettings.setOnClickListener(
                v -> {
                    startActivity(new Intent(SettingsActivity.this, ContactUsActivity.class));
                }
        );

        binding.helpCenterItemSettings.setOnClickListener(
                v -> {
                    startActivity(new Intent(SettingsActivity.this, HelpCenterActivity.class));
                }
        );




    }




    public void setLocale(String lang) { //call this in onCreate()
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, SettingsActivity.class);
        startActivity(refresh);
        finish();
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();






    }





}