package com.example.cookost;

public class Food {
    private String NamaResep;
    private String Deskripsi;
    private String Bahan;
    private String Langkah;
    private String foto;
public Food(){

}
    public Food(String NamaResep, String deskripsi, String bahan, String langkah,String prioritas) {
        this.NamaResep = NamaResep;
        this.Deskripsi = deskripsi;
        this.Bahan = bahan;
        this.Langkah = langkah;
        this.foto = prioritas;

    }

    public String getNamaResep() {
        return NamaResep;
    }

    public void setNamaResep(String namaResep) {
        NamaResep = namaResep;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getBahan() {
        return Bahan;
    }

    public void setBahan(String bahan) {
        Bahan = bahan;
    }

    public String getLangkah() {
        return Langkah;
    }

    public void setLangkah(String langkah) {
        Langkah = langkah;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
