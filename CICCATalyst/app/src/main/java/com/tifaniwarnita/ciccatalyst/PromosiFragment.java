package com.tifaniwarnita.ciccatalyst;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PromosiFragment extends Fragment {


    public PromosiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_promosi, container, false);

        //TODO: Ambil dari database daftar event yang ada
        return v;
    }


}
