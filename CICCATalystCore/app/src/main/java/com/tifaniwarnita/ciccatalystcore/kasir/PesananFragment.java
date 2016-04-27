package com.tifaniwarnita.ciccatalystcore.kasir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.PelangganTableDataAdapter;
import com.tifaniwarnita.ciccatalystcore.model.Pesanan;
import com.tifaniwarnita.ciccatalystcore.model.PesananTableAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesananFragment extends Fragment {

    private SortableTableView tableView;
    private LinearLayout pesananContainer;
    private LayoutInflater inflaterPesanan;

    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

    public PesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pesanan, container, false);
        pesananContainer = (LinearLayout) v.findViewById(R.id.pesanan_container);
        inflaterPesanan = inflater;
        /*tableView = (SortableTableView) v.findViewById(R.id.tabel_pesanan);
        String[] header = {"Pemesan          ", "Mulai         ", "Selesai         "};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), header));
        tableView.setColumnCount(3);
        tableView.setColumnComparator(0, new PemesanComparator());
        tableView.setColumnComparator(1, new WaktuMulaiComparator());
        tableView.setColumnComparator(2, new WaktuSelesaiComparator());*/
        updateTabelPesanan();
        return v;
    }

    public void updateTabelPesanan() {

        if (pesananContainer.getChildCount() > 0) {
            pesananContainer.removeAllViews();
        }

        Backendless.Persistence.of(Pesanan.class).find(new AsyncCallback<BackendlessCollection<Pesanan>>() {
            @Override
            public void handleResponse(BackendlessCollection<Pesanan> response) {
                List<Pesanan> pesanans = response.getData();
                int counter = 0;
                for (Pesanan pesanan: pesanans) {
                    Date created = pesanan.getCreated();
                    Calendar startDate = Calendar.getInstance();
                    Calendar endDate = Calendar.getInstance();
                    startDate.setTime(created);
                    endDate.setTime(created);
                    endDate.add(Calendar.HOUR_OF_DAY, pesanan.getDurasi());


                    String counterString = String.valueOf(counter);
                    int counterSize = counterString.length();
                    for (int i = 0; i < 3-counterSize; ++i) {
                        counterString = "0" + counterString;
                    }
                    counterString = "#" + counterString;

                    String dateString = startDate.get(Calendar.HOUR_OF_DAY) + ":" + startDate.get(Calendar.MINUTE)
                                        + " - " +
                                        endDate.get(Calendar.HOUR_OF_DAY) + ":" + endDate.get(Calendar.MINUTE);
                    pesananContainer.addView(createPesanan(counterString, pesanan.getPemesan(), dateString));
                    ++counter;
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        /*
        pesananContainer.addView(createPesanan("#001", "Snowball", "12.00 - 13.00"));
        pesananContainer.addView(createPesanan("#002", "Quincy", "12.00 - 13.00"));
        pesananContainer.addView(createPesanan("#003", "Miaw", "12.00 - 13.00"));
        pesananContainer.addView(createPesanan("#004", "Snowball", "12.00 - 13.00"));
        */
        /*// TODO: ambil dari backendless
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
        tableView.setDataAdapter(new PesananTableAdapter(getContext(), pesananDummy));*/
    }

    public View createPesanan(String id, String nama, String waktu) {
        View pesananView = inflaterPesanan.inflate(R.layout.template_kasir_pesanan, pesananContainer, false);
        ((TextView) pesananView.findViewById(R.id.text_view_pesanan_id)).setText(id);
        ((TextView) pesananView.findViewById(R.id.text_view_pemesan)).setText(nama);
        ((TextView) pesananView.findViewById(R.id.text_view_jam_kunjungan)).setText(waktu);

        return pesananView;
    }

    private static class PemesanComparator implements Comparator<Pesanan> {

        @Override
        public int compare(Pesanan lhs, Pesanan rhs) {
            return lhs.getPemesan().compareTo(rhs.getPemesan());
        }
    }
}
