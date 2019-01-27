package com.example.andry007.pelanggaransiswa;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andry007.pelanggaransiswa.Adapter.KategoriAdapter;
import com.example.andry007.pelanggaransiswa.Object.KategoriPelanggaran;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InputKategori extends AppCompatActivity {
    ListView listView;
    Button bt_save;
    EditText ktg, sekor;
    ArrayList<KategoriPelanggaran> datakategori;
    DatabaseReference dbReference = FirebaseDatabase.getInstance()
            .getReference("KategoriPelanggaran");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kategori);


        datakategori = new ArrayList<>();
        listView = findViewById(R.id.list_kategori);
        bt_save = findViewById(R.id.save_kategori);
        ktg = findViewById(R.id.et_kategori);
        sekor = findViewById(R.id.et_skor);

        loadKategori();
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKategori();
            }
        });
    }

    private void addKategori() {
        String kategori = ktg.getText().toString().trim();
        String skor = sekor.getText().toString().trim();
            if (!TextUtils.isEmpty(kategori)) {
                String id = dbReference.push().getKey();
                KategoriPelanggaran kategoris = new KategoriPelanggaran(id, kategori, skor);
                dbReference.child(id).setValue(kategoris);
                ktg.setText("");
                sekor.setText("");
                Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(kategori)){
                Toast.makeText(this, "Masukan Kategori", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(this, "Masukan pasword", Toast.LENGTH_SHORT).show();
            }

    }



    private void loadKategori() {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot unit : dataSnapshot.getChildren()){
                   // KategoriPelanggaran v = unit.getValue(KategoriPelanggaran.class);
                 //   datakategori.add(v);
                }

                KategoriAdapter adapter = new KategoriAdapter(InputKategori.this, datakategori);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
