package com.ahmed.store_app_pro_1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.CustomItemNotificationBinding;
import com.ahmed.store_app_pro_1.databinding.CustomItemRvCartBinding;
import com.ahmed.store_app_pro_1.ui.models.NotificationModel;
import com.ahmed.store_app_pro_1.ui.models.NotificationModel;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {


    ArrayList<NotificationModel> notificationList;

    public NotificationAdapter(ArrayList<NotificationModel> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_notification,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationModel popularModel = notificationList.get(position);
        holder.bind(popularModel);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        CustomItemNotificationBinding binding;
        NotificationModel notificationList;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            binding = CustomItemNotificationBinding.bind(itemView);

        }


        public void bind(NotificationModel notificationList) {
            this.notificationList = notificationList;

            binding.tvTime.setText(notificationList.getDate());
            binding.tvNotificationBottomSettings.setText(notificationList.getDescription());


        }
    }
}