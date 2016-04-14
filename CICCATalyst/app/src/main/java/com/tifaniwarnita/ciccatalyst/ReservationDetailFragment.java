package com.tifaniwarnita.ciccatalyst;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReservationDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATE = "date";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private Date date;

    private ReservationDetailFragmentListener fragmentListener;

    public interface ReservationDetailFragmentListener {

    }

    public ReservationDetailFragment() {

    }

    public static ReservationDetailFragment newInstance(String date) {
        ReservationDetailFragment fragment = new ReservationDetailFragment();
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
        View v =  inflater.inflate(R.layout.fragment_reservation_detail, container, false);
        TextView test = (TextView) v.findViewById(R.id.test_text_view);
        test.setText(date.toString());

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
                (ReservationDetailFragment.DEFAULT_DATE_FORMAT);
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
                (ReservationDetailFragment.DEFAULT_DATE_FORMAT);
        dateString = dateFormat.format(date);
        return dateString;
    }
}
