package com.example.iti_project.User_Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.iti_project.R;

public class Custom_dialog_change_password extends AppCompatDialogFragment {
    EditText et_custom_old_password, et_custom_new_password;
    Button btn_custom_edit_change_password, btn_custom_cancel_change_password;
    Custom_Dialog_change_password_Interface custom_dialog_change_password_interface;
    private OnPositiveClickListener_password onPositiveClickListener_password;
    private OnNeutralClickListener_password onNeutralClickListener_password;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_dialog_change_password,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_custom_old_password = view.findViewById(R.id.et_custom_old_password);
        et_custom_new_password = view.findViewById(R.id.et_custom_new_password);
        btn_custom_cancel_change_password = view.findViewById(R.id.btn_custom_cancel_change_password);
        btn_custom_edit_change_password = view.findViewById(R.id.btn_custom_edit_change_password);

        btn_custom_edit_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveClickListener_password.onPositiveButtonClicked_password();
                String old_password = et_custom_old_password.getText().toString();
                String new_password = et_custom_new_password.getText().toString();
                Toast.makeText(getContext(), ""+new_password, Toast.LENGTH_SHORT).show();
                custom_dialog_change_password_interface.apply_new_password(old_password,new_password);
                dismiss();
            }
        });

        btn_custom_cancel_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNeutralClickListener_password.onNeutralButtonClicked_password();
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPositiveClickListener_password) {
            onPositiveClickListener_password = (OnPositiveClickListener_password) context;
        }
        else {
            throw new RuntimeException("please implement listener:Positive");
        }
        if (context instanceof Custom_dialog_change_name.OnNeutralClickListener) {
            onNeutralClickListener_password = (OnNeutralClickListener_password) context;
        }
        else {
            throw new RuntimeException("please implement listener:Neutral");
        }
        custom_dialog_change_password_interface = (Custom_Dialog_change_password_Interface) context;
    }

    public interface OnPositiveClickListener_password {
        void onPositiveButtonClicked_password();
    }
    public interface OnNeutralClickListener_password {
        void onNeutralButtonClicked_password();
    }

    public interface Custom_Dialog_change_password_Interface {
        void apply_new_password(String old_password, String new_password);
    }
}
