package com.ahmed.store_app_pro_1.network.api;

import com.ahmed.store_app_pro_1.ui.models.category.CategoryMainModel;
import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.home.HomeModel;
import com.ahmed.store_app_pro_1.ui.models.product.MainProductModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.ahmed.store_app_pro_1.ui.models.users.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Call<List<ProductModel>> GetProductByCategoryName(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @POST("getSubCategories")
    Call<CategoryMainModel> GetSubCategory(@FieldMap Map<String, Object> map);



    @Headers({ "Accept: application/json", "Accept-Language: ar"})
    @GET("getCategories")
    Call<CategoryMainModel> GetCategory();








}
