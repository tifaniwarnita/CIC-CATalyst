package com.tifaniwarnita.ciccatalystcore.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tifani on 4/24/2016.
 */
public class Pesanan {
    String pemesan;
    int durasi;
    public Date created;

    public Pesanan() {

    }

    public Pesanan(String pemesan, int durasi) {
        this.pemesan = pemesan;
        this.durasi = durasi;
    }

    public String getPemesan() {
        return pemesan;
    }

    public void setPemesan(String pemesan) {
        this.pemesan = pemesan;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }
}
