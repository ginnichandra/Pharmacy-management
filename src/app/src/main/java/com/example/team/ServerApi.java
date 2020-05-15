package com.example.team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ServerApi {

//   USER SERVICES---------------------------------------------------------------------------------------

    @POST("authenticate")
    Call<Jwt> authenticate(@Body  Details details);

    @POST("user")
    Call<User> user ();

    // Get all Users
//    @GET("api/users/getUsers")
    @GET("api/users/getUsersByAccess")
    Call<ArrayList<User>> getUsers();

    //Add new User
    @POST("api/users/saveNewUser")
    Call<Void> addUser(@Body User user);

    //Update user
    @POST("api/users/updateUser")
    Call<Void> updateUser(@Body User user);

//    PRODUCTS SERVICES--------------------------------------------------------------------------------------

//    @GET("api/products/getProducts")
//    Call<ArrayList<Products>> getProducts();

    @POST("api/products/addProduct")
    Call<Void> addProduct(@Body Products product);

    @GET("/api/storeproducts/getStoreProductsByLocation")
    Call<ArrayList<StoreProduct>> getProducts(@Query("locationId") int locationId);

    @POST("api/storeproducts/addNewStoreProducts")
    Call <Void> addStoreProduct(@Body StoreProduct storeProduct);



//    CART SERVICES----------------------------------------------------------------------------------------------

    @POST("api/invoice/createInvoice")
    Call<Void> checkout(@Body Invoice invoice);

//    Call<Void> checkout(
//            @Field("totalprice") int totalPrice,
//            @Field("salesList")  ArrayList<Sales> salesList
//            );
}
