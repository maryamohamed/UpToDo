package com.example.iti_project.User_Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iti_project.Login_Register.Register;
import com.example.iti_project.Login_Register.User;
import com.example.iti_project.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Profile extends AppCompatActivity implements
        Custom_dialog_change_name.Custom_Dialog_change_name_Interface, Custom_dialog_change_name.OnPositiveClickListener, Custom_dialog_change_name.OnNeutralClickListener,
        Custom_dialog_change_password.Custom_Dialog_change_password_Interface, Custom_dialog_change_password.OnPositiveClickListener_password, Custom_dialog_change_password.OnNeutralClickListener_password {

    //  Variables  //
    ImageView iv_profile;
    TextView tv_username;
    TextView tv_task_left;
    TextView tv_task_done;
    TextView tv_app_settings;
    TextView tv_change_account_name, tv_change_account_password, tv_change_account_image;

    TextView tv_about_us, tv_faq, tv_help_feedback, tv_support_us, tv_log_out;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        //  inflate  //
        iv_profile = findViewById(R.id.iv_profile);
        tv_username = findViewById(R.id.tv_username);

        tv_task_left = findViewById(R.id.tv_task_left);
        tv_task_done = findViewById(R.id.tv_task_done);

        tv_app_settings = findViewById(R.id.tv_app_Settings);

        tv_change_account_name = findViewById(R.id.tv_change_account_name);
        tv_change_account_password = findViewById(R.id.tv_change_account_password);
        tv_change_account_image = findViewById(R.id.tv_change_account_image);



        tv_change_account_name = findViewById(R.id.tv_change_account_name);
        tv_change_account_password = findViewById(R.id.tv_change_account_password);
        tv_change_account_image = findViewById(R.id.tv_change_account_image);

        tv_about_us = findViewById(R.id.tv_about_us);
        tv_faq = findViewById(R.id.tv_faq);
        tv_help_feedback = findViewById(R.id.tv_help_feedback);
        tv_support_us = findViewById(R.id.tv_support_us);
        tv_log_out = findViewById(R.id.tv_log_out);

        tv_app_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_profile_to_settings = new Intent(Profile.this,App_Settings.class);
                startActivity(intent_profile_to_settings);
            }
        });

        tv_change_account_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Custom_dialog_change_name custom_dialog_change_name = new Custom_dialog_change_name();
                custom_dialog_change_name.show(getSupportFragmentManager(),null);
            }
        });

        tv_change_account_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Custom_dialog_change_password custom_dialog_change_password = new Custom_dialog_change_password();
                custom_dialog_change_password.show(getSupportFragmentManager(),null);
            }
        });

        tv_change_account_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        tv_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_profile_to_about_us = new Intent(Profile.this,About_us.class);
                startActivity(intent_profile_to_about_us);
            }
        });

        tv_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_profile_to_faq = new Intent(Profile.this, FAQ.class);
                startActivity(intent_profile_to_faq);
            }
        });

        tv_help_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tv_support_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tv_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Register.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void apply_new_name(String new_name) {
        tv_username.setText(new_name);
    }

    @Override
    public void onPositiveButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

    @Override
    public void apply_new_password(String old_password, String new_password) {

    }

    @Override
    public void onPositiveButtonClicked_password() {

    }

    @Override
    public void onNeutralButtonClicked_password() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.capture_picture, menu);
        return true;
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    iv_profile.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, System.currentTimeMillis() + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                iv_profile.setImageBitmap(thumbnail);
            }
        }
    }

}