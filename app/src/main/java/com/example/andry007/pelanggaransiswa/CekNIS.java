package com.example.andry007.pelanggaransiswa;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andry007.pelanggaransiswa.Login.Login_Orangtua;
import com.example.andry007.pelanggaransiswa.Object.Data_Siswa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CekNIS extends AppCompatActivity {
    Button btn_cekNIS;
    EditText nis_siswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_nis);

        nis_siswa = findViewById(R.id.cek_nis);
        btn_cekNIS = findViewById(R.id.btn_cek_nis);
        cekdataNIS();
    }

    private void cekdataNIS() {
        btn_cekNIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                final DatabaseReference refData = db.getReference("DataSiswa");
                Data_Siswa dt = new Data_Siswa();
                final String value = dt.getNis();
                refData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(value)) {
                                Intent intent = new Intent(CekNIS.this, Pemberitahuan_Orangtua.class);
                                Toast.makeText(CekNIS.this, "Data Tersebut Tersedia",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CekNIS.this, "Data Tidak Tersedia, Masukan ulang NIS",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout_ortu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(CekNIS.this, MainActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
