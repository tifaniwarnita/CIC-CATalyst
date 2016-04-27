package com.tifaniwarnita.ciccatalystcore.admin;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.Event;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromosiFragment extends Fragment {
    private LayoutInflater inflaterPromosi;
    private LinearLayout dataEventContainer;

    public PromosiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_promosi, container, false);
        inflaterPromosi = inflater;
        dataEventContainer = (LinearLayout) v.findViewById(R.id.daftar_promosi_container);
        // updateDataPromosi();
        return v;
    }

    public void updateDataPromosi() {
        //TODO: Ambil dari database daftar event yang ada
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "Loading...");
        Backendless.Persistence.of( Event.class ).find(new AsyncCallback<BackendlessCollection<Event>>(){
            @Override
            public void handleResponse( BackendlessCollection<Event> results )
            {
                progressDialog.dismiss();
                List<Event> events = results.getData();
                Log.d(PromosiFragment.class.getSimpleName(), "Size: " + events.size());
                if (dataEventContainer.getChildCount() > 0) {
                    dataEventContainer.removeAllViews();
                }
                for (Event event: events) {
                    View eventView = inflaterPromosi.inflate(R.layout.template_promosi, dataEventContainer, false);
                    ((TextView) eventView.findViewById(R.id.event_title)).setText(event.getJudul());
                    ((TextView) eventView.findViewById(R.id.event_date)).setText(event.getTanggal());
                    ((TextView) eventView.findViewById(R.id.event_detail)).setText(event.getDeskripsi());
                    dataEventContainer.addView(eventView);
                }
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Koneksi gagal", Toast.LENGTH_SHORT).show();
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("1", fault.toString());
            }
        });
    }



}
