package com.ahmed.store_app_pro_1.ui.activites.home.profile_fragment.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.Utils;
import com.ahmed.store_app_pro_1.databinding.ActivityNotificationsBinding;
import com.ahmed.store_app_pro_1.ui.adapters.NotificationAdapter;
import com.ahmed.store_app_pro_1.ui.models.NotificationModel;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Window window = getWindow();
        window.setStatusBarColor( getResources().getColor(R.color.primary_color) );
        binding.toolbar.showOverflowMenu();
        binding.toolbar.setTitle("");
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });





        ArrayList<NotificationModel> notifications = Utils.FillNotification();

        NotificationAdapter adapter = new NotificationAdapter(notifications);

        binding.rvNotification.setAdapter(adapter);
        binding.rvNotification.setHasFixedSize(true);
        binding.rvNotification.setLayoutManager(new
                LinearLayoutManager(getBaseContext(),
                RecyclerView.VERTICAL,false));








    }
}