package com.wordpress.oksareinaldi.servislaptop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abah on 04/06/18.
 */

public class adapterKerusakan extends RecyclerView.Adapter<adapterKerusakan.ViewHolder> {
    private Context context;
    private ArrayList<modelKerusakan> modelKerusakan;


    public adapterKerusakan(ArrayList<modelKerusakan> modelKerusakan) {
        this.modelKerusakan = modelKerusakan;
    }


    @Override
    public adapterKerusakan.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_antri, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final adapterKerusakan.ViewHolder viewHolder, int i) {


        viewHolder.tv_nama.setText(modelKerusakan.get(i).getNama());
        viewHolder.tv_merk.setText(modelKerusakan.get(i).getMerk());
        viewHolder.tv_rusak.setText(modelKerusakan.get(i).getKerusakan());
        viewHolder.tv_terima.setText(modelKerusakan.get(i).getTerima());
        viewHolder.tv_kembali.setText(modelKerusakan.get(i).getKembali());
    }

    @Override
    public int getItemCount() {
        return modelKerusakan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nama, tv_rusak, tv_merk, tv_terima, tv_kembali;
        public ViewHolder(View view) {
            super(view);
            context = view.getContext();

            tv_nama = view.findViewById(R.id.nama);
            tv_rusak = view.findViewById(R.id.kerusakan);
            tv_merk = view.findViewById(R.id.merk);
            tv_terima = view.findViewById(R.id.tTer);
            tv_kembali = view.findViewById(R.id.tKem);


        }
    }

}
