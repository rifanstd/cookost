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

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private Button login;
    private TextView lupaPasword,belumPunyaAkun;
    private EditText  email, password;
    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btn_login);
        lupaPasword = (TextView) findViewById(R.id.tv_lupa_password);
        belumPunyaAkun = (TextView) findViewById(R.id.tv_belum_punya_akun);
        email = (EditText) findViewById(R.id.edt_email);
        password = (EditText) findViewById(R.id.edt_password);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        belumPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToRegister();
            }
        });
        lupaPasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPass();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void resetPass() {
        Intent i = new Intent(this, ResetPassword.class);
        startActivity(i);
    }


    private void userLogin() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if(Email.isEmpty()){
            email.setError("Masukan Email Anda");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Masukan Email Valid Anda");
            email.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Masukan Password Anda");
            password.requestFocus();
            return;
        }
        if(Password.length()<6){
            password.setError("Password Minimal 6 Karakter");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    switchActivitiesToHome();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(Login.this,"Akun Tidak Tersedia",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    private void switchActivitiesToHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

    private void switchActivitiesToRegister() {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}