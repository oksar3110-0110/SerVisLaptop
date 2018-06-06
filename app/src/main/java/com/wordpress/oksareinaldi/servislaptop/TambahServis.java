package com.wordpress.oksareinaldi.servislaptop;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wordpress.oksareinaldi.servislaptop.ApiServices.BaseApiServices;
import com.wordpress.oksareinaldi.servislaptop.ApiServices.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahServis extends AppCompatActivity implements View.OnClickListener{
    Button tanggal, jam, butTerJam, ButTerTanggal;
    Button send;
    EditText Nama, etTanggal, etJam, terTanggal, terJam;
    Spinner spin;
    AutoCompleteTextView textView,textView1;
    int mYear, mMonth, mDay, mHour, mMinute;
    ProgressDialog progressDialog;
    Context mContext;
    BaseApiServices mApiService;

    String[] kota = {
            "Cirebon",
            "Jakarta",
            "Bandung",
            "Banten",
            "Tanggerang",
            "Garut",
            "Tasik",
            "Aceh",
            "Ciamis",
            "Makassar",
            "Yogyakarta",
            "Bekasi",
            "Subang",
            "Karawang",
            "Cikarang",
            "Kuningan",
            "Sumedang",
            "Majalengka"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_servis);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        tanggal = findViewById(R.id.putTanggal);
        jam = findViewById(R.id.putJam);
        Nama = findViewById(R.id.Etnama);
        etTanggal = findViewById(R.id.EtTanggal);
        etJam = findViewById(R.id.EtJam);
        send = findViewById(R.id.Btsend);
        terJam = findViewById(R.id.EtJamTer);
        terTanggal = findViewById(R.id.EttglTer);
        butTerJam = findViewById(R.id.putJamTer);
        ButTerTanggal = findViewById(R.id.putTanggalTer);
        textView = (AutoCompleteTextView) findViewById(R.id.Kota);
        textView1 =  (AutoCompleteTextView) findViewById(R.id.Brand);

        final String[] kerusakan;
        final String[] merk;

        //deklarasi onclick
        tanggal.setOnClickListener(this);
        jam.setOnClickListener(this);
        butTerJam.setOnClickListener(this);
        ButTerTanggal.setOnClickListener(this);
        send.setOnClickListener(this);

        //Kota
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,kota);
        textView.setThreshold(1);
        textView.setAdapter(adapter);

        //Brand
        merk = getResources().getStringArray(R.array.merk);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,merk);
        textView1.setThreshold(1);
        textView1.setAdapter(adapter2);

        //spinner
        kerusakan = getResources().getStringArray(R.array.kerusakan);
        spin = findViewById(R.id.spinRusak);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kerusakan);
        spin.setAdapter(adapter1);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = adapterView.getSelectedItemPosition();
               /* Toast.makeText(getBaseContext(), "Kerusakan" + kerusakan[index], Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    @Override
    public void onClick(View v) {

        if(v == tanggal){

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    etTanggal.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                }
            }, mYear,mMonth,mDay);
            datePickerDialog.show();
        }

        if (v == jam){
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    etJam.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }

        // terima tanngal
        if(v == ButTerTanggal){

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    terTanggal.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                }
            }, mYear,mMonth,mDay);
            datePickerDialog.show();
        }

        if (v == butTerJam){
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    terJam.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }

        if ( v == send){
            progressDialog = ProgressDialog.show(mContext, null, "Please Wait ..", true, false);
            String nama = Nama.getText().toString();
            String jam = etJam.getText().toString();
            String tanggal = etTanggal.getText().toString();
            String tanggalter = terTanggal.getText().toString();
            String jamter = terJam.getText().toString();
            String kerusakan = spin.getSelectedItem().toString();
            String brand = textView1.getText().toString();
            String kota = textView.getText().toString();
            if(Nama.getText().toString().isEmpty() || etJam.getText().toString().isEmpty() || etTanggal.getText().toString().isEmpty() ||
                    terTanggal.getText().toString().isEmpty() || terJam.getText().toString().isEmpty() || spin.getSelectedItem().toString().isEmpty() ||
                    textView.getText().toString().isEmpty() || textView1.getText().toString().isEmpty()){
                Toast.makeText(getBaseContext(), "Masih ada Field Kosong!!", Toast.LENGTH_SHORT).show();
            } else {


                mApiService.tambahReq(nama,brand,kerusakan,tanggalter,tanggal).enqueue(new Callback<ResponseBody>() {
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
                                    startActivity(new Intent(mContext, MainActivity.class));
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TambahServis.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
