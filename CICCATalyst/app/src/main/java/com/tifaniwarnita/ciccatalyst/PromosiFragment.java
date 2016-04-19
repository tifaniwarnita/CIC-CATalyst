package com.tifaniwarnita.ciccatalyst;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.List;


public class PromosiFragment extends Fragment {


    public PromosiFragment() {
        // Required empty public constructor
    }

    public class Event {
        private String objectId;
        private String judul;
        private String tanggal;
        private String deskripsi;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId( String objectId ) {
            this.objectId = objectId;
        }

        public String getJudul() {
            return judul;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi( String deskripsi ) {
            this.deskripsi = deskripsi;
        }
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_promosi, container, false);
        final LinearLayout dataEventContainer = (LinearLayout) v.findViewById(R.id.daftar_promosi_container);
        //TODO: Ambil dari database daftar event yang ada
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "Loading...");
        Backendless.Persistence.of( Event.class ).find(new AsyncCallback<BackendlessCollection<Event>>(){
            @Override
            public void handleResponse( BackendlessCollection<Event> results )
            {
                progressDialog.dismiss();
                List<Event> events = results.getData();
                Log.d(PromosiFragment.class.getSimpleName(), "Size: " + events.size());
                for (Event event: events) {
                    Log.d("1", event.objectId);
                    Log.d("1", event.deskripsi);
                    View eventView = inflater.inflate(R.layout.template_promosi, dataEventContainer, false);
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




        return v;
    }


}
