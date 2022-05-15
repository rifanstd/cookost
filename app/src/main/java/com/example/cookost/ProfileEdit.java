package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileEdit extends AppCompatActivity {
    ImageView arrowBack;
    private EditText Username, Nama, Bio, Instagram,Facebook;
    private Button Save;
    private FirebaseAuth authProfile ;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        Username = (EditText) findViewById(R.id.edt_username);
        Nama = (EditText) findViewById(R.id.edt_name);
        Bio = (EditText) findViewById(R.id.edt_bio);
        Instagram = (EditText) findViewById(R.id.edt_instagram);
        Facebook = (EditText) findViewById(R.id.edt_facebook);
        Save = (Button) findViewById(R.id.btn_save);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_who);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_who, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        arrowBack = findViewById(R.id.backFromProfile);
        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(firebaseUser);
            }
        });


    }
    public void updateProfile(FirebaseUser firebaseUser){
        String new_username = Username.getText().toString();
        String new_Nama = Nama.getText().toString();
        String new_Bio = Bio.getText().toString();
        String new_Instagram = Instagram.getText().toString();
        String new_Facebook = Facebook.getText().toString();
        User u = new User (new_username,new_Nama,"Test",new_Bio,new_Instagram,new_Facebook);
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance("https://cookost-5cd89-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        String UserID =  firebaseUser.getUid();
        referenceProfile.child(UserID).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(new_username).build();
                    firebaseUser.updateProfile(profileUpdates);
                    Toast.makeText(ProfileEdit.this,"Update Profile Berhasil",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void switchActivitiesToHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}