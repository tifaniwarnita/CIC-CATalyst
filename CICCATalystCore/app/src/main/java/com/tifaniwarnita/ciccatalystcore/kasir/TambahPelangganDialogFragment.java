package com.tifaniwarnita.ciccatalystcore.kasir;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.Pelanggan;


public class TambahPelangganDialogFragment extends DialogFragment {

    private TambahPelangganDialogFragmentListener dialogFragmentListener;

    public interface TambahPelangganDialogFragmentListener {
        void onTambahPelanggan(String nama, String noHP, String email);
    }

    public TambahPelangganDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.dialog_fragment_tambah_pelanggan, null);

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
                dialogFragmentListener.onTambahPelanggan(nama, noHP, email);
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

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dialogFragmentListener = (TambahPelangganDialogFragmentListener) activity;
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
