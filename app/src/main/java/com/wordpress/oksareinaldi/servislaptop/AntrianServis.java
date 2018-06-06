package com.wordpress.oksareinaldi.servislaptop;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.oksareinaldi.servislaptop.ApiServices.UtilsApi;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AntrianServis extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<modelKerusakan> data;
    private adapterKerusakan adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.activity_antrian_servis, container, false);
        recyclerView = myFragmentView.findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(myFragmentView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
        return recyclerView;
    }

    private void loadJSON() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UtilsApi.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInter requestInter = retrofit.create(RequestInter.class);
        Call<ResponseKerusakan> call = requestInter.getJSON();
        call.enqueue(new Callback<ResponseKerusakan>() {
            @Override
            public void onResponse(Call<ResponseKerusakan> call, Response<ResponseKerusakan> response) {
                ResponseKerusakan ResponseKerusakan = response.body();
                data = new ArrayList<>(Arrays.asList(ResponseKerusakan.getModelKerusakan()));
                adapter = new adapterKerusakan(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseKerusakan> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });
    }
}
