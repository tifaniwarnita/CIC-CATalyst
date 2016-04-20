package com.tifaniwarnita.ciccatalystcore.kasir;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.tifaniwarnita.ciccatalystcore.R;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservasiFragment extends Fragment {

    private ReservationFragmentListener fragmentListener;

    public interface ReservationFragmentListener {
        void onSelectDate(Date date);
    }

    public ReservasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_reservasi, container, false);

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        caldroidFragment.setCaldroidListener(listener);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.calendar_fragment_container, caldroidFragment)
                .commit();

        // Button buttonReservasi = (Button) v.findViewById(R.id.button_reservation);
        TextView textViewInfo = (TextView) v.findViewById(R.id.text_view_reservasi_pesan_login);

        /*if (PreferencesController.isLoggedIn(getContext())) {
            buttonReservasi.setVisibility(View.VISIBLE);
            textViewInfo.setVisibility(View.INVISIBLE);
        } else {
            buttonReservasi.setVisibility(View.INVISIBLE);
            textViewInfo.setVisibility(View.VISIBLE);
        }*/


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Make sure that the container activity has implemented
        //the interface
        try {
            fragmentListener = (ReservationFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LoginFragmentListener methods");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {
            fragmentListener.onSelectDate(date);
        }
    };

}
