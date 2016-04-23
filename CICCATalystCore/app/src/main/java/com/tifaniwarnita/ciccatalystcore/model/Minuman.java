package com.tifaniwarnita.ciccatalystcore.model;

/**
 * Created by Tifani on 4/24/2016.
 */
public class Minuman {
    String nama;
    int harga;

    public Minuman(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }
}
