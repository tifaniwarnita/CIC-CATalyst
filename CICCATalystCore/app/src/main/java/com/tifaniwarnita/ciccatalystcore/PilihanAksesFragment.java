package com.tifaniwarnita.ciccatalystcore;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tifaniwarnita.ciccatalystcore.kasir.KasirActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PilihanAksesFragment extends Fragment {


    public PilihanAksesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pilihan_akses, container, false);

        ImageButton buttonKasir = (ImageButton) v.findViewById(R.id.button_kasir);
        ImageButton buttonPelayan = (ImageButton) v.findViewById(R.id.button_pelayan);
        ImageButton buttonAdmin = (ImageButton) v.findViewById(R.id.button_admin);

        buttonKasir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KasirActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                getActivity().startActivity(intent);
            }
        });

        buttonPelayan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PelayanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                getActivity().startActivity(intent);
            }
        });

        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, new AutentikasiAdminFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

}
