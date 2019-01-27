package com.example.andry007.pelanggaransiswa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.andry007.pelanggaransiswa.Object.Data_Siswa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Detail_Siswa extends AppCompatActivity {
    private ArrayList<String> lst = new ArrayList<>();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference refData = db.getReference("DataSiswa");

    TextView th, kls, nis, nm, almt, jk, nm_ortu, no_ortu, poinplg;
    Button bt_edit, bt_poin;
    private List<Data_Siswa> data_siswaList;
    String id = "";

//    public static Intent getActIntent(Activity activity) {
//        return new Intent(activity, Detail_Siswa.class);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__siswa);

        bt_poin = findViewById(R.id.btn_poin);
        bt_edit = findViewById(R.id.btn_edit);
        th = findViewById(R.id.get_thn);
        kls = findViewById(R.id.get_kls);
        nis = findViewById(R.id.get_nis);
        nm = findViewById(R.id.get_nama);
        jk = findViewById(R.id.get_jk);
        almt = findViewById(R.id.get_almt);
        nm_ortu = findViewById(R.id.get_nm_ortu);
        no_ortu = findViewById(R.id.get_no_ortu);
        // poinplg = findViewById(R.id.get_poin_plgrn);

        final Intent detailsIntent = getIntent();
        nm.setText(detailsIntent.getStringExtra("nama"));
        th.setText(detailsIntent.getStringExtra("thn_Ajaran"));
        kls.setText(detailsIntent.getStringExtra("kelas"));
        nis.setText(detailsIntent.getStringExtra("nis"));
        jk.setText(detailsIntent.getStringExtra("jk"));
        almt.setText(detailsIntent.getStringExtra("alamat"));
        nm_ortu.setText(detailsIntent.getStringExtra("nama_Ortu"));
        no_ortu.setText(detailsIntent.getStringExtra("no_Ortu"));

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_Siswa.this, Edit_Detil_Siswa.class);
                intent.putExtra("nama",detailsIntent.getStringExtra("nama"));
                intent.putExtra("thn_Ajaran",detailsIntent.getStringExtra("thn_Ajaran"));
                intent.putExtra("kelas",detailsIntent.getStringExtra("kelas"));
                intent.putExtra("nis",detailsIntent.getStringExtra("nis"));
                intent.putExtra("jk",detailsIntent.getStringExtra("jk"));
                intent.putExtra("alamat",detailsIntent.getStringExtra("alamat"));
                intent.putExtra("nama_Ortu",detailsIntent.getStringExtra("nama_Ortu"));
                intent.putExtra("no_Ortu",detailsIntent.getStringExtra("no_Ortu"));
                startActivity(intent);
                finish();

                    }
        });

        bt_poin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Intent intent = new Intent(Detail_Siswa.this, Tambah_Poin_Pelanggaran.class);
                        String jeneng = nm.getText().toString();
                        String taun = th.getText().toString();
                        String klas = kls.getText().toString();
                        String nomerinduk = nis.getText().toString();
                        String nohp = no_ortu.getText().toString();

                        intent.putExtra("nama",jeneng);
                        intent.putExtra("thn_Ajaran",taun);
                        intent.putExtra("kelas",klas);
                        intent.putExtra("nis",nomerinduk);
                        intent.putExtra("no_Ortu",nohp);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }



}

