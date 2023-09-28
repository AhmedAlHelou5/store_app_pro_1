package com.ahmed.store_app_pro_1.network.api;

import static com.ahmed.store_app_pro_1.Constans.REGISTER;

import com.ahmed.store_app_pro_1.ui.models.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/register")
    Call<User> registerUser(@FieldMap Map<String, String> map);
//    Call<User> registerUser(@Field("name") String name, @Field("phone") String phone, @Field("password") String password, @Field("password_confirmation") String password_confirmation);


    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/login")
    Call<User> loginUser(@Field("phone") String phone, @Field("password") String password);
//    Call<User> loginUser(@Body User user);

//    Call<User> registerUser(
//            @Field("name") String name,
//            @Field("phone") String phone,
//            @Field("password") String password,
//            @Field("password_confirmation") String password_confirmation,
//            @Field("fcm") String fcm,
//            @Header("Accept") String accept, @Header("Accept-Language") String accept_language
//    );

}
