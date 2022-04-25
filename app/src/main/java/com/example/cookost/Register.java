package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    private EditText newEmail,newPassword,username;
    private Button register;
    private TextView sudahPunyaAkun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        newEmail = (EditText) findViewById(R.id.edt_new_email);
        newPassword = (EditText) findViewById(R.id.edt_new_password);
        username = (EditText) findViewById(R.id.edt_username);
        register = (Button) findViewById(R.id.btn_register);
        sudahPunyaAkun = (TextView) findViewById(R.id.tv_sudah_punya_akun);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToLogin();
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