package com.example.iti_project.User_Profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.iti_project.User_Profile.fragments_faq.Fragment_q1;
import com.example.iti_project.User_Profile.fragments_faq.Fragment_q2;
import com.example.iti_project.User_Profile.fragments_faq.Fragment_q3;
import com.example.iti_project.User_Profile.fragments_faq.Fragment_q4;
import com.example.iti_project.User_Profile.fragments_faq.Fragment_q5;
import com.example.iti_project.R;

public class FAQ extends AppCompatActivity {

    // variables
    Button btn_q1, btn_q2, btn_q3, btn_q4, btn_q5;
    FrameLayout frame_layout_q1, frame_layout_q2, frame_layout_q3, frame_layout_q4, frame_layout_q5;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        // inflate
        btn_q1 = findViewById(R.id.btn_q1);
        btn_q2 = findViewById(R.id.btn_q2);
        btn_q3 = findViewById(R.id.btn_q3);
        btn_q4 = findViewById(R.id.btn_q4);
        btn_q5 = findViewById(R.id.btn_q5);
        frame_layout_q1 = findViewById(R.id.frame_layout_q1);
        frame_layout_q2 = findViewById(R.id.frame_layout_q2);
        frame_layout_q3 = findViewById(R.id.frame_layout_q3);
        frame_layout_q4 = findViewById(R.id.frame_layout_q4);
        frame_layout_q5 = findViewById(R.id.frame_layout_q5);

        btn_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_q1 fragment_q1 = new Fragment_q1();
                fragmentTransaction.replace(R.id.frame_layout_q1,fragment_q1);
                fragmentTransaction.commit();
            }
        });

        btn_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_q2 fragment_q2 = new Fragment_q2();
                fragmentTransaction.replace(R.id.frame_layout_q2,fragment_q2);
                fragmentTransaction.commit();
            }
        });

        btn_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_q3 fragment_q3 = new Fragment_q3();
                fragmentTransaction.replace(R.id.frame_layout_q3,fragment_q3);
                fragmentTransaction.commit();
            }
        });

        btn_q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_q4 fragment_q4 = new Fragment_q4();
                fragmentTransaction.replace(R.id.frame_layout_q4,fragment_q4);
                fragmentTransaction.commit();
            }
        });

        btn_q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_q5 fragment_q5 = new Fragment_q5();
                fragmentTransaction.replace(R.id.frame_layout_q5,fragment_q5);
                fragmentTransaction.commit();
            }
        });
    }
}