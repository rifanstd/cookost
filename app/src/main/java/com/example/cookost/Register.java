package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText newEmail,newPassword,username;
    private Button register;
    private TextView sudahPunyaAkun;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        newEmail = (EditText) findViewById(R.id.edt_new_email);
        newPassword = (EditText) findViewById(R.id.edt_new_password);
        username = (EditText) findViewById(R.id.edt_username);
        register = (Button) findViewById(R.id.btn_register);
        sudahPunyaAkun = (TextView) findViewById(R.id.tv_sudah_punya_akun);
        mAuth =  FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToLogin();
            }
        });
    }
    private void registerUser(){
        String new_email = newEmail.getText().toString();
        String new_password = newPassword.getText().toString();
        String Username = username.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(new_email).matches()){
            newEmail.setError("Masukan Email Anda");
            newEmail.requestFocus();
            return;
        }
        if(new_password.isEmpty()){
            newPassword.setError("Masukan Password Anda");
            newPassword.requestFocus();
            return;
        }
        if(Username.isEmpty()){
            username.setError("Masukan username Anda");
            username.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(new_email,new_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    DatabaseReference databaseReference;
                    User usera = new User(Username, new_email);
                    firebaseDatabase = FirebaseDatabase.getInstance("https://cookost-5cd89-default-rtdb.asia-southeast1.firebasedatabase.app");
                    firebaseDatabase.getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(usera).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            System.out.println("AMPE SINI WEEH");
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this,"Sudah Teregistrasi",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                switchActivitiesToHome();
                            }
                            else{
                                Toast.makeText(Register.this,"Gagal Teregistrasi",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Register.this,"Gagal Teregistrasi 2",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });

    }
    private void switchActivitiesToLogin() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    private void switchActivitiesToHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

}