package com.example.andry007.pelanggaransiswa.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.andry007.pelanggaransiswa.Menu_Wali;
import com.example.andry007.pelanggaransiswa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login_Wali extends AppCompatActivity {
    ProgressBar progressBar_Login;
    EditText UserEmail,UserPass;
    Button UserLogin;
    private boolean islog;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__wali);
        progressBar_Login = findViewById(R.id.progressBar_Login);
        UserEmail = findViewById(R.id.etUserEmail);
        UserPass = findViewById(R.id.etUserPass);
        UserLogin = findViewById(R.id.btn_user_login);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), Menu_Wali.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSign();
                islog = islogin();
            }

            private void startSign() {
                String wali = UserEmail.getText().toString();
                String pass = UserPass.getText().toString();

                if (TextUtils.isEmpty(wali)) {
                    Toast.makeText(getApplicationContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }



                else {
                    firebaseAuth.signInWithEmailAndPassword(wali, pass).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            progressBar_Login.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Login_Wali.this, Menu_Wali.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(Login_Wali.this, "Data Tidak Sesuai", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                    });
                }
                progressBar_Login.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean islogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }
}
