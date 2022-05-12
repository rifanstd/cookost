package com.example.cookost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class ProfileEdit extends AppCompatActivity {
    ImageView arrowBack;
    private EditText Username, Nama, Bio, Instagram,Facebook;
    private Button Save;

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
        String tmp = adapter.getContext().toString().trim();
        spinner.setAdapter(adapter);
        arrowBack = findViewById(R.id.backFromProfile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesToHome();
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void switchActivitiesToHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}