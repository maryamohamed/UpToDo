package com.example.iti_project.User_Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iti_project.R;

public class App_Settings extends AppCompatActivity {
    // variables
    Button btn_back, btn_change_app_color, btn_change_app_typography, btn_change_app_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        // inflate
        btn_back = findViewById(R.id.btn_back);
//        btn_change_app_color = findViewById(R.id.btn_change_app_color);
//        btn_change_app_typography = findViewById(R.id.btn_change_app_typography);
//        btn_change_app_language = findViewById(R.id.btn_change_app_language);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_app_settings_to_profile = new Intent(App_Settings.this,Profile.class);
                startActivity(intent_app_settings_to_profile);
            }
        });

//        btn_change_app_color.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btn_change_app_typography.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btn_change_app_language.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
  }
}