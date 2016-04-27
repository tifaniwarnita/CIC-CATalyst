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
 * Created by Tifani on 4/24/2016.
 */
public class PesananTableAdapter extends TableDataAdapter<Pesanan> {

    public PesananTableAdapter(Context context, List<Pesanan> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Pesanan pesanan = getRowData(rowIndex);
        View renderedView = null;

        /*switch (columnIndex) {
            case 0:
                renderedView = LayoutInflater.from(getContext())
                        .inflate(R.layout.template_pesanan_list_pemesan, null);
                // renderedView.setTag(R.string.id_pelanggan, pesanan.getObjectId());
                ((TextView) renderedView.findViewById(R.id.pemesan)).setText(pesanan.getPemesan());
                break;
            case 1:
                renderedView = LayoutInflater.from(getContext())
                        .inflate(R.layout.template_pesanan_list_waktu, null);
                // renderedView.setTag(R.string.id_pelanggan, pesanan.getObjectId());
                // ((TextView) renderedView.findViewById(R.id.waktu)).setText(pesanan.getWaktuMulai()+ " - " + pesanan.getWaktuSelesai());
                ((TextView) renderedView.findViewById(R.id.waktu)).setText(pesanan.getWaktuMulai());
                break;
            case 2:
                renderedView = LayoutInflater.from(getContext())
                        .inflate(R.layout.template_pesanan_list_waktu, null);
                // renderedView.setTag(R.string.id_pelanggan, pesanan.getObjectId());
                ((TextView) renderedView.findViewById(R.id.waktu)).setText(pesanan.getWaktuSelesai());
                break;
        }*/

        return renderedView;
    }
}
