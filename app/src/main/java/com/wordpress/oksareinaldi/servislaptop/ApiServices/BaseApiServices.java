package com.wordpress.oksareinaldi.servislaptop.ApiServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by abah on 07/05/18.
 */

public interface BaseApiServices {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("tambahservis.php")
    Call<ResponseBody> tambahReq(
            @Field("nama") String nama,
            @Field("merk") String merk,
            @Field("kerusakan") String kerusakan,
            @Field("terima") String terima,
            @Field("kembali") String kembali

    );

}
