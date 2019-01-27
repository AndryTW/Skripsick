package com.example.andry007.pelanggaransiswa.Object;

public class KategoriPelanggaran {
    private  String id,kategori, skor;

    public KategoriPelanggaran(){}


    public KategoriPelanggaran(String id, String kategori, String skor) {
        id = id;
        kategori = kategori;
        skor = skor;
    }

    public KategoriPelanggaran(String skor,String kategori){
        this.skor= skor;
        this.kategori= kategori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }

    @Override
    public String toString() {
        return kategori;
    }
}
