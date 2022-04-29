package com.example.cookost;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private RecyclerView rvKategori,rvAkhirBulan;
    private ArrayList<SetGetKategori> listKategori = new ArrayList<>();
    private ArrayList<SetGetMakanan> listAkhirBulan = new ArrayList<>();
    ImageView profilePhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        rvKategori = view.findViewById(R.id.rv_data_kategori);
        rvKategori.setHasFixedSize(true);
        profilePhoto = view.findViewById(R.id.profile_photo);
        rvAkhirBulan = view.findViewById(R.id.rv_data_akhir_bulan);
        listAkhirBulan.addAll(DataMakanan.getListMakanan());

        listKategori.addAll(DataKategori.getListData());
        showRecyclerListKategori();
        showRecyclerListMakanan();

        rvKategori.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        rvAkhirBulan = view.findViewById(R.id.rv_data_akhir_bulan);
        rvAkhirBulan.setHasFixedSize(true);
        rvAkhirBulan.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void showRecyclerListKategori(){
        DataKategoriAdapter KategoriAdapter = new DataKategoriAdapter(listKategori);
        rvKategori.setAdapter(KategoriAdapter);
        KategoriAdapter.setOnItemClickCallback(new DataKategoriAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SetGetKategori data) {
                showSelectedKategori(data);
            }
        });
    }

    private void showRecyclerListMakanan(){
        DataMakananAdapter AkhirBulanAdapter = new DataMakananAdapter(listAkhirBulan);
        rvAkhirBulan.setAdapter(AkhirBulanAdapter);
        AkhirBulanAdapter.setOnItemClickCallback(new DataMakananAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SetGetMakanan data) {
                showSelectedMakanan(data);
            }
        });
    }
    private void showSelectedKategori(SetGetKategori data) {
//        Intent i = new Intent(getContext(), DetailKategori.class);
//        i.putExtra("foto_kategori", data.getFotoKategori());
//        i.putExtra("judul_kategori",data.getNamaKategori());
//        i.putExtra("id",data.getNamaKategori());
//        startActivity(i);
    }
    private void showSelectedMakanan(SetGetMakanan data) {
        Intent i = new Intent(getContext(), DeskripsiMakanan.class);
        i.putExtra("foto_makanan", data.getFotoMakanan());
        i.putExtra("judul",data.getNamaMakanan());
        i.putExtra("foto_pengupload",data.getFotoProfil());
        i.putExtra("nama_pengupload",data.getPenguploadMakanan());
        i.putExtra("lokasi_pengupload",data.getLokasiPenguploadMakanan());
        i.putExtra("deskripsi",data.getDeskripsiMakanan());
        i.putExtra("bahan",data.getBahanMakanan());
        i.putExtra("langkah", data.getLangkahMakanan());
        startActivity(i);
    }

}