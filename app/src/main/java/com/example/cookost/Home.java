package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
    ImageView  profilePhoto;
    private Toolbar toolbar_home,toolbar_setting,toolbar_upload;
    BottomNavigationView bottomNavigationView;
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contextOfApplication = getApplicationContext();
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
        toolbar_home = findViewById(R.id.cookost_toolbar);
        setSupportActionBar(toolbar_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new FragmentHome()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
//                        toolbar_home = findViewById(R.id.cookost_toolbar);
//                        setSupportActionBar(toolbar_home);
                        selectedFragment = new FragmentHome();
                        break;
                    case R.id.nav_setting:
//                        toolbar_setting = findViewById(R.id.toolbar_setting);
//                        setSupportActionBar(toolbar_setting);
                        selectedFragment = new FragmentSetting();
                        break;
                    case R.id.nav_upload:
//                        toolbar_upload = findViewById(R.id.toolbarUpload);
//                        setSupportActionBar(toolbar_upload);
                        selectedFragment = new UploadFragment();
                        break;
                }
              
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedFragment).commit();
                return true;
            }
        });

    }

}