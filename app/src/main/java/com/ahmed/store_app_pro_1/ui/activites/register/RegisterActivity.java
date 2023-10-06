package com.ahmed.store_app_pro_1.ui.activites.register;

import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.app.PendingIntent.FLAG_MUTABLE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmed.store_app_pro_1.Constans;
import com.ahmed.store_app_pro_1.R;
import com.ahmed.store_app_pro_1.databinding.ActivityRegisterBinding;
import com.ahmed.store_app_pro_1.network.api.ApiInterface;
import com.ahmed.store_app_pro_1.network.api.RetrofitClientInstance;
import com.ahmed.store_app_pro_1.ui.activites.login.LoginActivity;
import com.ahmed.store_app_pro_1.ui.activites.privacy.PrivacyActivity;
import com.ahmed.store_app_pro_1.ui.activites.splash.two.OnBoardingTwoActivity;
import com.ahmed.store_app_pro_1.ui.activites.verfiy.VerfiyActivity;
import com.ahmed.store_app_pro_1.ui.models.users.User;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
//    private static final String CHANNEL_ID ="my_channel_01"; ;
    ActivityRegisterBinding binding;
    private static final int NOTIFICATION_PERMISSION_CODE = 101;
    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 1;
    boolean isProgressVisible = false;

    private NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        HttpUrlConnection conn = (HttpURLConnection) BASE_URL.openConnection();
//        conn.setConnectTimeout(7000);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

        btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

//        if (binding.idPBLoading.getVisibility() == View.VISIBLE) {
//            btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));
//
//        }

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {


                        if (result.getResultCode() == 1 || result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Log.d("TAG", "onActivityResult: " + intent);
                            if (intent != null) {
                                binding.checkBoxAccept.setActivated(true);
//                                binding.checkBoxAccept.isChecked();
                                binding.checkBoxAccept.setChecked(true);
                                binding.tvCheckedTerms.setTextColor(getResources().getColor(R.color.primary_color));
                                Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));


                            }


                        }
                    }
                });


        binding.btnNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidFeilds()) {
                    isProgressVisible = true;
                    btnNewRegisterIsActive(false, getResources().getDrawable(R.drawable.on_boarding_btn_sign_up_un_check));

                    binding.idPBLoading.setVisibility(View.VISIBLE);

//                    checkPermission(Manifest.permission.ACCESS_NOTIFICATION_POLICY, NOTIFICATION_PERMISSION_CODE);



                    ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", binding.edTextFullName.getText().toString());
                    map.put("phone", binding.edTextPhone.getText().toString());
                    map.put("password", binding.edTextPassword1.getText().toString());
                    map.put("password_confirmation", binding.edTextPassword1.getText().toString());

                    Log.d("TAG", "onClick: " + map);
                    Call<User> call = apiInterface.registerUser(map);


                    call.enqueue((new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(RegisterActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();

                            if (response.isSuccessful() && response.body() != null) {
                                Log.e("TAG", "onResponse:getFcm " + response.body().getData());
                                Log.e("TAG", "onResponse:getFcm " + response.body().getData().getPhone());


                                Log.e("TAG", "onResponse: " + response.body());

                                Intent intent = new Intent(RegisterActivity.this, VerfiyActivity.class);

                                // put in shared preferences value
                            SharedPreferences.Editor editor = getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE).edit();
                            editor.putString("phone", response.body().getData().getPhone() );
                            editor.putString("code", response.body().getMessage() );
                            editor.apply();
                                isProgressVisible = false;

                                binding.idPBLoading.setVisibility(View.GONE);

                                intent.putExtra("phone", response.body().getData().getPhone());
                                intent.putExtra("code", response.body().getMessage());
                                startActivity(intent);


                            } else {
                                Log.e("TAG", "onResponse: null " + response.body());
//                            Log.e("TAG", "onResponse: null " + response.body().getMessage());
                                showSnackBar("حدث خطأ اثناء اتصالك بالانترنت");
                                btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));


                            }


                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("TAG", "onFailure: " + t.getMessage());
                            showSnackBar("تأكد من اتصالك بالانترنت");
                            btnNewRegisterIsActive(true, getResources().getDrawable(R.drawable.on_boarding_btn_next));


                            isProgressVisible = false;

                            binding.idPBLoading.setVisibility(View.GONE);
                        }
                    }));

                }

            }
        });


        binding.iconBackOnBoarding.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, OnBoardingTwoActivity.class);
            startActivity(intent);
            finish();
        });


        binding.tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
