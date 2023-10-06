package com.ahmed.store_app_pro_1.network.api;


import com.ahmed.store_app_pro_1.ui.models.about.AboutAllModel;
import com.ahmed.store_app_pro_1.ui.models.about.ContactUsModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryMainModel;
import com.ahmed.store_app_pro_1.ui.models.faqs.MainFaqsModel;
import com.ahmed.store_app_pro_1.ui.models.favorite.AddProductToFavoriteModel;
import com.ahmed.store_app_pro_1.ui.models.favorite.GetProductFavoriteModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.product.MainProductModel;
import com.ahmed.store_app_pro_1.ui.models.product.SearchProductModel;
import com.ahmed.store_app_pro_1.ui.models.users.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/register")
    Call<User> registerUser(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/login")
    Call<User> loginUser(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/resendCode")
    Call<User> resendCodeOTP(@Field("phone") String phone);

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/verify")
    Call<User> verifyCodeAndLogin(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("user/passwordReset")
    Call<User> forgetPassword(@FieldMap Map<String, Object> map);


//    @FormUrlEncoded
//    @Headers({ "Content-Type: application/json",})
//    @POST("sms/2/text/advanced")
//    Call<Object> SendSms(@Field("body") String body);





    //    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @GET("home")
    Call<HomeModel> GetHomeData();

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("products/getProductsByCategory")
    Call<MainProductModel> GetProductByCategoryId(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("products/getProductsByCategory")
    Call<MainProductModel> GetProductByCategoryName(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("getSubCategories")
    Call<CategoryMainModel> GetSubCategory(@FieldMap Map<String, Object> map);


     @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("products/getProductsById")
    Call<SearchProductModel> GetProductById(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("products/addProductToFavorite")
    Call<AddProductToFavoriteModel> AddProductToFavorite(@FieldMap Map<String, Object> map,@Header("Authorization") String token);



    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @GET("products/getProductsFavorite")
    Call<GetProductFavoriteModel> GetProductFromFavorite(@Header("Authorization") String token);




    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("contactUs")
    Call<ContactUsModel> ContactUs(@FieldMap Map<String, Object> map);


    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @GET("getCategories")
    Call<CategoryMainModel> GetCategory();

    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @GET("about")
    Call<AboutAllModel> GetAbout();

    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @GET("getFaqs")
    Call<MainFaqsModel> GetFaqs();




}
