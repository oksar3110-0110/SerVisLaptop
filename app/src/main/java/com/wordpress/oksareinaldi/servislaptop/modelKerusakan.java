package com.wordpress.oksareinaldi.servislaptop;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abah on 04/06/18.
 */

public class modelKerusakan {
    @SerializedName("nama") private String nama;
    @SerializedName("kerusakan") private String kerusakan;
    @SerializedName("terima") private String terima;
    @SerializedName("kembali") private String kembali;
    @SerializedName("merk") private String merk;

    public String getMerk() {
        return merk;
    }

    public String getNama() {
        return nama;
    }

    public String getKerusakan() {
        return kerusakan;
    }

    public String getTerima() {
        return terima;
    }

    public String getKembali() {
        return kembali;
    }
}
