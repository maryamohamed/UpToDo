package com.example.iti_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.iti_project.Add_Task.Database;
import com.example.iti_project.Add_Task.DialogCloseListener;
import com.example.iti_project.Add_Task.Fragment_create_task;
import com.example.iti_project.Add_Task.TASK;
import com.example.iti_project.Add_Task.Task_Adapter;
import com.example.iti_project.User_Profile.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DialogCloseListener {

    // variables
    BottomNavigationView bottomNavigationView;
    ImageView iv_person;

    RecyclerView rv_tasks;
    private Database database;
    private Task_Adapter task_adapter;
    private ArrayList<TASK> tasks;
    RecyclerView.Adapter adapter;
    SearchView search_bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // inflate
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        search_bar = findViewById(R.id.search_bar);
        iv_person = findViewById(R.id.iv_person
        );
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) Home.this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        database = new Database(this);
        database.write_into_database();

        // inflate
        rv_tasks = findViewById(R.id.rv_tasks);
        rv_tasks.setLayoutManager(new LinearLayoutManager(this));
        task_adapter = new Task_Adapter(database,Home.this);
        rv_tasks.setHasFixedSize(true);

        adapter = new Task_Adapter(tasks, Home.this);
        rv_tasks.setAdapter(task_adapter);

        tasks = database.get_all_tasks();
        Collections.reverse(tasks);

        task_adapter.set_tasks(tasks);

        iv_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Home.this, Profile.class);
                startActivity(in);
            }
        });

//        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ArrayList<TASK> suggest = new ArrayList<>();
//                for (String search :) {
//                    if (search.toLowerCase().contains(search_bar.getQuery().toString()) {
//                        suggest.add(search);
//                    }
//                }
//                search_bar.getSuggestionsAdapter(search_bar);
//                return false;
//            }
//        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        tasks = database.get_all_tasks();
        Collections.reverse(tasks);
        task_adapter.set_tasks(tasks);
        task_adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.celandar:
                Fragment_calender_view fragment_calender_view = new Fragment_calender_view();
                fragment_calender_view.show(getSupportFragmentManager(),"TAG");
                return true;
            case R.id.plus:
                Fragment_create_task.newInstance().show(getSupportFragmentManager(), Fragment_create_task.TAG);
                return true;
            case R.id.pomodoro:
                Intent intent_home_to_pomodoro = new Intent(Home.this, Pomodoro.class);
                startActivity(intent_home_to_pomodoro);
                return true;
            case R.id.profile:
                Intent intent_home_to_profile = new Intent(Home.this, Profile.class);
                startActivity(intent_home_to_profile);
                return true;
        }
        return false;
    }
}