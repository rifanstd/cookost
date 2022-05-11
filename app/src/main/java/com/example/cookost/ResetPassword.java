package com.example.cookost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ResetPassword extends AppCompatActivity {
    private EditText emailPemulihan;
    private Button kirim;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        emailPemulihan = (EditText) findViewById(R.id.edt_new_pass);
        kirim = (Button) findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirimEmail();
            }
        });
    }

    private void kirimEmail() {
        String email = emailPemulihan.getText().toString().trim();
        if(email.isEmpty()){
            emailPemulihan.setError("Mohon Isi Email Anda");
            emailPemulihan.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailPemulihan.setError("Mohon Isi Email Anda Dengan Benar");
            emailPemulihan.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPassword.this,"Cek Email Anda",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ResetPassword.this,"Email yang anda masukan tidak terdaftar ditempat kami",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}