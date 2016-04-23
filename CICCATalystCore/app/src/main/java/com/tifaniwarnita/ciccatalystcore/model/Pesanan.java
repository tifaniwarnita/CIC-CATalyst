package com.tifaniwarnita.ciccatalystcore.model;

import java.util.ArrayList;

/**
 * Created by Tifani on 4/24/2016.
 */
public class Pesanan {
    String pemesan;
    String waktuMulai;
    String waktuSelesai;
    ArrayList<EsKrim> daftarEsKrim = new ArrayList<>();
    ArrayList<Minuman> daftarMinuman = new ArrayList<>();

    public Pesanan(String pemesan, String waktuMulai, String waktuSelesai, ArrayList<EsKrim> daftarEsKrim, ArrayList<Minuman> daftarMinuman) {
        this.pemesan = pemesan;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        // TODO:
        //this.daftarEsKrim = daftarEsKrim;
        //this.daftarMinuman = daftarMinuman;
    }

    public String getPemesan() {
        return pemesan;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public ArrayList<EsKrim> getDaftarEsKrim() {
        return daftarEsKrim;
    }

    public ArrayList<Minuman> getDaftarMinuman() {
        return daftarMinuman;
    }
}
