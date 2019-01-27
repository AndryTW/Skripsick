package com.example.andry007.pelanggaransiswa.firebase;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import static com.android.volley.VolleyLog.TAG;

public class FirebaseInstanceID extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onTokenRefresh() {
        //Mendapatkan Instance dan Memperoleh Token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Menampilkan Token pada Log
        Log.d(TAG, "Token Saya : "+ refreshedToken);

        saveToken(refreshedToken);
    }

    //Method berikut ini digunakan untuk memperoleh token dan mennyimpannya ke server atau sistem lainnya
    private void saveToken(String refreshedToken) {
        SharedPrefManager.getInstance(getApplicationContext()).setToken(refreshedToken);
    }
}
