package com.example.iti_project.Login_Register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iti_project.Home;
import com.example.iti_project.R;


public class Register extends AppCompatActivity {
    EditText register_username, register_pass, register_confirmpass;
    Button register_btn;
    ImageView facebook, google;
    login_Register_Database lll;
    TextView register_register_with, register_back_arrow, have_account;
    String name, password, conf_password;
    User U;
    Boolean check_name;
    boolean res;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register_back_arrow = findViewById(R.id.register_back_arrow);
        register_username = findViewById(R.id.et_register_username);
        register_pass = findViewById(R.id.et_register_password);
        register_confirmpass = findViewById(R.id.et_register_confirm_password);
        register_btn = findViewById(R.id.btn_register);
        facebook = findViewById(R.id.iv_register_facebook);
        google = findViewById(R.id.iv_register_google);
        register_register_with = findViewById(R.id.tv_register_register_with);
        have_account = findViewById(R.id.tv_register_login);
        lll = new login_Register_Database(this);



        // register (creating a new ACCOUNT)
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                name = register_username.getText().toString();
                password = register_pass.getText().toString();
                conf_password = register_confirmpass.getText().toString();
                U = new User(name, password, conf_password);

                if (name.equals("") || password.equals("") || conf_password.equals(""))
                    Toast.makeText(Register.this, "All Fields Required!", Toast.LENGTH_SHORT).show();

                else {

                    if (name.length() > 6 || name.contains("_")) {
                        if (password.length() > 6) {
                            if (password.equals(conf_password)) {
                                check_name = lll.checkUsername(name);
                                if (!check_name) {
                                    res = lll.insertUser(U);
                                    if (res) {
                                        Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Register.this, Home.class);
                                        startActivity(i);

                                    } else {
                                        Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Register.this, "User already exists! please login", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                ShowError(register_confirmpass,"password not match!");

                            }

                        } else {
                            ShowError(register_pass,"too short! should contain at least 7 characters");
                        }
                    }else {
                        ShowError(register_username, "should be greater than 7 characters and contain _ symbol!");

                    }

                }
            }
        });

        // return back to login activity
        register_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        // if  have an account

        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


    }
    public void ShowError(EditText input,String s){
        input.setError(s);
        input.requestFocus();
    }

}



