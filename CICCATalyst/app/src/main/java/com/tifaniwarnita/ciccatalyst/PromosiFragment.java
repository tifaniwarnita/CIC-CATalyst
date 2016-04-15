package com.tifaniwarnita.ciccatalyst;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        private String deskripsi;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId( String objectId ) {
            this.objectId = objectId;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi( String deskripsi ) {
            this.deskripsi = deskripsi;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_promosi, container, false);

        //TODO: Ambil dari database daftar event yang ada
        Backendless.Persistence.of( Event.class ).find(new AsyncCallback<BackendlessCollection<Event>>(){
            @Override
            public void handleResponse( BackendlessCollection<Event> results )
            {
                List<Event> events = results.getData();
                for (Event event: events) {
                    Log.d("1", event.objectId);
                    Log.d("1", event.deskripsi);
                }
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("1", fault.toString());
            }
        });




        return v;
    }


}
