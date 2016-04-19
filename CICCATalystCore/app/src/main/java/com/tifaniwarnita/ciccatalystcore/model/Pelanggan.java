package com.tifaniwarnita.ciccatalystcore.model;

/**
 * Created by Tifani on 4/19/2016.
 */
public class Pelanggan {
    private String id;
    private String nama;
    private String telepon;
    private String email;
    private String token;

    public Pelanggan(String id, String nama, String telepon, String email, String token) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
