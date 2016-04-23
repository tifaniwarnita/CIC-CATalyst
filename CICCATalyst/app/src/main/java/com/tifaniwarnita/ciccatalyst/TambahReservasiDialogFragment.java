package com.tifaniwarnita.ciccatalyst;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TambahReservasiDialogFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATE = "date";

    private Date date;
    private Calendar calendar;
    private TextView textViewTanggal;
    private Spinner spinnerWaktuMulai;
    private Spinner spinnerWaktuSelesai;

    private TambahReservasiDialogFragmentListener dialogFragmentListener;

    public interface TambahReservasiDialogFragmentListener {
        void onReservasi(int i, int j);
    }

    public TambahReservasiDialogFragment() {
        // Required empty public constructor
    }

    public static TambahReservasiDialogFragment newInstance(String dateString) {
        TambahReservasiDialogFragment fragment = new TambahReservasiDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, dateString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = DetailReservasiFragment.convertStringToDate(
                    getArguments().getString(ARG_DATE));
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } else {
            date = new Date();
            calendar = Calendar.getInstance();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.dialog_fragment_tambah_reservasi, null);

        textViewTanggal = (TextView) promptView.findViewById(R.id.text_view_tanggal);
        showDate();
        textViewTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        myDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE));
                datePickerDialog.show();
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

        Button buttonBatal = (Button) promptView.findViewById(R.id.button_batal);

        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button buttonReservasi = (Button) promptView.findViewById(R.id.button_reservasi);
        buttonReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragmentListener.onReservasi(spinnerWaktuMulai.getSelectedItemPosition(),
                        spinnerWaktuSelesai.getSelectedItemPosition());
                Toast.makeText(getContext(), "Reservasi berhasil", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(promptView);
        final Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
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
            dialogFragmentListener = (TambahReservasiDialogFragmentListener) activity;
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

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            calendar.set(arg1, arg2, arg3);
            showDate();
        }
    };

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

    private void showDate() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        StringBuilder dateBuilder = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
        textViewTanggal.setText(Html.fromHtml("<u>" + dateBuilder + "</u>"));
    }
}
