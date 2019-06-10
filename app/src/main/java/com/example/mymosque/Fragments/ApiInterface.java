package com.example.mymosque.Fragments;

import com.example.mymosque.Models.Feedback;
import com.example.mymosque.Models.MasjidArrayList;
import com.example.mymosque.Models.UserId;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    //Create User Function Call
    @FormUrlEncoded
    @POST("createuser")
    Call<UserId> GetUserID(@Field("emi") String emi);

    //SetFavouriteFunction call
    @FormUrlEncoded
    @POST("getprimarymosque")
    Call getPrimary(@Query("u_id") int u_id);

    @FormUrlEncoded
    @POST("feedback/{u_id}")
            Call<Feedback>  feedback(@Path("u_id") int id ,
                                     @Field("msg")String message,
                                     @Field("contact")String contact,
                                     @Field("name")String name
    );


    @GET("getMosquesList/{u_id}")
    Call<MasjidArrayList> getInfo(@Path("u_id") int id,
                                  @Query("page") int page);
}
