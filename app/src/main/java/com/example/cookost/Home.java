package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ImageView sidebarIcon, profilePhoto;
    private Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sidebarIcon =  findViewById(R.id.icon_sidebar);
        profilePhoto = findViewById(R.id.profile_photo);
        toolbar = findViewById(R.id.cookost_toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new FragmentHome()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new FragmentHome();
                        break;
                    case R.id.nav_setting:
                        selectedFragment = new FragmentSetting();
                        break;
                    case R.id.nav_upload:
                        selectedFragment = new FragmentUpload();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedFragment).commit();
                return true;
            }
        });

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        rvQueen = findViewById(R.id.rv_data_kategori);
//        rvQueen.setHasFixedSize(true);
//        list.addAll(DataKategori.getListData());
//        showRecyclerList();
//        rvQueen.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        profilePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Home.this,"Clicked Profile",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//    private void showRecyclerList(){
//        rvQueen.setLayoutManager(new LinearLayoutManager(this));
//        DataKategoriAdapter KategoriAdapter = new DataKategoriAdapter(list);
//        rvQueen.setAdapter(KategoriAdapter);
//        KategoriAdapter.setOnItemClickCallback(new DataKategoriAdapter.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(SetGetKategori data) {
//
//            }
//        });
//    }

}