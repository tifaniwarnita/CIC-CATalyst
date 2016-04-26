package com.tifaniwarnita.ciccatalystcore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class PelayanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayan);

        String applicationId = getResources().getString(R.string.application_id);
        String secretKey = getResources().getString(R.string.secret_key);
        Backendless.initApp(this, applicationId, secretKey, "v1");

        String gcmSenderId = getResources().getString(R.string.google_api_project_number);
        Backendless.Messaging.registerDevice(gcmSenderId, "pesanan", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.d(this.getClass().getSimpleName(), "Registered");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(this.getClass().getSimpleName(), fault.toString());
            }
        });
    }
}
