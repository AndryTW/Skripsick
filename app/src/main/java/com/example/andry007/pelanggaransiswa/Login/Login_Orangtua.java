package com.example.andry007.pelanggaransiswa.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andry007.pelanggaransiswa.CekNIS;
import com.example.andry007.pelanggaransiswa.Pemberitahuan_Orangtua;
import com.example.andry007.pelanggaransiswa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Login_Orangtua extends AppCompatActivity {
    private EditText InputUserPhoneNumber, InputUserVerificationCode;
    private Button SendVerificationCodeButton, VerifyButton;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseAuth mAuth;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__orangtua);

        mAuth = FirebaseAuth.getInstance();

        InputUserPhoneNumber = findViewById(R.id.phone);
        InputUserVerificationCode = findViewById(R.id.kode);
        SendVerificationCodeButton = findViewById(R.id.kirim_kode);
        VerifyButton = findViewById(R.id.verifikasi);

        SendVerificationCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    String phoneNumber = "+62"+InputUserPhoneNumber.getText().toString();

                    if (TextUtils.isEmpty(phoneNumber)) {
                        Toast.makeText(Login_Orangtua.this, "masukan nomor telepon dengan benar",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                phoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                Login_Orangtua.this,
                                callbacks);
                    }
                }
            }
        });

        VerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   InputUserPhoneNumber.setVisibility(View.INVISIBLE);
                // SendVerificationCodeButton.setVisibility(View.INVISIBLE);

                String verificationCode = InputUserVerificationCode.getText().toString();

                if (TextUtils.isEmpty(verificationCode))
                {
                    Toast.makeText(Login_Orangtua.this, "masukan kode verifikasi dengan benar",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Login_Orangtua.this,
                        "Nomor tidak valid, cek kembali nomor yang diinputkan",
                        Toast.LENGTH_LONG).show();
            }
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token)
            {
                mVerificationId = verificationId;
                mResendToken = token;

                Toast.makeText(Login_Orangtua.this, "Kode telah dikirim, mohon cek dan verifikasi",
                        Toast.LENGTH_SHORT).show();

            }
        };
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                            Toast.makeText(Login_Orangtua.this,
                                    "Sukses.", Toast.LENGTH_SHORT).show();
                            SendUserToPemberitahuan();
                        }
                        else
                        {
                            String message = task.getException().toString();
                            Toast.makeText(Login_Orangtua.this, "Error: " + message, Toast.LENGTH_SHORT).
                                    show();
                        }
                    }
                });
    }

    private void SendUserToPemberitahuan() {
            Intent intent = new Intent(Login_Orangtua.this, CekNIS.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }


    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, CekNIS.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        Intent intent = new Intent(this, CekNIS.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

