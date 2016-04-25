package com.tifaniwarnita.ciccatalystcore.model;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalystcore.R;

/**
 * Created by Tifani on 4/25/2016.
 */
public class MinumanLayout {
    private TextView textViewMinumanKe;
    private Spinner spinnerMinuman;

    public MinumanLayout(View view, int jumlah) {
        textViewMinumanKe = (TextView) view.findViewById(R.id.minuman_ke);
        spinnerMinuman = (Spinner) view.findViewById(R.id.minuman);
        textViewMinumanKe.setText("Minuman ke-" + jumlah);
    }
}
