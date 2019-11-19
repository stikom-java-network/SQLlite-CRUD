package com.example.pos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    Context x;
    String kode[];
    String nama[];
    String satuan[];
    String hargabeli[];
    String hargajual[];
    String diskon[];
    LayoutInflater y;

    public Adapter(Context x, String[] kode, String[] nama, String[] satuan, String[] hargabeli, String[] hargajual, String[] diskon) {
        this.x = x;
        this.kode = kode;
        this.nama = nama;
        this.satuan = satuan;
        this.hargabeli = hargabeli;
        this.hargajual = hargajual;
        this.diskon = diskon;
        y = (LayoutInflater.from(x));
    }

    @Override
    public int getCount() {
        return kode.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =y.inflate(R.layout.list_barang, null);
        TextView kode1=(TextView)convertView.findViewById(R.id.Kode);
        TextView nama1=(TextView)convertView.findViewById(R.id.Nama);
        TextView satuan1=(TextView)convertView.findViewById(R.id.Satuan);
        TextView hargabeli1=(TextView)convertView.findViewById(R.id.HargaBeli);
        TextView hargajual1=(TextView)convertView.findViewById(R.id.HargaJual);
        TextView diskon1=(TextView)convertView.findViewById(R.id.Diskon);
        kode1.setText(kode[position]);
        nama1.setText(nama[position]);
        satuan1.setText(satuan[position]);
        hargabeli1.setText(hargabeli[position]);
        hargajual1.setText(hargajual[position]);
        diskon1.setText(diskon[position]);
        return convertView;
    }
}
