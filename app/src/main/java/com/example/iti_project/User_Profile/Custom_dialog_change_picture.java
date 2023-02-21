package com.example.iti_project.User_Profile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.iti_project.R;

public class Custom_dialog_change_picture extends AppCompatDialogFragment {
    Button btn_custom_take_picture, btn_custom_import_from_gallery;
    Custom_Dialog_change_picture_Interface custom_dialog_change_picture_interface;

    private OnPositiveClickListener_picture onPositiveClickListener_picture;
    private final int Gallery_Request = 1001;
    public static final int RESULT_OK = -1;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_dialog_change_picture,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_custom_take_picture = view.findViewById(R.id.btn_custom_take_picture);
        btn_custom_import_from_gallery = view.findViewById(R.id.btn_custom_import_from_gallery);

        btn_custom_take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveClickListener_picture.onPositiveButtonClicked_picture();
            }
        });

        btn_custom_import_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveClickListener_picture.onPositiveButtonClicked_picture();
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,Gallery_Request);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public interface OnPositiveClickListener_picture {
        void onPositiveButtonClicked_picture();
    }

    public interface Custom_Dialog_change_picture_Interface {
        void apply_new_picture_from_camera(int image_from_camera);
        void apply_new_picture_from_gallery(int image_from_gallery);
    }
}
