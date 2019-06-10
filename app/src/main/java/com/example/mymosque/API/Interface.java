package com.example.mymosque.API;

import com.example.mymosque.Models.Feedback;
import com.example.mymosque.Models.MasjidArrayList;
import com.example.mymosque.Models.PrimraryMosqueData;
import com.example.mymosque.Models.UserId;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Interface
{
    @FormUrlEncoded
    @POST("createuser")
    @NotNull
    Call GetUserID(@Field("emi") @NotNull String emi, Call<UserId> userIdCall);

    @FormUrlEncoded
    @POST("getprimrarymosque")
    @NotNull
    Call getprimrary(@Field("userid") int userid, Call<PrimraryMosqueData> userIdCall);

    @FormUrlEncoded
    @POST("feedback/{u_id}")
    @NotNull
    Call feedback(@Path("u_id") int id,
                  @Field("msg") String message,
                  @NotNull String contact, @Field("contact")
                  @NotNull String name, @Field("name") @NotNull String var4, Call<Feedback> userIdCall);

    @GET("getMosquesList/{u_id}")
    @NotNull
    Call getInfo(@Path("u_id") int id,
                 @Query("page") int page, Call<MasjidArrayList> userIdCall);
}
