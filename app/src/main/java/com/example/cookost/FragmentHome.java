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
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private RecyclerView rvQueen;
    private ArrayList<SetGetKategori> list = new ArrayList<>();

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
        rvQueen = view.findViewById(R.id.rv_data_kategori);
        rvQueen.setHasFixedSize(true);
        list.addAll(DataKategori.getListData());
        showRecyclerList();
        rvQueen.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void showRecyclerList(){
        DataKategoriAdapter KategoriAdapter = new DataKategoriAdapter(list);
        rvQueen.setAdapter(KategoriAdapter);
        KategoriAdapter.setOnItemClickCallback(new DataKategoriAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SetGetKategori data) {

            }
        });
    }
}