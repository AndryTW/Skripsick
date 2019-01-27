package com.example.andry007.pelanggaransiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andry007.pelanggaransiswa.Object.Data_Siswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Detil_Siswa extends AppCompatActivity {
    EditText ET_nm, ET_nis, ET_no_ortu, ET_nm_ortu, ET_almt,ET_thn, ET_kelas, ET_jk;
    Button update;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference refData = db.getReference("DataSiswa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__detil__siswa);

        ET_nm = findViewById(R.id.et_nama);
        ET_nis = findViewById(R.id.et_NIS);
        ET_no_ortu = findViewById(R.id.et_no_ortu);
        ET_nm_ortu = findViewById(R.id.et_nama_ortu);
        ET_almt = findViewById(R.id.et_alamat);
        ET_kelas = findViewById(R.id.et_kelas);
        ET_thn = findViewById(R.id.et_thn_ajaran);
        ET_jk = findViewById(R.id.et_jk);
        update = findViewById(R.id.btn_updt);


        final Intent edit = getIntent();
        ET_nm.setText(edit.getStringExtra("nama"));
        ET_nis.setText(edit.getStringExtra("nis"));
        ET_nm_ortu.setText(edit.getStringExtra("nama_Ortu"));
        ET_no_ortu.setText(edit.getStringExtra("no_Ortu"));
        ET_almt.setText(edit.getStringExtra("alamat"));
        ET_kelas.setText(edit.getStringExtra("kelas"));
        ET_jk.setText(edit.getStringExtra("jk"));
        ET_thn.setText(edit.getStringExtra("thn_Ajaran"));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jng = ET_nm.getText().toString();
                String no_in = ET_nis.getText().toString();
                String wongtuo = ET_nm_ortu.getText().toString();
                String no_ort = ET_no_ortu.getText().toString();
                String omah = ET_almt.getText().toString();
                String kels = ET_kelas.getText().toString();
                String jns_kel = ET_jk.getText().toString();
                String th = ET_thn.getText().toString();

                Data_Siswa up = new Data_Siswa();
                up.setNama(jng);
                up.setNis(no_in);
                up.setNama_Ortu(wongtuo);
                up.setNo_Ortu(no_ort);
                up.setKelas(kels);
                up.setJk(jns_kel);
                up.setThn_Ajaran(th);
                up.setAlamat(omah);
                refData.child(no_in).setValue(up);

                Intent intent = new Intent(Edit_Detil_Siswa.this, Menu_Wali.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                            }
        });
    }

}