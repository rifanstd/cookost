package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    ImageView arrowBack;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TextView TVusername, TVNama,TVWho,TVBio,TVInstagram, TVFacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TVusername = (TextView) findViewById(R.id.edt_username);
        TVNama = (TextView) findViewById(R.id.edt_name);
        TVWho = (TextView) findViewById(R.id.spinner_who);
        TVBio = (TextView) findViewById(R.id.edt_bio);
        TVInstagram = (TextView) findViewById(R.id.edt_instagram);
        TVFacebook = (TextView) findViewById(R.id.edt_facebook)  ;
        arrowBack = findViewById(R.id.backFromProfile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://cookost-5cd89-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String Username = userProfile.username;
                    String Nama = userProfile.nama;
                    String Bio = userProfile.bio;
                    String Facebook = userProfile.facebook;
                    String Instagram = userProfile.instagram;
                    String Who = userProfile.who;
                    TVusername.setText(Username);
                    TVNama.setText(Nama);
                    TVBio.setText(Bio);
                    TVWho.setText(Who);
                    TVFacebook.setText(Facebook);
                    TVInstagram.setText(Instagram);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this,"Terdapat Kesalahan, Mohon Coba lagi",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void switchActivitiesToHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}