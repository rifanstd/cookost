package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(MainActivity.this, Login.class);
//                startActivity(i);
//                finish();
//            }
//        },3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            startActivity(new Intent(this,Home.class));
            finish();
        }
        else{
            startActivity(new Intent(this,Login.class));
            finish();
        }
    }
}