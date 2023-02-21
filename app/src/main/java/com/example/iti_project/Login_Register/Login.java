package com.example.iti_project.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iti_project.FingerPrint.FingerPrint;
import com.example.iti_project.Home;
import com.example.iti_project.R;


public class Login extends AppCompatActivity {
    EditText login_username, login_pass;
    Button login_btn;
    TextView new_account, login_register_with, login_back_arrow;
    Boolean check_pass;
    login_Register_Database lll;
    ImageView facebook, google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_back_arrow = findViewById(R.id.login_back_arrow);
        login_username = findViewById(R.id.et_login_username);
        login_pass = findViewById(R.id.et_login_password);
        login_btn = findViewById(R.id.btn_login);
        new_account = findViewById(R.id.tv_login_register);
        login_register_with = findViewById(R.id.tv_login_register_with);
        facebook = findViewById(R.id.iv_login_facebook);
        google = findViewById(R.id.iv_login_google);
        lll = new login_Register_Database(this);
        // back to fingerprint activity
        login_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, FingerPrint.class);
                startActivity(intent);
            }
        });


        // if don't have an account

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        // login if have an account
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = login_username.getText().toString();
                String pass = login_pass.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(Login.this, "All Fields Required!", Toast.LENGTH_SHORT).show();
                else {
                    check_pass = lll.checkUsernamePassword(user, pass);
                    if (check_pass) {
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}