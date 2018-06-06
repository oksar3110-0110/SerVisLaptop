package com.wordpress.oksareinaldi.servislaptop;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abah on 04/06/18.
 */

public interface RequestInter {
    @GET("antrian.php")
    Call<ResponseKerusakan> getJSON();
}
