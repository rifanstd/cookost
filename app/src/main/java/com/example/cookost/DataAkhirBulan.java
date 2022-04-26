package com.example.cookost;

import java.util.ArrayList;

public class DataAkhirBulan {
        private static int[] FotoAkhirBulan ={
                R.drawable.indomie_telur_kornet,
                R.drawable.nasi_goreng_telur,
                R.drawable.scramble_egg,
                R.drawable.pizza_mie
        };
        private static String[] NamaAkhirBulan ={
                "Indome Telur Kornet",
                "Nasi Goreng Telur",
                "Scramble Egg",
                "Pizza MIe"
        };
    static ArrayList<SetGetAkhirBulan>getListAkhirBulan(){
        ArrayList<SetGetAkhirBulan> list = new ArrayList<>();
        for (int i = 0; i < NamaAkhirBulan.length; i++) {
            SetGetAkhirBulan akhirBulan = new SetGetAkhirBulan();
            akhirBulan.setNamaAkhirBulan(NamaAkhirBulan[i]);
            akhirBulan.setFotoAkhirBulan(FotoAkhirBulan[i]);
            list.add(akhirBulan);
        }
        return list;
    }
}
