package com.tifaniwarnita.ciccatalystcore.kasir;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.EsKrim;
import com.tifaniwarnita.ciccatalystcore.model.EsKrimLayout;
import com.tifaniwarnita.ciccatalystcore.model.MinumanLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TambahPesananDialogFragment extends DialogFragment {
    private Spinner spinnerWaktuMulai;
    private Spinner spinnerWaktuSelesai;
    private LinearLayout esKrimContainer;
    private LinearLayout minumanContainer;
    private ArrayList<EsKrimLayout> pesananEsKrim = new ArrayList<>();
    private ArrayList<MinumanLayout> pesananMinuman = new ArrayList<>();

    public TambahPesananDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.dialog_fragment_tambah_pesanan, null);

        final EditText editTextNama = (EditText) promptView.findViewById(R.id.edit_text_nama);
        esKrimContainer = (LinearLayout) promptView.findViewById(R.id.es_krim_container);
        minumanContainer = (LinearLayout) promptView.findViewById(R.id.minuman_container);

        Button buttonBatal = (Button) promptView.findViewById(R.id.button_batal);
        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button buttonTambah = (Button) promptView.findViewById(R.id.button_tambah);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish("CIC CATalyst Core", "Pesanan baru");
                Toast.makeText(getContext(), "Data pesanan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        LinearLayout buttonTambahEsKrim = (LinearLayout) promptView.findViewById(R.id.button_tambah_es_krim);
        LinearLayout buttonTambahMinuman = (LinearLayout) promptView.findViewById(R.id.button_tambah_minuman);

        buttonTambahEsKrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahEsKrim();
            }
        });

        buttonTambahMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahMinuman();
            }
        });

        String[] waktu = getResources().getStringArray(R.array.jam_mulai_selesai_reservasi);
        spinnerWaktuMulai = (Spinner) promptView.findViewById(R.id.spinner_waktu_mulai);
        ArrayAdapter<CharSequence> adapterWaktuMulai = new ArrayAdapter<CharSequence>(
                getActivity(),
                android.R.layout.simple_spinner_item);
        for (int i=0; i<waktu.length-1; i++) {
            adapterWaktuMulai.add(waktu[i]);
        }
        adapterWaktuMulai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWaktuMulai.setAdapter(adapterWaktuMulai);
        spinnerWaktuMulai.setOnItemSelectedListener(spinnerWaktuMulaiListener);
        spinnerWaktuSelesai = (Spinner) promptView.findViewById(R.id.spinner_waktu_selesai);
        ArrayAdapter<CharSequence> adapterWaktuSelesai = new ArrayAdapter<CharSequence>(
                getActivity(),
                android.R.layout.simple_spinner_item);
        for (int i=1; i<waktu.length; i++) {
            adapterWaktuSelesai.add(waktu[i]);
        }
        adapterWaktuSelesai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWaktuSelesai.setAdapter(adapterWaktuSelesai);

        tambahEsKrim();
        tambahMinuman();



        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(promptView);
        final Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private void publish(String title, String message) {
        Log.d(this.getClass().getSimpleName(), "Publish");
        PublishOptions publishOptions = new PublishOptions();
        publishOptions.putHeader( "android-ticker-text", "You just got a push notification!");
        publishOptions.putHeader( "android-content-title", title );
        publishOptions.putHeader( "android-content-text", message);
        Backendless.Messaging.publish("pesanan", "Hi Devices!", publishOptions, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {
                Log.d(TambahPesananDialogFragment.class.getSimpleName(), response.toString());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TambahPesananDialogFragment.class.getSimpleName(), fault.toString());
            }
        });
    }

    private void tambahEsKrim() {
        View tambahEsKrimView = LayoutInflater.from(getContext()).inflate(R.layout.template_tambah_es_krim, esKrimContainer, false);
        EsKrimLayout esKrimLayout = new EsKrimLayout(tambahEsKrimView, pesananEsKrim.size()+1);
        pesananEsKrim.add(esKrimLayout);
        esKrimContainer.addView(tambahEsKrimView);
    }

    private void tambahMinuman() {
        View tambahMinumanView = LayoutInflater.from(getContext()).inflate(R.layout.template_tambah_minuman, minumanContainer, false);
        MinumanLayout minumanLayout = new MinumanLayout(tambahMinumanView, pesananMinuman.size()+1);
        pesananMinuman.add(minumanLayout);
        minumanContainer.addView(tambahMinumanView);
    }

    private Spinner.OnItemSelectedListener spinnerWaktuMulaiListener = new Spinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String[] waktu = getResources().getStringArray(R.array.jam_mulai_selesai_reservasi);
            ArrayAdapter<CharSequence> adapterWaktuSelesai = new ArrayAdapter<CharSequence>(
                    getActivity(),
                    android.R.layout.simple_spinner_item);
            for (int i=position+1; i<waktu.length; i++) {
                adapterWaktuSelesai.add(waktu[i]);
            }
            adapterWaktuSelesai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerWaktuSelesai.setAdapter(adapterWaktuSelesai);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
