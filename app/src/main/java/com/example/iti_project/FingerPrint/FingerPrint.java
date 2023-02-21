package com.example.iti_project.FingerPrint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iti_project.Home;
import com.example.iti_project.Introduction.Start_Intro;
import com.example.iti_project.Login_Register.Login;
import com.example.iti_project.R;

import java.util.concurrent.Executor;

public class FingerPrint extends AppCompatActivity {
    Button use_password;
    ImageView imageView;
    TextView cancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        imageView = findViewById(R.id.img_fingerPrint);
        use_password = findViewById(R.id.btn_fingerprint_usePassword);
        cancel = findViewById(R.id.tv_fingerprint_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FingerPrint.this, Start_Intro.class);
                startActivity(i);
            }
        });

        // Moving to login activity
        use_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FingerPrint.this, Login.class);
                startActivity(i);
            }
        });


        // fingerprint Authentication
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Please Verify")
                        .setDescription("User Authentication")
                        .setNegativeButtonText("Cancel")
                        .build();
                getPrompt().authenticate(promptInfo);
            }
        });

    }
    private BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(FingerPrint.this, errString, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent_finger_print_to_home = new Intent(FingerPrint.this, Home.class);
                startActivity(intent_finger_print_to_home);
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(FingerPrint.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

            }
        };
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;
    }

}