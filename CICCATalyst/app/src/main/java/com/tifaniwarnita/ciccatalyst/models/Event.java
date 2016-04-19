package com.tifaniwarnita.ciccatalyst.models;

/**
 * Created by Tifani on 4/19/2016.
 */
public class Event {
    private String objectId;
    private String judul;
    private String tanggal;
    private String deskripsi;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId( String objectId ) {
        this.objectId = objectId;
    }

    public String getJudul() {
        return judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi( String deskripsi ) {
        this.deskripsi = deskripsi;
    }
}