//            finish();
        });


        binding.linerCheckBoxAccept.setOnClickListener(view -> {
                    activityResultLauncher.launch(new Intent(RegisterActivity.this, PrivacyActivity.class));

                }
        );


    }



    private void showSnackBar(String SMSCode) {

        Snackbar snack = Snackbar.make(binding.getRoot(), SMSCode, 10000);
        View view1 = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
        params.gravity = Gravity.TOP;
        view1.setLayoutParams(params);
        snack.show();
    }


    public void showNotification(View view,String title){
        Intent intent = new Intent(this, RegisterActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, FLAG_IMMUTABLE);

        Notification.Builder builder = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_message_24)
                    .setContentTitle("Tc-Bike App")
                    .setContentText(title)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        }

        Notification notification;
        notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID,notification);

    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            CharSequence channelName = "My Channel";
            String channelDiscription = "My Channel Discreption";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,channelName,importance);
            channel.setDescription(channelDiscription);

            notificationManager.createNotificationChannel(channel);
        }
    }




    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(RegisterActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(RegisterActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(RegisterActivity.this, "NOTIFICATION Permission Granted", Toast.LENGTH_SHORT) .show();
            }
            else {
                Toast.makeText(RegisterActivity.this, "NOTIFICATION Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }

    }





    public void sendSMS(String number, String message) {

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null, message, null, null);
    }
    ///////////////////////////////////////////
    public static boolean
    isValidPassword(String password) {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
//      if (password == null) {
//          return false;
//      }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public boolean isValidFeilds() {
        ArrayList<Boolean> isValid = new ArrayList<>();


        if (!binding.edTextPhone.getText().toString().startsWith("972")) {
            binding.edTextPhone.setError(getString(R.string.msg_phone_number_start_with));
            isValid.add(false);
            Log.e("TAG", "!phoneNumber.startsWith(\"972\": ");

        }

        if (isValidPassword(binding.edTextPassword1.getText().toString()) == false) {

            binding.edTextPassword1.setError(getString(R.string.msg_week_passwrod));
            Log.e("TAG", "password.length() <= 6: ");

            isValid.add(false);
        }

        if (TextUtils.isEmpty(binding.edTextFullName.getText().toString())) {
            binding.edTextFullName.setError(getString(R.string.required));
            Log.e("TAG", "TextUtils.isEmpty(fullName): ");

            isValid.add(false);
        }

        if (TextUtils.isEmpty(binding.edTextPhone.getText().toString())) {
            binding.edTextPhone.setError(getString(R.string.required));
            Log.e("TAG", "TextUtils.isEmpty(phoneNumber): ");

            isValid.add(false);

        }
        if (TextUtils.isEmpty(binding.edTextPassword1.getText().toString())) {
            Log.e("TAG", "TextUtils.isEmpty(password): ");

            binding.edTextPassword1.setError(getString(R.string.required));
            isValid.add(false);

        }


        if (isValid.contains(false)) {
            return false;
        }
        return true;


    }


    public void ShowHidePass(View view) {

        if (view.getId() == R.id.show_pass_btn) {

            if (binding.edTextPassword1.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
                binding.edTextPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Hide Password
                binding.edTextPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }




    void btnNewRegisterIsActive(boolean isEnable, Drawable drawable) {
        binding.btnNewRegister.setEnabled(isEnable);
        binding.btnNewRegister.setClickable(isEnable);
        binding.btnNewRegister.setBackground(drawable);

    }


//    private void sendDataToServer(User user) {
//        {
//
//
//
////            apiInterface = retrofit.create(ApiInterface.class);
//        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
////            User data = new User(user.getData(), user.getData().getPhone(), user.getData().getPassword());
//
//            Call<User> call = apiInterface.registerUser(user);
//
//
//            Log.e("TAG", "call " + apiInterface);
//
//
//            call.enqueue((new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//
//                        Toast.makeText(RegisterActivity.this, "Success body" + response.body(), Toast.LENGTH_LONG).show();
//                          Log.e("TAG", "onResponse:getFcm "+response.body().getData());
////                          Log.e("TAG", "onResponse:getName "+response.body().getName() );
////                          Log.e("TAG", "onResponse:getPhone "+response.body().getPhone() );
////                          Log.e("TAG", "onResponse:getPassword "+response.body().getPassword() );
////                          Log.e("TAG", "onResponse:getId "+response.body().getId() );
//
//                        Log.e("TAG", "onResponse: " + response.body());
////                        Log.e("TAG", "onResponse:getName " + response.body().getData().getName());
////                        Log.e("TAG", "onResponse:getPhone " + response.body().getData().getPhone());
////                        Log.e("TAG", "onResponse:getFcm " + response.body().getData().isFcm());
////                        Log.e("TAG", "onResponse:getIcon_url " + response.body().getData().getIconUrl());
////                        Log.e("TAG", "onResponse:getId " + response.body().getData().getId());
//
////                Toast.makeText(RegisterActivity.this, "Success getName"+response.body().getName(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(RegisterActivity.this, "Success getPassword"+response.body().getPassword(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(RegisterActivity.this, "Success getPhone"+response.body().getPhone(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(RegisterActivity.this, "Success getFcm"+response.body().getFcm(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(RegisterActivity.this, "Success getIcon_url"+response.body().getIcon_url(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(RegisterActivity.this, "Success getId"+response.body().getId(), Toast.LENGTH_SHORT).show();
//
//
////                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
////                startActivity(intent);
////                finish();
//
//
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Toast.makeText(RegisterActivity.this, "onFailure body" + t.getMessage(), Toast.LENGTH_LONG).show();
//                    Log.e("TAG", "onFailure: " + t.getMessage());
//
//
//                }
//            }));
//
//        }
//
//
//    }
}