package com.tifaniwarnita.ciccatalystcore.model;

/**
 * Created by Tifani on 4/19/2016.
 */
public class Pelanggan {
    private String objectId;
    private String nama;
    private String telepon;
    private String email;
    private String token;

    public String getObjectId() {
        return objectId;
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

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
