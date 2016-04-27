package com.tifaniwarnita.ciccatalystcore.kasir;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalystcore.R;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailReservasiFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATE = "date";
    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

    private Date date;
    private ArrayList<TextView> textViewJam = new ArrayList<>();
    private ArrayList<TextView> textViewPemesan = new ArrayList<>();

    private ReservationDetailFragmentListener fragmentListener;

    public interface ReservationDetailFragmentListener {
        void onDetailReservasiBack();
    }

    public DetailReservasiFragment() {

    }

    public static DetailReservasiFragment newInstance(String date) {
        DetailReservasiFragment fragment = new DetailReservasiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = convertStringToDate(
                    getArguments().getString(ARG_DATE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_detail_reservasi, container, false);

        TextView textViewTanggal = (TextView) v.findViewById(R.id.text_view_tanggal);
        TextView textViewInfo = (TextView) v.findViewById(R.id.text_view_reservasi_pesan_login);
        int[] idJam = {
                R.id.jam_1,
                R.id.jam_2,
                R.id.jam_3,
                R.id.jam_4,
                R.id.jam_5,
                R.id.jam_6,
                R.id.jam_7,
                R.id.jam_8,
                R.id.jam_9,
                R.id.jam_10,
                R.id.jam_11,
        };

        int[] idPemesan = {
                R.id.pemesan_1,
                R.id.pemesan_2,
                R.id.pemesan_3,
                R.id.pemesan_4,
                R.id.pemesan_5,
                R.id.pemesan_6,
                R.id.pemesan_7,
                R.id.pemesan_8,
                R.id.pemesan_9,
                R.id.pemesan_10,
                R.id.pemesan_11,
        };

        for (int i=0; i<idJam.length; i++) {
            TextView t = (TextView) v.findViewById(idJam[i]);
            TextView t2 = (TextView) v.findViewById(idPemesan[i]);
            textViewJam.add(t);
            textViewPemesan.add(t);
        }

        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MM yyyy");
            textViewTanggal.setText(dateFormat.format(date));
        }

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReservationDetailFragmentListener) {
            fragmentListener = (ReservationDetailFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener.onDetailReservasiBack();
        fragmentListener = null;
    }

    public static Date convertStringToDate(String dateString) {
        Date convertDate = null;
        SimpleDateFormat format = new SimpleDateFormat
                (DetailReservasiFragment.DEFAULT_DATE_FORMAT);
        try {
            convertDate = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertDate;
    }

    public static String convertDateToString(Date date) {
        String dateString = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat
                (DetailReservasiFragment.DEFAULT_DATE_FORMAT);
        dateString = dateFormat.format(date);
        return dateString;
    }

    public void updateDummy(String pemesan, int mulai, int selesai) {
        for(int i = mulai; i<(mulai + selesai)+1; i++) {
            textViewPemesan.get(i).setText(pemesan);
            textViewPemesan.get(i).setVisibility(View.VISIBLE);
            textViewJam.get(i).setText("Sudah dipesan");
        }
    }
}
