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
import android.widget.ProgressBar;
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

public class Login extends AppCompatActivity {
    Button login, regist;
    EditText EtUser, EtPass;
    Button BtLogin, BtRegist;
    ProgressDialog progressDialog;
    Context mContext;
    TextView Forget;
    BaseApiServices mApiService;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        initComponents();


        sessionManager = new SessionManager(this);

        if (sessionManager.getSessionStatus()){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }



    }

    private void initComponents() {
        login = findViewById(R.id.loginButlog);
        regist = findViewById(R.id.loginRegistlog);
        EtUser = findViewById(R.id.loginUser);
        EtPass = findViewById(R.id.loginPass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(mContext, null, "Wait..");
                prosesLogin();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });
    }

    public void prosesLogin(){
        mApiService.loginRequest(EtUser.getText().toString(), EtPass.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    progressDismiss();
                    try{
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")){
                            Toast.makeText(mContext, "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                            sessionManager.saveBool(sessionManager.SESSION_STATUS, true);
                            String username = jsonResult.getJSONObject("data").getString("username");
                            sessionManager.saveStr(sessionManager.SESSION_USERNAME, username);
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        } else{
                            String error_msg = jsonResult.getString("error_msg");
                            Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } else {
                    progressDismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }
    private void progressDismiss() {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }


}
