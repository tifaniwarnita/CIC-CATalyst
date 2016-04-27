package com.tifaniwarnita.ciccatalystcore.admin;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class TambahEventDialogFragment extends DialogFragment {

    private TambahEventDialogFragmentListener dialogFragmentListener;

    public interface TambahEventDialogFragmentListener {
        void onTambahEvent(String judul, String jangka, String deskripsi);
    }


    public TambahEventDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.dialog_fragment_tambah_event, null);

        final EditText editTextJudul = (EditText) promptView.findViewById(R.id.edit_text_judul_promosi);
        final EditText editTextJangka = (EditText) promptView.findViewById(R.id.edit_text_jangka_waktu);
        final EditText editTextDeskripsi = (EditText) promptView.findViewById(R.id.edit_text_deskripsi);

        Button buttonBatal = (Button) promptView.findViewById(R.id.button_batal);
        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button buttonReservasi = (Button) promptView.findViewById(R.id.button_tambah);
        buttonReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = editTextJudul.getText().toString();
                String jangka = editTextJangka.getText().toString();
                String deskripsi = editTextDeskripsi.getText().toString();
                dialogFragmentListener.onTambahEvent(judul, jangka, deskripsi);
                dismiss();
            }
        });

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

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dialogFragmentListener = (TambahEventDialogFragmentListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialogFragmentListener = null;
    }

}
