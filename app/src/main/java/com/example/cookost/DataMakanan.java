package com.example.cookost;

import java.util.ArrayList;

public class DataMakanan {
    private static  String[] KategoriMakanan={
            "Makanan Hangat",
            "Makanan Ringan",
            "Kere Hore",
            "Kere Hore"
    };
    private static int[] fotoProfil={
            R.drawable.rifan,
            R.drawable.rifan,
            R.drawable.rifan,
            R.drawable.rifan
    };
        private static  String[] IdPenguploadMakanan={
                "@RifanSTD",
                "@LeleTerbangs",
                "@BagusTI",
                "@randomS"
        };
    private static  String[] LokasiPenguploadMakanan={
            "Bandar Lampung",
            "Bandar Lampung",
            "Jakarta",
            "None"
    };
        private static int[] FotoMakanan ={
                R.drawable.indomie_telur_kornet,
                R.drawable.nasi_goreng_telur,
                R.drawable.scramble_egg,
                R.drawable.pizza_mie
        };
        private static  String [] desc={
                "AAAA",
                "BBBBB",
                "CCCCC",
                "DDDDD"
    };
        private static String[] NamaMakanan ={
                "Indome Telur Kornet",
                "Nasi Goreng Telur",
                "Scramble Egg",
                "Pizza MIe"
        };
        private static String[] langkahMakanan={
                "Panaskan Mentega kemudian Masukan Bawang Merah & Bawang Putih lalu Masukan Kornet\n" +
                        "Panaskan Air masukan Telur, rebus telurnya setelah matang angkat telurnya\n" +
                        "Panaskan Air masak Mie Indomienya\n" +
                        "Siapkan Mangkuk yang sudah diisi bumbu indomienya, Masukan Mie nya aduk sampai Rata kemudian hias Mie nya agar tampil menarik\n" +
                        "Tambahkan Selada, Tomat, Telur dan Kornet diatas Mie nya\n" +
                        "IndoMie Goreng bisa dinikmati",
                "b","c","d"
        };
        private static String[] BahanMakanan={
                "Indomie Goreng\n" +
                        "Bawang Putih & Bawang Merah\n" +
                        "Kornet\n" +
                        "Telor\n" +
                        "Hiasan bisa sayur, kacang polong, tomat,dll.",
                "b","c","d"
        };
    static ArrayList<SetGetMakanan> getListMakanan(){
        ArrayList<SetGetMakanan> list = new ArrayList<>();
        for (int i = 0; i < NamaMakanan.length; i++) {
            SetGetMakanan Makanan = new SetGetMakanan();
            Makanan.setNamaMakanan(NamaMakanan[i]);
            Makanan.setFotoMakanan(FotoMakanan[i]);
            Makanan.setLangkahMakanan(langkahMakanan[i]);
            Makanan.setBahanMakanan(BahanMakanan[i]);
            Makanan.setKategoriMakanan(KategoriMakanan[i]);
            Makanan.setLokasiPenguploadMakanan(LokasiPenguploadMakanan[i]);
            Makanan.setPenguploadMakanan(IdPenguploadMakanan[i]);
            Makanan.setFotoProfil(fotoProfil[i]);
            Makanan.setDeskripsiMakanan(desc[i]);
            list.add(Makanan);
        }
        return list;
    }
}
