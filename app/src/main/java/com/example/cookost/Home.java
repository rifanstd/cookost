package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ImageView sidebarIcon, profilePhoto;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sidebarIcon =  findViewById(R.id.icon_sidebar);
        profilePhoto = findViewById(R.id.profile_photo);
        toolbar = findViewById(R.id.cookost_toolbar);
        setSupportActionBar(toolbar);
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this,"Clicked Profile",Toast.LENGTH_SHORT).show();
            }
        });
    }

}