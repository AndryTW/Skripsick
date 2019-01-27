package com.example.andry007.pelanggaransiswa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andry007.pelanggaransiswa.Object.Pelanggaran;
import com.example.andry007.pelanggaransiswa.R;

import java.util.ArrayList;
import java.util.List;

public class Data_PelanggaranAdapter extends ArrayAdapter<Pelanggaran> {
    Context konteks;
    List<Pelanggaran> data_pelanggarList;


    public static class Holder {
        TextView tx_jp;
        TextView tx_totpoin;
        TextView tx_wektu;
    }

    public Data_PelanggaranAdapter(ArrayList<Pelanggaran> data_pelanggarList, Context context) {
        super(context, R.layout.list_item_templates3, data_pelanggarList);
        this.data_pelanggarList = data_pelanggarList;
        this.konteks=context;
    }

    @Override
    public int getCount() {
        return data_pelanggarList.size();
    }


    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        Pelanggaran data_pelanggaran = getItem (position);
        Holder listHolder;
        final View result;

        if (convert == null) {
            listHolder = new Holder();
            LayoutInflater inflate = LayoutInflater.from(getContext());
            convert = inflate.inflate(R.layout.list_item_templates3, parent, false);
            listHolder.tx_jp = (TextView) convert.findViewById(R.id.txt_pelanggaran);
            listHolder.tx_totpoin = (TextView) convert.findViewById(R.id.txt_total_poin);
            listHolder.tx_wektu = (TextView) convert.findViewById(R.id.txt_time);
            convert.setTag(listHolder);

            result = convert;
            convert.setTag(listHolder);
        }

        else {
            listHolder = (Data_PelanggaranAdapter.Holder) convert.getTag();
            result = convert;
        }

        return convert;
    }
}
