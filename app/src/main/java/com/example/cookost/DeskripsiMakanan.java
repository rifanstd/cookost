package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeskripsiMakanan extends AppCompatActivity {
    private ImageView fotoMakanan;
    private ImageView fotoPengupload;
    private TextView judul, deskripsi,bahan, langkah, namaPengupload, lokasiPengupload;
    ImageView arrowback;
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_makanan);
        fotoMakanan = findViewById(R.id.desc_foto_makanan);
        fotoPengupload = findViewById(R.id.img_profil);
        judul = findViewById(R.id.desc_judul);
        deskripsi = findViewById(R.id.tv_deskripsi);
        bahan = findViewById(R.id.tv_bahan);
        langkah = findViewById(R.id.tv_langkah);
        namaPengupload = findViewById(R.id.desc_tv_id_pengupload);
        lokasiPengupload = findViewById(R.id.desc_tv_id_lokasi);

        Bundle covers = getIntent().getExtras();
        Bundle cover = getIntent().getExtras();
        int foto_makanan = covers.getInt("foto_makanan");
        int foto_profil = cover.getInt("foto_pengupload");
        String JUDUL = getIntent().getStringExtra("judul");
        String DESC = getIntent().getStringExtra("deskripsi");
        String NAMA_PENGUPLOAD = getIntent().getStringExtra("nama_pengupload");
        String LOKASI_PENGUPLOAD = getIntent().getStringExtra("lokasi_pengupload");
        String BAHAN = getIntent().getStringExtra("bahan");
        String LANGKAH = getIntent().getStringExtra("langkah");

        fotoMakanan.setImageResource(foto_makanan);
        fotoPengupload.setImageResource(foto_profil);
        namaPengupload.setText(NAMA_PENGUPLOAD);
        lokasiPengupload.setText(LOKASI_PENGUPLOAD);
        judul.setText(JUDUL);
        deskripsi.setText(DESC);
        bahan.setText(BAHAN);
        langkah.setText(LANGKAH);
        arrowback = findViewById(R.id.ArrowBack);
        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
    }
    private void switchActivitiesToHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}