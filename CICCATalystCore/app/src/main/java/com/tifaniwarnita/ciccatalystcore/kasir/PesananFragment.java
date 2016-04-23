package com.tifaniwarnita.ciccatalystcore.kasir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.PelangganTableDataAdapter;
import com.tifaniwarnita.ciccatalystcore.model.Pesanan;
import com.tifaniwarnita.ciccatalystcore.model.PesananTableAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesananFragment extends Fragment {

    private SortableTableView tableView;

    public PesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pesanan, container, false);
        tableView = (SortableTableView) v.findViewById(R.id.tabel_pesanan);
        String[] header = {"Pemesan          ", "Mulai         ", "Selesai         "};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), header));
        tableView.setColumnCount(3);
        tableView.setColumnComparator(0, new PemesanComparator());
        tableView.setColumnComparator(1, new WaktuMulaiComparator());
        tableView.setColumnComparator(2, new WaktuSelesaiComparator());
        updateTabelPesanan();
        return v;
    }

    public void updateTabelPesanan() {
        // TODO: ambil dari backendless
        List<Pesanan> pesananDummy = new ArrayList<>();
        pesananDummy.add(new Pesanan("Tifani", "11.00", "12.00", null, null));
        pesananDummy.add(new Pesanan("Bruno", "11.00", "12.00", null, null));
        pesananDummy.add(new Pesanan("Snowball", "12.00", "14.00", null, null));
        pesananDummy.add(new Pesanan("Quincy", "12.00", "13.00", null, null));
        pesananDummy.add(new Pesanan("Inocci", "15.00", "17.00", null, null));
        pesananDummy.add(new Pesanan("Yukine", "15.00", "20.00", null, null));
        pesananDummy.add(new Pesanan("Erica", "15.00", "16.00", null, null));
        pesananDummy.add(new Pesanan("Popo", "17.00", "19.00", null, null));

        pesananDummy.add(new Pesanan("Quincy", "12.00", "13.00", null, null));
        pesananDummy.add(new Pesanan("Inocci", "15.00", "17.00", null, null));
        pesananDummy.add(new Pesanan("Yukine", "15.00", "20.00", null, null));
        pesananDummy.add(new Pesanan("Erica", "15.00", "16.00", null, null));
        pesananDummy.add(new Pesanan("Popo", "17.00", "19.00", null, null));
        pesananDummy.add(new Pesanan("Quincy", "12.00", "13.00", null, null));
        pesananDummy.add(new Pesanan("Inocci", "15.00", "17.00", null, null));
        pesananDummy.add(new Pesanan("Yukine", "15.00", "20.00", null, null));
        pesananDummy.add(new Pesanan("Erica", "15.00", "16.00", null, null));
        pesananDummy.add(new Pesanan("Popo", "17.00", "19.00", null, null));
        pesananDummy.add(new Pesanan("Quincy", "12.00", "13.00", null, null));
        pesananDummy.add(new Pesanan("Inocci", "15.00", "17.00", null, null));
        pesananDummy.add(new Pesanan("Yukine", "15.00", "20.00", null, null));
        pesananDummy.add(new Pesanan("Erica", "15.00", "16.00", null, null));
        pesananDummy.add(new Pesanan("Popo", "17.00", "19.00", null, null));
        tableView.setDataAdapter(new PesananTableAdapter(getContext(), pesananDummy));
    }

    private static class PemesanComparator implements Comparator<Pesanan> {

        @Override
        public int compare(Pesanan lhs, Pesanan rhs) {
            return lhs.getPemesan().compareTo(rhs.getPemesan());
        }
    }

    private static class WaktuMulaiComparator implements Comparator<Pesanan> {

        @Override
        public int compare(Pesanan lhs, Pesanan rhs) {
            return lhs.getWaktuMulai().compareTo(rhs.getWaktuMulai());
        }
    }

    private static class WaktuSelesaiComparator implements Comparator<Pesanan> {

        @Override
        public int compare(Pesanan lhs, Pesanan rhs) {
            return lhs.getWaktuSelesai().compareTo(rhs.getWaktuSelesai());
        }
    }

}
