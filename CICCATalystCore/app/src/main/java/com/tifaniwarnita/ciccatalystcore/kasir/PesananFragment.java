package com.tifaniwarnita.ciccatalystcore.kasir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifaniwarnita.ciccatalystcore.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesananFragment extends Fragment {


    public PesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesanan, container, false);
    }

}
