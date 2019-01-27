package com.example.andry007.pelanggaransiswa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andry007.pelanggaransiswa.Object.Data_Siswa;
import com.example.andry007.pelanggaransiswa.R;

import java.util.ArrayList;
import java.util.List;

public class Data_SiswaAdapter extends ArrayAdapter<Data_Siswa> implements View.OnClickListener {
    Context mcontext;
    List <Data_Siswa> data_siswaList;

    public static class ViewHolder {
        TextView _th;
        TextView _nis;
        TextView _kelas;
        TextView _nama;
    }

    public Data_SiswaAdapter(ArrayList<Data_Siswa> data, Context context) {
        super(context, R.layout.list_item_templates, data);
        this.data_siswaList = data;
        this.mcontext=context;


    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Data_Siswa data_siswa = (Data_Siswa)object;

    }

    @Override
    public int getCount() {
        return data_siswaList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data_Siswa data_siswa = getItem (position);
        ViewHolder listViewHolder;
        final View result;

        if (convertView == null) {
            listViewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_templates, parent, false);
            listViewHolder._th = (TextView) convertView.findViewById(R.id.id_thn);
            listViewHolder._nis = (TextView) convertView.findViewById(R.id.id_nis);
            listViewHolder._kelas = (TextView) convertView.findViewById(R.id.id_kls);
            listViewHolder._nama = (TextView) convertView.findViewById(R.id.id_nama);
            convertView.setTag(listViewHolder);

            result = convertView;
            convertView.setTag(listViewHolder);
        }

        else {
            listViewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        listViewHolder._th.setText(data_siswa.getThn_Ajaran().toString());
        listViewHolder._nis.setText(data_siswa.getNis().toString());
        listViewHolder._kelas.setText(data_siswa.getKelas().toString());
        listViewHolder._nama.setText(data_siswa.getNama().toString());

        return convertView;
    }

}

