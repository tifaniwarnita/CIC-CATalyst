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
        implements DetailReservasiFragment.ReservationDetailFragmentListener,
        TambahReservasiDialogFragment.TambahReservasiDialogFragmentListener {

    private Date date = null;
    private DetailReservasiFragment detailReservasiFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reservasi);

        if (getIntent().hasExtra(getResources().getString(R.string.detail_reservasi)))
            date = DetailReservasiFragment.convertStringToDate(getIntent().getStringExtra(getResources().getString(R.string.detail_reservasi)));
        else
            date = new Date();

        FragmentManager fm = getSupportFragmentManager();
        detailReservasiFragment = DetailReservasiFragment.newInstance(
                DetailReservasiFragment.convertDateToString(date));
        if (date != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, detailReservasiFragment)
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
                TambahReservasiDialogFragment tambahReservasiDialog = TambahReservasiDialogFragment.newInstance(
                        DetailReservasiFragment.convertDateToString(date)
                );
                tambahReservasiDialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void onDetailReservasiBack() {
        detailReservasiFragment = null;
    }

    @Override
    public void onReservasi(int i, int j) {
        if (detailReservasiFragment != null) {
            detailReservasiFragment.updateDummy(i, j);
        }
    }
}
