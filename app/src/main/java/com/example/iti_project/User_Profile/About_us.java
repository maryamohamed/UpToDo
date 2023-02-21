package com.example.iti_project.User_Profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.iti_project.User_Profile.fragments_about_us.fragment_about_1;
import com.example.iti_project.User_Profile.fragments_about_us.fragment_about_2;
import com.example.iti_project.User_Profile.fragments_about_us.fragment_about_3;
import com.example.iti_project.User_Profile.fragments_about_us.fragment_about_4;
import com.example.iti_project.R;

public class About_us extends AppCompatActivity {

    // Variables
    Button btn_1, btn_2, btn_3, btn_4;
    FrameLayout frame_layout_about_us;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // inflate
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        frame_layout_about_us = findViewById(R.id.frame_layout_about_us);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_about_1 fragment_about_1 = new fragment_about_1();
                fragmentTransaction.replace(R.id.frame_layout_about_us,fragment_about_1);
                fragmentTransaction.commit();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_about_2 fragment_about_2 = new fragment_about_2();
                fragmentTransaction.replace(R.id.frame_layout_about_us,fragment_about_2);
                fragmentTransaction.commit();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_about_3 fragment_about_3 = new fragment_about_3();
                fragmentTransaction.replace(R.id.frame_layout_about_us,fragment_about_3);
                fragmentTransaction.commit();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_about_4 fragment_about_4 = new fragment_about_4();
                fragmentTransaction.replace(R.id.frame_layout_about_us,fragment_about_4);
                fragmentTransaction.commit();
            }
        });
    }
}