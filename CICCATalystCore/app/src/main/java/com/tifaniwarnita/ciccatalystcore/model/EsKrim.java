package com.tifaniwarnita.ciccatalystcore.model;

/**
 * Created by Tifani on 4/24/2016.
 */
public class EsKrim {
    String flakes;
    String rasa;
    String topping1;
    String topping2;
    int harga;

    public EsKrim(String flakes, String rasa, String topping1, String topping2, int harga) {
        this.flakes = flakes;
        this.rasa = rasa;
        this.topping1 = topping1;
        this.topping2 = topping2;
        this.harga = harga;
    }

    public String getFlakes() {
        return flakes;
    }

    public String getRasa() {
        return rasa;
    }

    public String getTopping1() {
        return topping1;
    }

    public String getTopping2() {
        return topping2;
    }

    public int getHarga() {
        return harga;
    }
}
