package com.example.cookost;

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

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private RecyclerView rvKategori,rvAkhirBulan;
    private ArrayList<SetGetKategori> listKategori = new ArrayList<>();
    private ArrayList<SetGetAkhirBulan> listAkhirBulan = new ArrayList<>();


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
//        rvQueen = getActivity().findViewById(R.id.rv_data_kategori);
        rvKategori = view.findViewById(R.id.rv_data_kategori);
        rvKategori.setHasFixedSize(true);
        rvAkhirBulan = view.findViewById(R.id.rv_data_akhir_bulan);
        rvAkhirBulan.setHasFixedSize(true);
//        listAkhirBulan.addAll(DataAkhirBulan.getListAkhirBulan());
        listAkhirBulan.addAll(DataAkhirBulan.getListAkhirBulan());
        listKategori.addAll(DataKategori.getListData());
        showRecyclerListKategori();
        showRecyclerListAkhirBulan();
        rvKategori.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        rvAkhirBulan.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void showRecyclerListKategori(){
        DataKategoriAdapter KategoriAdapter = new DataKategoriAdapter(listKategori);
        rvKategori.setAdapter(KategoriAdapter);
        KategoriAdapter.setOnItemClickCallback(new DataKategoriAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SetGetKategori data) {
            }
        });
    }
    private void showRecyclerListAkhirBulan(){
        DataAkhirBulanAdapter AkhirBulanAdapter = new DataAkhirBulanAdapter(listAkhirBulan);
        rvAkhirBulan.setAdapter(AkhirBulanAdapter);
        AkhirBulanAdapter.setOnItemClickCallback(new DataAkhirBulanAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SetGetAkhirBulan data) {

            }
        });
    }
}