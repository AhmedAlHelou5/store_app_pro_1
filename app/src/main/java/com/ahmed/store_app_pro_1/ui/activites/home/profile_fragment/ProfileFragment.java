package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.store_app_pro_1.databinding.FragmentProfileBinding;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.about_app.AboutAppActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.favorite.FavoriteActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.myProfile.PersonalDataActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.notifications.NotificationsActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.orders.OrdersActivity;
import com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.settings.SettingsActivity;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileFragment.
//     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(getLayoutInflater(), container, false);
        binding.myOrdersItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getActivity(), OrdersActivity.class));

            }
        });

        binding.profileItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PersonalDataActivity.class));

            }
        });

        binding.myOrdersItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), OrdersActivity.class));

            } });


        binding.settignItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });


        binding.notificationItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationsActivity.class));
            }
        });

        binding.likesItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FavoriteActivity.class));
            }
        });


        binding.aboutItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutAppActivity.class));
            }
        });







        binding.signOutItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });













        String phone="1234567890";

        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        String countryIso = telephonyManager.getNetworkCountryIso().toUpperCase();              //important!!

        binding.tvPhoneNumber.setText(PhoneNumberUtils.formatNumber(phone, countryIso));



        return binding.getRoot();

    }

}