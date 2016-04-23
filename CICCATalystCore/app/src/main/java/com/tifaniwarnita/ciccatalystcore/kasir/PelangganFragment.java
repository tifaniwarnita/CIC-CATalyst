package com.tifaniwarnita.ciccatalystcore.kasir;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.Pelanggan;
import com.tifaniwarnita.ciccatalystcore.model.PelangganTableDataAdapter;

import java.util.Comparator;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PelangganFragment extends Fragment {

    private SortableTableView tableView;
    private boolean isUpdating;


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
        updateTabelPelanggan();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTabelPelanggan();
    }

    private void updateTabelPelanggan() {
        if (KasirActivity.dataPelanggan.size() == 0 && !isUpdating) {
            isUpdating = true;
            updateDataPelanggan();
        } else
            tableView.setDataAdapter(new PelangganTableDataAdapter(getContext(), KasirActivity.dataPelanggan));
    }

    public void updateDataPelanggan() {
        KasirActivity.dataPelanggan.clear();
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "Menunggu...");
        // TODO: Ambil data pelanggan yang ditambahin hari ini aja
        Backendless.Persistence.of( Pelanggan.class ).find(new AsyncCallback<BackendlessCollection<Pelanggan>>(){
            @Override
            public void handleResponse( BackendlessCollection<Pelanggan> results )
            {
                progressDialog.dismiss();
                List<Pelanggan> pelangganBaru = results.getData();
                for (Pelanggan pelanggan: pelangganBaru) {
                    KasirActivity.dataPelanggan.add(pelanggan);
                }
                Log.d(PelangganFragment.class.getSimpleName(), "Size: " + KasirActivity.dataPelanggan.size());
                Log.d(PelangganFragment.class.getSimpleName(), "Nama: " + KasirActivity.dataPelanggan.get(0).getNama());
                tableView.setDataAdapter(new PelangganTableDataAdapter(getContext(), KasirActivity.dataPelanggan));
                isUpdating = false;
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Koneksi gagal", Toast.LENGTH_SHORT).show();
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d(PelangganFragment.class.getSimpleName(), fault.toString());
            }
        });

    }

    private static class PelangganComparator implements Comparator<Pelanggan> {

        @Override
        public int compare(Pelanggan lhs, Pelanggan rhs) {
            return lhs.getNama().compareTo(rhs.getNama());
        }
    }

}
