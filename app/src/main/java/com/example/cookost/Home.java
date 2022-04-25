package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ImageView sidebarIcon, profilePhoto;
    private Toolbar toolbar;
    private RecyclerView rvQueen;
    private ArrayList<SetGetKategori> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sidebarIcon =  findViewById(R.id.icon_sidebar);
        profilePhoto = findViewById(R.id.profile_photo);
        toolbar = findViewById(R.id.cookost_toolbar);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        rvQueen = findViewById(R.id.rv_data_kategori);
        rvQueen.setHasFixedSize(true);
        list.addAll(DataKategori.getListData());
        showRecyclerList();
        rvQueen.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this,"Clicked Profile",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showRecyclerList(){
        rvQueen.setLayoutManager(new LinearLayoutManager(this));
        DataKategoriAdapter KategoriAdapter = new DataKategoriAdapter(list);
        rvQueen.setAdapter(KategoriAdapter);
        KategoriAdapter.setOnItemClickCallback(new DataKategoriAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SetGetKategori data) {

            }
        });
    }

}