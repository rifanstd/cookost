package com.example.cookost;

import java.util.ArrayList;

public class DataKategori {
    private static int[] FotoKategori ={
            R.drawable.kat_makanan_hangat,
            R.drawable.kat_makanan_ringan,
            R.drawable.kat_kere_hore
    };

    private static String[] NamaKategori ={
            "Makanan Hangat",
            "Makanan Ringan",
            "Kere Hore"
    };
    static ArrayList<SetGetKategori>getListData(){
        ArrayList<SetGetKategori> list = new ArrayList<>();
        for (int i = 0; i < NamaKategori.length; i++) {
            SetGetKategori kategori = new SetGetKategori();
            kategori.setNamaKategori(NamaKategori[i]);
            kategori.setFotoKategori(FotoKategori[i]);
            list.add(kategori);
        }
        return list;
    }
}
