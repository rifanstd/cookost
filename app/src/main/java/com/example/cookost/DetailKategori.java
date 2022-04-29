package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailKategori extends AppCompatActivity {
    private ImageView fotoMakanan;
    private ImageView fotoPengupload;
    private RecyclerView rvKategori,rvAkhirBulan;
    private ArrayList<SetGetKategori> listKategori = new ArrayList<>();
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();
    private TextView judul, deskripsi,bahan, langkah, namaPengupload, lokasiPengupload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);
        rvAkhirBulan = findViewById(R.id.rv_data_akhir_bulan);
        rvAkhirBulan.setHasFixedSize(true);
        rvAkhirBulan.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }
}