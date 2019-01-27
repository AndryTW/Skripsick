package com.example.andry007.pelanggaransiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andry007.pelanggaransiswa.Login.Login_Orangtua;
import com.example.andry007.pelanggaransiswa.Login.Login_Wali;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnwali = (Button) findViewById(R.id.wali);
        btnwali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login_Wali.class);
                startActivity(i);
            }
        });
        Button btnortu = (Button) findViewById(R.id.ortu);
        btnortu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login_Orangtua.class);
                startActivity(i);
            }
        });
    }
}
