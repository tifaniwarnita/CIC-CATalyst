package com.tifaniwarnita.ciccatalystcore.admin;


import android.app.AlertDialog;
import android.app.Dialog;
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

import com.tifaniwarnita.ciccatalystcore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TambahEventDialogFragment extends DialogFragment {


    public TambahEventDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.dialog_fragment_tambah_event, null);

        final EditText editTextNama = (EditText) promptView.findViewById(R.id.edit_text_nama);
        final EditText editTextNoHP = (EditText) promptView.findViewById(R.id.edit_text_no_hp);
        final EditText editTextEmail = (EditText) promptView.findViewById(R.id.edit_text_email);

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
                String nama = editTextNama.getText().toString();
                String noHP = editTextNoHP.getText().toString();
                String email = editTextEmail.getText().toString();
                Toast.makeText(getContext(), "Data pelanggan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
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

}
