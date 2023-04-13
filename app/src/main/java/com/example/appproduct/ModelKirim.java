package com.example.appproduct;

public class ModelKirim {
    private String nama;
    private String alamat;
    private String jumlah;

    public ModelKirim() {

    }

    public ModelKirim(String nama, String alamat, String jumlah) {
        this.nama = nama;
        this.alamat = alamat;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
