package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DeskripsiMakanan extends AppCompatActivity {
    private ImageView fotoMakanan;
    private ImageView fotoPengupload;
    private TextView nama_resep, deskripsi,bahan, langkah, namaPengupload, lokasiPengupload;
    ImageView arrowback;
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_makanan);
        fotoMakanan = findViewById(R.id.desc_foto_makanan);
        nama_resep = findViewById(R.id.desc_judul);
        deskripsi = findViewById(R.id.tv_deskripsi);
        bahan = findViewById(R.id.tv_bahan);
        langkah = findViewById(R.id.tv_langkah);


        Bundle covers = getIntent().getExtras();
        Bundle cover = getIntent().getExtras();
        String link = getIntent().getStringExtra("foto");
        int foto_makanan = covers.getInt("foto");
        setFoodIcon(link);
        String JUDUL = getIntent().getStringExtra("NamaResep");
        String DESC = getIntent().getStringExtra("Deskripsi");
        String BAHAN = getIntent().getStringExtra("Bahan");
        String LANGKAH = getIntent().getStringExtra("Langkah");



        nama_resep.setText(JUDUL);
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
    private void setFoodIcon(String iconUrl){
        Glide.with(this).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.kat_kere_hore)).into(fotoMakanan);
    }
}