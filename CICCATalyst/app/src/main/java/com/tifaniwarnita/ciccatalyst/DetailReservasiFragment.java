package com.tifaniwarnita.ciccatalyst;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalyst.controllers.PreferencesController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailReservasiFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATE = "date";
    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

    private Date date;

    private ReservationDetailFragmentListener fragmentListener;

    public interface ReservationDetailFragmentListener {
        void onReservasiButtonClick(Date date);
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
        Button buttonReservasi = (Button) v.findViewById(R.id.button_reservation);
        TextView textViewInfo = (TextView) v.findViewById(R.id.text_view_reservasi_pesan_login);

        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MM yyyy");
            textViewTanggal.setText(dateFormat.format(date));
        }

        if (PreferencesController.isLoggedIn(getContext())) {
            buttonReservasi.setVisibility(View.VISIBLE);
            textViewInfo.setVisibility(View.INVISIBLE);
            buttonReservasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentListener.onReservasiButtonClick(date);
                }
            });
        } else {
            buttonReservasi.setVisibility(View.INVISIBLE);
            textViewInfo.setVisibility(View.VISIBLE);
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
}
