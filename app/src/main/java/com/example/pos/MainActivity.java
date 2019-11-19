package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static String dbname="stok.db";
    EditText kode, nama, satuan, hbeli, hjual, diskon;
    Button save, update, delete, clear;
    ListView jisi;
    SQLiteDatabase db;
    String kd, nm, st, hb, hj, dsk;
    int x;
    String kode11[];
    String nama11[];
    String satuan11[];
    String hbeli11[];
    String hjual11[];
    String diskon11[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kode = findViewById(R.id.id_barang);
        nama = findViewById(R.id.nama_barang);
        satuan = findViewById(R.id.satuan_barang);
        hbeli = findViewById(R.id.harga_beli);
        hjual = findViewById(R.id.harga_jual);
        diskon = findViewById(R.id.diskon);
        jisi = findViewById(R.id.isi);

        save = findViewById(R.id.btnSimpan);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnHapus);
        clear = findViewById(R.id.btnClear);

        jisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kd = ((TextView)view.findViewById(R.id.Kode)).getText().toString();
                nm = ((TextView)view.findViewById(R.id.Nama)).getText().toString();
                st = ((TextView)view.findViewById(R.id.Satuan)).getText().toString();
                hb = ((TextView)view.findViewById(R.id.HargaBeli)).getText().toString();
                hj = ((TextView)view.findViewById(R.id.HargaJual)).getText().toString();
                dsk = ((TextView)view.findViewById(R.id.Diskon)).getText().toString();

                kode.setText(kd);
                nama.setText(nm);
                satuan.setText(st);
                hbeli.setText(hb);
                hjual.setText(hj);
                diskon.setText(dsk);

                save.setEnabled(false);
                delete.setEnabled(true);
                update.setEnabled(true);
                clear.setEnabled(true);
            }
        });

        db= openOrCreateDatabase(dbname, MODE_PRIVATE, null);
        db.execSQL("create table if not exists barang(kode varchar(20) primary key, nama varchar(100), satuan varchar(10), hb varchar(10), hj varchar(10), diskon varchar(10));");
        Cursor c = db.rawQuery("select * from barang",null);

        String kode11[]=new String[c.getCount()];
        String nama11[]=new String[c.getCount()];
        String satuan11[]=new String[c.getCount()];
        String hb11[]=new String[c.getCount()];
        String hj11[]=new String[c.getCount()];
        String diskon11[]=new String[c.getCount()];
        x = 1;

        if (c.getCount()>0){
            c.moveToFirst();
            kode11[0]=c.getString(c.getColumnIndex("kode"));
            nama11[0]=c.getString(c.getColumnIndex("nama"));
            satuan11[0]=c.getString(c.getColumnIndex("satuan"));
            hb11[0]=c.getString(c.getColumnIndex("hb"));
            hj11[0]=c.getString(c.getColumnIndex("hj"));
            diskon11[0]=c.getString(c.getColumnIndex("diskon"));
            while (c.moveToNext()){
                kode11[x]=c.getString(c.getColumnIndex("kode"));
                nama11[x]=c.getString(c.getColumnIndex("nama"));
                satuan11[x]=c.getString(c.getColumnIndex("satuan"));
                hb11[x]=c.getString(c.getColumnIndex("hb"));
                hj11[x]=c.getString(c.getColumnIndex("hj"));
                diskon11[x]=c.getString(c.getColumnIndex("diskon"));
                x = x+1;
            }
            db.close();
            Adapter yyy = new Adapter(getApplicationContext(),kode11,nama11,satuan11,hb11,hj11,diskon11);
            jisi.setAdapter(yyy);

        }else{
            db.close();
            Adapter yyy = new Adapter(getApplicationContext(),kode11,nama11,satuan11,hb11,hj11,diskon11);
            jisi.setAdapter(yyy);


        }

        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        clear.setOnClickListener(this);

        save.setEnabled(true);
        delete.setEnabled(false);
        update.setEnabled(false);
        clear.setEnabled(false);

    }

    @Override
    public void onClick(View v) {
        if (v == save){
            db= openOrCreateDatabase(dbname, MODE_PRIVATE, null);

            kd = kode.getText().toString();
            nm = nama.getText().toString();
            st = satuan.getText().toString();
            hb = hbeli.getText().toString();
            hj = hjual.getText().toString();
            dsk = diskon.getText().toString();
            Cursor d = db.rawQuery("select * from barang where kode='"+kd+"';",null);
            if (d.getCount()>0){
                Toast.makeText(getApplicationContext(),"Kode Sudah Terdaftar", Toast.LENGTH_LONG).show();
            }else if (kd == null){
                Toast.makeText(getApplicationContext(),"Kode Harus diisi", Toast.LENGTH_LONG).show();
            }else{
                db.execSQL("insert into barang values('"+kd+"','"+nm+"','"+st+"','"+hb+"','"+hj+"','"+dsk+"');");
                Cursor c = db.rawQuery("select * from barang",null);
                String kode11[]=new String[c.getCount()];
                String nama11[]=new String[c.getCount()];
                String satuan11[]=new String[c.getCount()];
                String hb11[]=new String[c.getCount()];
                String hj11[]=new String[c.getCount()];
                String diskon11[]=new String[c.getCount()];
                x = 1;
                c.moveToFirst();
                kode11[0]=c.getString(c.getColumnIndex("kode"));
                nama11[0]=c.getString(c.getColumnIndex("nama"));
                satuan11[0]=c.getString(c.getColumnIndex("satuan"));
                hb11[0]=c.getString(c.getColumnIndex("hb"));
                hj11[0]=c.getString(c.getColumnIndex("hj"));
                diskon11[0]=c.getString(c.getColumnIndex("diskon"));
                while (c.moveToNext()){
                    kode11[x]=c.getString(c.getColumnIndex("kode"));
                    nama11[x]=c.getString(c.getColumnIndex("nama"));
                    satuan11[x]=c.getString(c.getColumnIndex("satuan"));
                    hb11[x]=c.getString(c.getColumnIndex("hb"));
                    hj11[x]=c.getString(c.getColumnIndex("hj"));
                    diskon11[x]=c.getString(c.getColumnIndex("diskon"));
                    x = x+1;
                }
                db.close();
                Adapter yyy = new Adapter(getApplicationContext(),kode11,nama11,satuan11,hb11,hj11,diskon11);
                jisi.setAdapter(yyy);
                Toast.makeText(getApplicationContext(),"Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();

                kode.setText(null);
                nama.setText(null);
                satuan.setText(null);
                hbeli.setText(null);
                hjual.setText(null);
                diskon.setText(null);

                save.setEnabled(true);
                delete.setEnabled(false);
                update.setEnabled(false);
                clear.setEnabled(false);
            }


        }else if (v == delete){
            db= openOrCreateDatabase(dbname, MODE_PRIVATE, null);
            kd = kode.getText().toString();
            nm = nama.getText().toString();
            st = satuan.getText().toString();
            hb = hbeli.getText().toString();
            hj = hjual.getText().toString();
            dsk = diskon.getText().toString();
            db.execSQL("delete from barang where kode='"+kd+"';");
            Cursor c = db.rawQuery("select * from barang",null);
            String kode11[]=new String[c.getCount()];
            String nama11[]=new String[c.getCount()];
            String satuan11[]=new String[c.getCount()];
            String hb11[]=new String[c.getCount()];
            String hj11[]=new String[c.getCount()];
            String diskon11[]=new String[c.getCount()];
            x = 1;

            if (c.getCount()>0){
                c.moveToFirst();
                kode11[0]=c.getString(c.getColumnIndex("kode"));
                nama11[0]=c.getString(c.getColumnIndex("nama"));
                satuan11[0]=c.getString(c.getColumnIndex("satuan"));
                hb11[0]=c.getString(c.getColumnIndex("hb"));
                hj11[0]=c.getString(c.getColumnIndex("hj"));
                diskon11[0]=c.getString(c.getColumnIndex("diskon"));
                while (c.moveToNext()){
                    kode11[x]=c.getString(c.getColumnIndex("kode"));
                    nama11[x]=c.getString(c.getColumnIndex("nama"));
                    satuan11[x]=c.getString(c.getColumnIndex("satuan"));
                    hb11[x]=c.getString(c.getColumnIndex("hb"));
                    hj11[x]=c.getString(c.getColumnIndex("hj"));
                    diskon11[x]=c.getString(c.getColumnIndex("diskon"));
                    x = x+1;
                }

                db.close();
                Adapter yyy = new Adapter(getApplicationContext(),kode11,nama11,satuan11,hb11,hj11,diskon11);
                jisi.setAdapter(yyy);
                Toast.makeText(getApplicationContext(),"Data Terhapus", Toast.LENGTH_LONG).show();

                kode.setText(null);
                nama.setText(null);
                satuan.setText(null);
                hbeli.setText(null);
                hjual.setText(null);
                diskon.setText(null);

                save.setEnabled(true);
                delete.setEnabled(false);
                update.setEnabled(false);
                clear.setEnabled(false);
            }else{
                db.close();
                Adapter yyy = new Adapter(getApplicationContext(),kode11,nama11,satuan11,hb11,hj11,diskon11);
                jisi.setAdapter(yyy);
            }

        }else if (v == update){
            db= openOrCreateDatabase(dbname, MODE_PRIVATE, null);
            kd = kode.getText().toString();
            nm = nama.getText().toString();
            st = satuan.getText().toString();
            hb = hbeli.getText().toString();
            hj = hjual.getText().toString();
            dsk = diskon.getText().toString();
            db.execSQL("update barang set nama = '"+nm+"', satuan = '"+st+"', hb = '"+hb+"' , hj = '"+hj+"', diskon = '"+dsk+"' where kode='"+kd+"';");
            Cursor c = db.rawQuery("select * from barang",null);
            String kode11[]=new String[c.getCount()];
            String nama11[]=new String[c.getCount()];
            String satuan11[]=new String[c.getCount()];
            String hb11[]=new String[c.getCount()];
            String hj11[]=new String[c.getCount()];
            String diskon11[]=new String[c.getCount()];
            x = 1;
            c.moveToFirst();
            kode11[0]=c.getString(c.getColumnIndex("kode"));
            nama11[0]=c.getString(c.getColumnIndex("nama"));
            satuan11[0]=c.getString(c.getColumnIndex("satuan"));
            hb11[0]=c.getString(c.getColumnIndex("hb"));
            hj11[0]=c.getString(c.getColumnIndex("hj"));
            diskon11[0]=c.getString(c.getColumnIndex("diskon"));
            while (c.moveToNext()){
                kode11[x]=c.getString(c.getColumnIndex("kode"));
                nama11[x]=c.getString(c.getColumnIndex("nama"));
                satuan11[x]=c.getString(c.getColumnIndex("satuan"));
                hb11[x]=c.getString(c.getColumnIndex("hb"));
                hj11[x]=c.getString(c.getColumnIndex("hj"));
                diskon11[x]=c.getString(c.getColumnIndex("diskon"));
                x = x+1;
            }

            db.close();
            Adapter yyy = new Adapter(getApplicationContext(),kode11,nama11,satuan11,hb11,hj11,diskon11);
            jisi.setAdapter(yyy);
            Toast.makeText(getApplicationContext(),"Data terupdate", Toast.LENGTH_LONG).show();
        }else{
            kode.setText(null);
            nama.setText(null);
            satuan.setText(null);
            hbeli.setText(null);
            hjual.setText(null);
            diskon.setText(null);

            save.setEnabled(true);
            delete.setEnabled(false);
            update.setEnabled(false);
            clear.setEnabled(false);
        }
    }
}
