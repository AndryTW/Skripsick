package com.example.andry007.pelanggaransiswa.Object;

public class Pelanggaran {

    private String id;

    private String jenisPelanggaran;
    private String total_Poin;
    private String saran;
    private String waktu;

    public Pelanggaran() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenisPelanggaran() {
        return jenisPelanggaran;
    }

    public void setJenisPelanggaran(String jenisPelanggaran) {
        this.jenisPelanggaran = jenisPelanggaran;
    }

    public String getTotal_Poin() {
        return total_Poin;
    }

    public void setTotal_Poin(String total_Poin) {
        this.total_Poin = total_Poin;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}