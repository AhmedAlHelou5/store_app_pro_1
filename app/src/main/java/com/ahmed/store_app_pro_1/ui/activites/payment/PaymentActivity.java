package com.ahmed.store_app_pro_1.ui.activites.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ScrollView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityPaymentBinding;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.getRoot().setNestedScrollingEnabled(true);






        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );

        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });


        setSupportActionBar(binding.toolbar);


        binding.linearCash.setOnClickListener(this); // calling onClick() method
        binding.linearVisa.setOnClickListener(this);

        binding.btnBackBottom.setBackground(getResources().getDrawable(R.drawable.on_boarding_btn_login));

        binding.cashIcon.setImageDrawable(getResources().getDrawable(R.drawable.cash_select));
        binding.tvCash.setTextColor(getResources().getColor(R.color.white));
        binding.linearCash.setBackground(getResources().getDrawable(R.drawable.bg_btn_selected_payment_cash));

        binding.linerEnterDataVisa.setVisibility(View.GONE);




        binding.btnBackBottom.setOnClickListener(v -> {
            onBackPressed();
        });




    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_cash:
                binding.cashIcon.setImageDrawable(getResources().getDrawable(R.drawable.cash_select));
                binding.tvCash.setTextColor(getResources().getColor(R.color.white));
                binding.linearCash.setBackground(getResources().getDrawable(R.drawable.bg_btn_selected_payment_cash));

                binding.visaIcon.setImageDrawable(getResources().getDrawable(R.drawable.visa_un_select));
                binding.linearVisa.setBackground(getResources().getDrawable(R.drawable.bg_btn_un_select_payment_cash));

                binding.linerEnterDataVisa.setVisibility(View.GONE);




                break;
            case R.id.linear_visa:

                binding.visaIcon.setImageDrawable(getResources().getDrawable(R.drawable.visa_selected));
                binding.linearVisa.setBackground(getResources().getDrawable(R.drawable.bg_btn_selected_payment_cash));

                binding.cashIcon.setImageDrawable(getResources().getDrawable(R.drawable.cash_icon));
                binding.tvCash.setTextColor(getResources().getColor(R.color.black));
                binding.linearCash.setBackground(getResources().getDrawable(R.drawable.bg_btn_un_select_payment_cash));
                binding.linerEnterDataVisa.setVisibility(View.VISIBLE);





                break;
            default:
                binding.cashIcon.setImageDrawable(getResources().getDrawable(R.drawable.cash_select));
                binding.tvCash.setTextColor(getResources().getColor(R.color.white));
                binding.linearCash.setBackground(getResources().getDrawable(R.drawable.bg_btn_selected_payment_cash));
                binding.linerEnterDataVisa.setVisibility(View.GONE);


                break;
        }
    }



}