package com.tifaniwarnita.ciccatalystcore.model;

/**
 * Created by Tifani on 4/19/2016.
 */
public class Pelanggan {
    private String objectId;
    private String id;
    private String nama;
    private String telepon;
    private String email;
    private String token;

    public Pelanggan(String objectId, String id, String nama, String telepon, String email, String token) {
        this.objectId = objectId;
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
        this.token = token;
    }

    public String getObjectId() {
        return objectId;
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

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setId(String id) {
        this.id = id;
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
