package com.example.iti_project.Introduction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iti_project.FingerPrint.FingerPrint;
import com.example.iti_project.Login_Register.Login;
import com.example.iti_project.Login_Register.Register;
import com.example.iti_project.R;

public class Last_Intro extends AppCompatActivity {
TextView intro_back_arrow;
Button last_intro_fingerprint_login,last_intro_password_login,last_intro_create_account;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_intro);
        intro_back_arrow=findViewById(R.id.last_intro_back_arrow);
        last_intro_fingerprint_login=findViewById(R.id.last_intro_btn_fingerprint_login);
        last_intro_password_login=findViewById(R.id.last_intro_btn_password_login);
        last_intro_create_account=findViewById(R.id.last_intro_btn_create_account);

        // return back to third activity
        intro_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Last_Intro.this,
                        SlideActivity.class);
                startActivity(i);
            }
        });

         // moving to fingerprint activity
        last_intro_fingerprint_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Last_Intro.this, FingerPrint.class);
                startActivity(i);
            }
        });

        // moving to login activity
        last_intro_password_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Last_Intro.this, Login.class);
                startActivity(i);
            }
        });

        // moving to register activity
        last_intro_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Last_Intro.this, Register.class);
                startActivity(i);
            }
        });
    }
}