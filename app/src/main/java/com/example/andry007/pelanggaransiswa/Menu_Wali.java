package com.example.andry007.pelanggaransiswa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.andry007.pelanggaransiswa.Adapter.Data_SiswaAdapter;
import com.example.andry007.pelanggaransiswa.Login.Login_Wali;
import com.example.andry007.pelanggaransiswa.Object.Data_Siswa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu_Wali extends AppCompatActivity {
    ArrayList<HashMap<String, String>> contactList;
    ListView listView;
    Button bt_dt_pelanggaran;
    ArrayList<Data_Siswa> data_siswaList;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference refData = db.getReference("DataSiswa");

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__wali);

        data_siswaList = new ArrayList<>();
        listView = findViewById(R.id.list_datasiswa);
        loadDataSiswa();
        bt_dt_pelanggaran = findViewById(R.id.data_pelanggaran);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user == null){
                    startActivity(new Intent(Menu_Wali.this, Login_Wali.class));
                    finish();
                }

            }
        };

        bt_dt_pelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Wali.this, Data_Pelanggaran.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        bt_dt_pelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Wali.this, Data_Pelanggaran.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadDataSiswa(){
        refData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                if(data_siswaList.size() > 0)
                    data_siswaList.clear();
                for (DataSnapshot unit : dataSnapshot.getChildren()){
                    Data_Siswa vl = unit.getValue(Data_Siswa.class);
                    data_siswaList.add(vl);
                }

                ListAdapter adapter = new Data_SiswaAdapter(data_siswaList,getApplicationContext());
                listView.setAdapter(adapter);
                ((Data_SiswaAdapter) adapter).notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data_Siswa selectedItem = data_siswaList.get(position);
                if (selectedItem != null);
                Intent intent = new Intent(getApplicationContext(),Detail_Siswa.class);
                intent.putExtra("thn_Ajaran",selectedItem.getThn_Ajaran());
                intent.putExtra("kelas",selectedItem.getKelas());
                intent.putExtra("nis",selectedItem.getNis());
                intent.putExtra("nama",selectedItem.getNama());
                intent.putExtra("alamat",selectedItem.getAlamat());
                intent.putExtra("jk",selectedItem.getJk());
                intent.putExtra("nama_Ortu",selectedItem.getNama_Ortu());
                intent.putExtra("no_Ortu",selectedItem.getNo_Ortu());
                startActivity(intent);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.action_tambah:
                startActivity(new Intent(this, InputKategori.class));
                break;
        }
        return true;
    }
}