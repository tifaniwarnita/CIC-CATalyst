package com.tifaniwarnita.ciccatalystcore;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tifaniwarnita.ciccatalystcore.model.Pelanggan;
import com.tifaniwarnita.ciccatalystcore.model.PelangganTableDataAdapter;

import java.util.ArrayList;
import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PelangganFragment extends Fragment {

    private static ArrayList<Pelanggan> dataPelanggan = new ArrayList<>();
    private SortableTableView tableView;


    public PelangganFragment() {
        // Required empty public constructor
        // createPelangganDummy();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pelanggan, container, false);
        tableView = (SortableTableView) v.findViewById(R.id.tabel_pelanggan);
        String[] header = {"Data Pelanggan"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), header));
        tableView.setColumnCount(1);
        tableView.setColumnComparator(0, new PelangganComparator());
        if (dataPelanggan.size() == 0)
            updateDataPelanggan();
        //tableView.setColumnCount(dataPelanggan.size()+1);
        // tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), header));
        /*daftarPelangganContainer = (LinearLayout) v.findViewById(R.id.daftar_pesanan_container);
        refreshPelangganList(inflater, daftarPelangganContainer);*/
        return v;
    }

    private void updateDataPelanggan() {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "Menunggu...");
        Backendless.Persistence.of( Pelanggan.class ).find(new AsyncCallback<BackendlessCollection<Pelanggan>>(){
            @Override
            public void handleResponse( BackendlessCollection<Pelanggan> results )
            {
                progressDialog.dismiss();
                PelangganFragment.dataPelanggan = new ArrayList<>(results.getData());
                Log.d(PelangganFragment.class.getSimpleName(), "Size: " + dataPelanggan.size());
                tableView.setDataAdapter(new PelangganTableDataAdapter(getContext(), dataPelanggan));
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Koneksi gagal", Toast.LENGTH_SHORT).show();
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("1", fault.toString());
            }
        });

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

    /*private void createPelangganDummy() {
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
    }*/

    private static class PelangganComparator implements Comparator<Pelanggan> {

        @Override
        public int compare(Pelanggan lhs, Pelanggan rhs) {
            return lhs.getNama().compareTo(rhs.getNama());
        }
    }

}
