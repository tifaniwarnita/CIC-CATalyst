package com.tifaniwarnita.ciccatalystcore.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalystcore.R;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by Tifani on 4/19/2016.
 */
public class PelangganTableDataAdapter extends TableDataAdapter<Pelanggan> {

    public PelangganTableDataAdapter(Context context, List<Pelanggan> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Pelanggan pelanggan = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = LayoutInflater.from(getContext())
                        .inflate(R.layout.template_pelanggan_list, null);


                renderedView.setTag(R.string.id_pelanggan, pelanggan.getId());
                ((TextView) renderedView.findViewById(R.id.nama_pelanggan)).setText(pelanggan.getNama());
                break;
        }

        return renderedView;
    }
}
