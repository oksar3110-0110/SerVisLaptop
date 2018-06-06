package com.wordpress.oksareinaldi.servislaptop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.wordpress.oksareinaldi.servislaptop.ApiServices.BaseApiServices;
import com.wordpress.oksareinaldi.servislaptop.ApiServices.UtilsApi;
import com.wordpress.oksareinaldi.servislaptop.Session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {
    EditText EtUser, EtPass, Etmail;
    Button BtLogin, BtRegist;
    ProgressDialog progressDialog;
    Context mContext;
    BaseApiServices mApiService;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;

        EtUser = findViewById(R.id.regUser);
        EtPass = findViewById(R.id.regPass);
        BtLogin = findViewById(R.id.CancelRegist);
        BtRegist = findViewById(R.id.RegistBut);
        mApiService = UtilsApi.getAPIService();


        BtRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(mContext, null, "Please Wait ..", true, false);
                prosesRegist();
            }
        });

        BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }

    private void prosesRegist() {
        mApiService.registerRequest(EtUser.getText().toString(), EtPass.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse: BERHASIL");
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("error").equals("false")) {
                            String error = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, Login.class));
                        } else {
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("debug", "onResponse: GAGAL!");
                    progressDialog.dismiss();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}

