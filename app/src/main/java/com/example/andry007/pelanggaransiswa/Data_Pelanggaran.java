package com.example.andry007.pelanggaransiswa;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.DatePickerDialog;

import com.example.andry007.pelanggaransiswa.Adapter.Data_PelanggaranAdapter;
import com.example.andry007.pelanggaransiswa.Object.Pelanggaran;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Data_Pelanggaran extends AppCompatActivity {
     DatePickerDialog datePickerDialog;
     SimpleDateFormat dateFormatter;
     TextView dateresult;
     Button bt_kalender;
     ListView list_pelanggar;
     ArrayList<Pelanggaran> data_pelanggarList;

     FirebaseDatabase db = FirebaseDatabase.getInstance();
     DatabaseReference refDatas = db.getReference("DataPelanggaran");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__pelanggaran);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        data_pelanggarList = new ArrayList<>();

        list_pelanggar = findViewById(R.id.list_datapelanggar);
        dateresult = (TextView) findViewById(R.id.dateresult);
        bt_kalender = (Button) findViewById(R.id.bt_kalender);
        bt_kalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        loadDataPelanggar();
    }

    private void loadDataPelanggar() {
        refDatas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data_pelanggarList.clear();
                for (DataSnapshot unit : dataSnapshot.getChildren()){
                    Pelanggaran v = unit.getValue(Pelanggaran.class);
                    data_pelanggarList.add(v);
                }

                ListAdapter adp = new Data_PelanggaranAdapter(data_pelanggarList,getApplicationContext());
                list_pelanggar.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDateDialog(){
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */
                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                dateresult.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();

    }
}
