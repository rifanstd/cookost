package com.example.cookost;

public class User {
    public String username, email,nama,who,bio,instagram,facebook;
    public User(){

    }
    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.nama = "";
        this.who = "";
        this.bio = "";
        this.instagram = "";
        this.facebook = "";

    }
    public User (String username, String nama,String who,String bio,String instagram,String facebook){
        this.username = username;
        this.email = email;
        this.nama = nama;
        this.who = who;
        this.bio = bio;
        this.instagram = instagram;
        this.facebook = facebook;
    }
}
