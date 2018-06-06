package com.wordpress.oksareinaldi.servislaptop.ApiServices;

/**
 * Created by abah on 07/05/18.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.43.112/API_SERVIS/";
    /* public static final String BASE_URL_API = "http://192.168.0.129/altis/API_COBA/";*/
    /*public static final String BASE_URL_API = "http://128.110.0.244/altis/API_COBA/";*/
    // Mendeklarasikan Interface BaseApiService
    public static BaseApiServices getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiServices.class);
    }
}
