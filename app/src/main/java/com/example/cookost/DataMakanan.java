package com.example.cookost;

import java.util.ArrayList;

public class DataMakanan {
    ArrayList<Integer> asw = new ArrayList<Integer>();

    private static int[] IdKategoriMakanan={
            1,
            2,
            3,
            3
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
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
                "Nasi Goreng Telur Sederhana adalah salah satu varian nasi goreng yang paling banyak disukai karena mudah dibuat dan tidak memakan banyak waktu, sehingga cocok untuk dijadikan menu untuk sarapan.",
                "Scrambled Egg merupakan olahan telur yang sangat digemari banyak orang. Memiliki rasa yang gurih, tekstur yang lembut, dan sangat mudah dibuat di rumah. Cocok sebagai lauk untuk sarapan.",
                "Salah satu cara mudah membuat kreasi dari mi goreng instan adalah dengan menjadikannya \"pizza\"."
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
                        "IndoMie Goreng bisa dinikmati", "Panaskan minyak goreng, tumis bawang merah dan bawang putih hingga harum.\n" +
                "\n" +
                "Masukkan telur, nasi putih, kacang polong, kecap asin, kaldu ayam, dan merica, aduk hingga rata.\n" +
                "\n" +
                "Masak dengan api besar hingga nasi berasap. Angkat, sajikan segera.","Campurkan telur, susu cair, garam dan merica. Kocok menggunakan whisk hingga rata.\n" +
                "\n" +
                "Lelehkan mentega dalam wajan, tuangkan telur. Aduk rata secara perlahan hingga menjadi orak-arik. Angkat, tuang ke piring saji. Beri peterseli sebagai garnish. Sajikan.","1. Rebus mi sampai setengah matang. Tiriskan.\n" +
                "\n" +
                "2. Campurkan bumbu mi, cabai, dan seledri dengan telur. Kocok lepas sampai semua bahan tercampur rata.\n" +
                "\n" +
                "3. Tuang mi ke dalam telur. Aduk rata.\n" +
                "\n" +
                "4. Panaskan margarin di teflon dengan api kecil.\n" +
                "\n" +
                "5. Tuang mi. Tutup teflon selama kurang lebih tiga menit. Lalu balik dan tutup kembali selama sekitar tiga menit.\n" +
                "\n" +
                "6. Oles bagian atas dengan saus bolognese. Angkat dan sajikan dengan keju parut sesuai selera."
        };
        private static String[] BahanMakanan={
                "Indomie Goreng\n" +
                        "Bawang Putih & Bawang Merah\n" +
                        "Kornet\n" +
                        "Telor\n" +
                        "Hiasan bisa sayur, kacang polong, tomat,dll.",
                "2 sdm minyak goreng\n" +
                        "2 butir bawang merah, haluskan\n" +
                        "3 siung bawang putih, haluskan\n" +
                        "4 butir telur, masak orak-arik\n" +
                        "500 g nasi putih\n" +
                        "50 g kacang polong\n" +
                        "2 sdm kecap asin\n" +
                        "½ sdt kaldu ayam bubuk\n" +
                        "¼ sdt merica bubuk","50 g mentega\n" +
                "6 butir telur\n" +
                "2 sdm susu cair\n" +
                "½ sdt garam\n" +
                "¼ sdt merica bubuk","2 bungkus mi goreng instan\n" +
                "2 butir telur ayam\n" +
                "2 sdm saus bolognese\n" +
                "1 sdm margarin\n" +
                "3 buah cabai rawit, iris tipis\n" +
                "1 batang seledri, iris tipis\n" +
                "keju parut sesuai selera"
        };
    static ArrayList<SetGetMakanan> getListMakanan(){
        ArrayList<SetGetMakanan> list = new ArrayList<>();
        for (int i = 0; i < NamaMakanan.length; i++) {
            SetGetMakanan Makanan = new SetGetMakanan();
            Makanan.setNamaMakanan(NamaMakanan[i]);
            Makanan.setFotoMakanan(FotoMakanan[i]);
            Makanan.setLangkahMakanan(langkahMakanan[i]);
            Makanan.setBahanMakanan(BahanMakanan[i]);
            Makanan.setIdKategoriMakanan(IdKategoriMakanan[i]);
            Makanan.setLokasiPenguploadMakanan(LokasiPenguploadMakanan[i]);
            Makanan.setPenguploadMakanan(IdPenguploadMakanan[i]);
            Makanan.setFotoProfil(fotoProfil[i]);
            Makanan.setDeskripsiMakanan(desc[i]);
            list.add(Makanan);
        }
        return list;
    }
    public void AddMakanan(String NamaMakanan, String Deskripsi, String Bahan, String Langkah) {
        SetGetMakanan Makanan = new SetGetMakanan();
        Makanan.setIdKategoriMakanan(1);
        Makanan.setFotoMakanan(R.drawable.rifan);
        Makanan.setFotoProfil(R.drawable.rifan);
        Makanan.setNamaMakanan(NamaMakanan);
        Makanan.setDeskripsiMakanan(Deskripsi);
        Makanan.setBahanMakanan(Bahan);
        Makanan.setLangkahMakanan(Langkah);
        Makanan.setPenguploadMakanan("RIFAN");
        Makanan.setLokasiPenguploadMakanan("Bandar Lampung");
    }
    static ArrayList<SetGetMakanan> setListMakanan(){
        ArrayList<SetGetMakanan> list = new ArrayList<>();
     return list;
    }

}
