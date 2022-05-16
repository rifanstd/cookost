package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileEdit extends AppCompatActivity {
    ImageView arrowBack;
    private EditText Username, Nama, Bio, Instagram,Facebook;
    private Button Save;
    private FirebaseAuth authProfile ;
    private ImageView img;
    private String userID;
    private Button pilihFoto;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;
    private Uri image_uri;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        storageReference = FirebaseStorage.getInstance().getReference();
        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();
        Username = (EditText) findViewById(R.id.edt_username);
        img = (ImageView) findViewById(R.id.image) ;
        pilihFoto = (Button) findViewById(R.id.btn_pick_foto) ;
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


        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
        StorageReference profileRef = storageReference.child("Users/"+authProfile.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(firebaseUser);
            }
        });
        pilihFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
//                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                        requestPermissions(getActivity(),String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISSION_CODE);
                    }
                    else{
                        pickFromGallery();
                    }
                }
                else {
                    pickFromGallery();
                }
            }
        });


    }

    private void pickFromGallery() {
        Intent intent =  new Intent (Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == IMAGE_PICK_CODE){
                image_uri = data.getData();
               // img.setImageURI(image_uri);
                uploadImageToFirebase(image_uri);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadImageToFirebase(Uri image_uri) {
        StorageReference fileRef = storageReference.child("Users/"+authProfile.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ProfileEdit.this,"Foto Terupload",Toast.LENGTH_LONG).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(img);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileEdit.this,"Foto Tidak Terupload",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickFromGallery();
                }
                else{
                    Toast.makeText(this,"Dibutuhkan Izin Akses",Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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