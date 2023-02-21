package com.example.iti_project.Introduction;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iti_project.R;

import java.util.Timer;
import java.util.TimerTask;

public class Start_Intro extends AppCompatActivity {
Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_intro);
         timer=new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 Intent i=new Intent(Start_Intro.this, SlideActivity.class);
                 startActivity(i);
                 finish();

             }

         },2000);

    }
}