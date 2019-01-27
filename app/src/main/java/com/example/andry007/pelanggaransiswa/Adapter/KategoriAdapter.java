package com.example.andry007.pelanggaransiswa.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andry007.pelanggaransiswa.Object.KategoriPelanggaran;
import com.example.andry007.pelanggaransiswa.R;

import java.util.List;

public class KategoriAdapter extends ArrayAdapter<KategoriPelanggaran> implements View.OnClickListener {
    Activity context;
    private List<KategoriPelanggaran> data_kategori;


    public static class ViewHolders {
        TextView _jenis;
        TextView _skor;
    }

    public KategoriAdapter(Activity context, List<KategoriPelanggaran> data_kategori) {
        super(context, R.layout.list_item_templates2, data_kategori);
        this.data_kategori = data_kategori;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data_kategori.size();
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        KategoriPelanggaran kategoriPelanggaran = (KategoriPelanggaran) object;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            KategoriPelanggaran kategoriPelanggaran = getItem (position);
            ViewHolders listViewHolders;
            final View result;

            if (convertView == null){
                listViewHolders = new ViewHolders();
                LayoutInflater inflater = context.getLayoutInflater();
                View listViewItem = inflater.inflate(R.layout.list_item_templates2, null, false);
                listViewHolders._jenis = (TextView) convertView.findViewById(R.id.txt_kategori);
                listViewHolders._skor = (TextView) convertView.findViewById(R.id.txt_skor);

                convertView.setTag(listViewHolders);

                result = convertView;
                convertView.setTag(listViewHolders);
            }
            else {
                listViewHolders = (ViewHolders) convertView.getTag();
                result = convertView;
            }
            listViewHolders._jenis.setText(kategoriPelanggaran.getKategori().toString());
            listViewHolders._skor.setText(kategoriPelanggaran.getSkor().toString());

            return convertView;
    }


}
