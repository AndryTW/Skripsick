package com.example.andry007.pelanggaransiswa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.andry007.pelanggaransiswa.Object.KategoriPelanggaran;
import com.example.andry007.pelanggaransiswa.Object.Pelanggaran;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Tambah_Poin_Pelanggaran extends AppCompatActivity{
    TextView taun, nis, jng, kels, no_ortu, wktu, totalpoin, cek_poin,ket;
    Spinner jen_pel;
    Button kirim, save,reset;
    EditText saran_guru;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference refPelanggaran = db.getReference("DataPelanggaranSiswa");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refDb = db.getReference("DataSiswa");
    ArrayList<KategoriPelanggaran> list_pelanggaran;
    String skorSelect = "";
    String skorLast = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__poin__pelanggaran);

        taun = findViewById(R.id.th_ajaran);
        nis = findViewById(R.id.nis);
        jng = findViewById(R.id.nama);
        kels = findViewById(R.id.kls);
        no_ortu = findViewById(R.id.no_ortu);
        save = findViewById(R.id.simpan_poin);
        reset = findViewById(R.id.reset_poin);
        wktu = findViewById(R.id.waktu);
        saran_guru = findViewById(R.id.et_saran);
        totalpoin = findViewById(R.id.txt_poin);
        jen_pel = findViewById(R.id.spinner_pelanggaran);

        ket = findViewById(R.id.ket_spinner);
        cek_poin = findViewById(R.id.skor_spinner);

        final String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").
                format(Calendar.getInstance().getTime());
        wktu.setText(date);


        saran_guru.setText("");
        final Intent edit = getIntent();
        taun.setText(edit.getStringExtra("Thn_Ajaran"));
        nis.setText(edit.getStringExtra("NIS"));
        jng.setText(edit.getStringExtra("Nama"));
        kels.setText(edit.getStringExtra("Kelas"));
        no_ortu.setText(edit.getStringExtra("No_Ortu"));

        String siswa_nis = nis.getText().toString();
        String nm = jng.getText().toString();
        String kls = kels.getText().toString();
        String no_ort = no_ortu.getText().toString();
        String tahn = taun.getText().toString();
        totalpoin.setText(skorLast);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference referenceKategori = db.getReference("KategoriPelanggaran");
        jen_pel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Jenis Pelanggaran")){
                    ket.setText("");
                }
                else {
                    String keterangan = jen_pel.getSelectedItem().toString();
                    ket.setText(keterangan);
                    skorSelect = list_pelanggaran.get(position).getSkor();
                    cek_poin.setText(skorSelect.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Query lastQuery = refDb.child(edit.getStringExtra("nis"))
                .child("pelanggaran")
                .orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot addressSnapshot : dataSnapshot.getChildren()) {
                    Pelanggaran plg = addressSnapshot.getValue(Pelanggaran.class);
                    skorLast = plg.getTotal_Poin();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Handle possible errors.
            }
        });

        referenceKategori.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_pelanggaran = new ArrayList<KategoriPelanggaran>();
                list_pelanggaran.clear();

                for(DataSnapshot addressSnapshot : dataSnapshot.getChildren()) {
                    KategoriPelanggaran ktg = addressSnapshot.getValue(KategoriPelanggaran.class);
                    list_pelanggaran.add(ktg);
                }

                list_pelanggaran.add(0, new KategoriPelanggaran("0","Jenis Pelanggaran"));
                ArrayAdapter<KategoriPelanggaran> areasAdapter = new ArrayAdapter<KategoriPelanggaran>(Tambah_Poin_Pelanggaran.this,
                        android.R.layout.simple_spinner_item, list_pelanggaran);

                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jen_pel.setAdapter(areasAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", "Gagal", databaseError.toException());
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = refDb.getKey();

                String jenis_pelanggaran = ket.getText().toString().trim();
                int total_Poin = Integer.parseInt(skorLast) + Integer.parseInt(skorSelect);
                String waktu = wktu.getText().toString().trim();
                String saran = saran_guru.getText().toString().trim();

                Pelanggaran data = new Pelanggaran();
                data.setJenisPelanggaran(jenis_pelanggaran);
                data.setWaktu(waktu);
                data.setTotal_Poin(String.valueOf(total_Poin));
                data.setSaran(saran);
                refDb.child(edit.getStringExtra("nis"))
                        .child("pelanggaran")
                        .push()
                        .setValue(data);

            }
        });

    }
}
