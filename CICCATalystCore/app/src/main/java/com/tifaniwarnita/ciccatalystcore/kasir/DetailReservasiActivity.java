package com.tifaniwarnita.ciccatalystcore.kasir;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tifaniwarnita.ciccatalystcore.PilihanAksesFragment;
import com.tifaniwarnita.ciccatalystcore.R;

import java.util.Date;

public class DetailReservasiActivity extends AppCompatActivity
        implements DetailReservasiFragment.ReservationDetailFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reservasi);

        String date = null;
        if (getIntent().hasExtra(getResources().getString(R.string.detail_reservasi)))
            date = getIntent().getStringExtra(getResources().getString(R.string.detail_reservasi));

        FragmentManager fm = getSupportFragmentManager();
        if (date != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, DetailReservasiFragment.newInstance(date))
                    .commit();
        } else {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new DetailReservasiFragment())
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(getApplicationContext(), "DETAIL RESERVASI",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onReservasiButtonClick(Date date) {

    }

    @Override
    public void onDetailReservasiBack() {

    }
}
