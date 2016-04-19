package com.tifaniwarnita.ciccatalystcore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalystcore.model.Pelanggan;
import com.tifaniwarnita.ciccatalystcore.model.PelangganTableDataAdapter;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PelangganFragment extends Fragment {

    private ArrayList<Pelanggan> dataPelanggan = new ArrayList<>();
    private LinearLayout daftarPelangganContainer;
    private static final String[][] DATA_TO_SHOW = { { "This", "is", "a", "test" },
            { "and", "a", "second", "test" } };


    public PelangganFragment() {
        // Required empty public constructor
        createPelangganDummy();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pelanggan, container, false);
        TableView tableView = (TableView) v.findViewById(R.id.tabel_pelanggan);
        String[] header = {"Data Pelanggan"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), header));
        String[][] data = {{dataPelanggan.get(0).getNama()},
                {dataPelanggan.get(1).getNama()},
                {dataPelanggan.get(2).getNama()}
        };
        tableView.setColumnCount(1);
        //tableView.setColumnCount(dataPelanggan.size()+1);
        // tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), header));
        tableView.setDataAdapter(new PelangganTableDataAdapter(getContext(), dataPelanggan));
        /*daftarPelangganContainer = (LinearLayout) v.findViewById(R.id.daftar_pesanan_container);
        refreshPelangganList(inflater, daftarPelangganContainer);*/
        return v;
    }

    private void refreshPelangganList(LayoutInflater inflater, ViewGroup container) {
        /*for(int i = 0; i<dataPelanggan.size(); i++) {
            daftarPelangganContainer.addView(inflater.inflate(R.layout.template_horizontal_line, container, false));
            View v = inflater.inflate(R.layout.template_pelanggan_list, container, false);
            v.setTag(R.string.id_pelanggan, dataPelanggan.get(i).getId());
            ((TextView) v.findViewById(R.id.nama_pelanggan)).setText(dataPelanggan.get(i).getNama());
            //TODO BUTTON LISTENER
            daftarPelangganContainer.addView(v);
        }*/
    }

    private void createPelangganDummy() {
        dataPelanggan.add(new Pelanggan(
                "1",
                "Tifani Warnita",
                "081286264299",
                "tifaniwarnita@gmail.com",
                "KUCINGTERBANG"
        ));
        dataPelanggan.add(new Pelanggan(
                "2",
                "Aufar Gilbran",
                "085722835981",
                "kucingimbalance@gmail.com",
                "KUCINGMEONG"
        ));
        dataPelanggan.add(new Pelanggan(
                "3",
                "Lucky Cahyadi",
                "081322835981",
                "lilalilaaingpusing@gmail.com",
                "KUCINGBUMBUM"
        ));
    }



}
