package com.example.andry007.pelanggaransiswa.Object;

import java.io.Serializable;

public class Data_Siswa implements Serializable {
    private String kelas;
    private String nama;
    private String nama_Ortu;
    private String no_Ortu;
    private String thn_Ajaran;
    private String nis;
    private String jk;
    private String alamat;

    public Data_Siswa(){}

    public Data_Siswa(String kelas, String nama, String nama_Ortu, String no_Ortu,
                      String thn_Ajaran, String nis, String jk, String alamat) {
        this.kelas = kelas;
        this.nama = nama;
        this.nama_Ortu = nama_Ortu;
        this.no_Ortu = no_Ortu;
        this.thn_Ajaran = thn_Ajaran;
        this.nis = nis;
        this.jk = jk;
        this.alamat = alamat;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_Ortu() {
        return nama_Ortu;
    }

    public void setNama_Ortu(String nama_Ortu) {
        this.nama_Ortu = nama_Ortu;
    }

    public String getNo_Ortu() {
        return no_Ortu;
    }

    public void setNo_Ortu(String no_Ortu) {
        this.no_Ortu = no_Ortu;
    }

    public String getThn_Ajaran() {
        return thn_Ajaran;
    }

    public void setThn_Ajaran(String thn_Ajaran) {
        this.thn_Ajaran = thn_Ajaran;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }





}

