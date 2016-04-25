package com.tifaniwarnita.ciccatalystcore.model;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalystcore.R;

/**
 * Created by Tifani on 4/25/2016.
 */
public class EsKrimLayout {
    TextView textViewEsKrimKe;
    Spinner spinnerFlakes;
    Spinner spinnerRasaEskrim;
    Spinner spinnerTopping1;
    Spinner spinnerTopping2;

    public EsKrimLayout(View view, int jumlah) {
        textViewEsKrimKe = (TextView) view.findViewById(R.id.es_krim_ke);
        spinnerFlakes = (Spinner) view.findViewById(R.id.spinner_flakes);
        spinnerRasaEskrim = (Spinner) view.findViewById(R.id.spinner_rasa_es_krim);
        spinnerTopping1 = (Spinner) view.findViewById(R.id.spinner_topping_1);
        spinnerTopping2 = (Spinner) view.findViewById(R.id.spinner_topping_2);
        textViewEsKrimKe.setText("Es Krim ke-" + jumlah);
    }

    public TextView getTextViewEsKrimKe() {
        return textViewEsKrimKe;
    }

    public Spinner getSpinnerFlakes() {
        return spinnerFlakes;
    }

    public Spinner getSpinnerRasaEskrim() {
        return spinnerRasaEskrim;
    }

    public Spinner getSpinnerTopping1() {
        return spinnerTopping1;
    }

    public Spinner getSpinnerTopping2() {
        return spinnerTopping2;
    }
}
