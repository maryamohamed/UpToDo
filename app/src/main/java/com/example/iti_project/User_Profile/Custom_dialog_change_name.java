package com.example.iti_project.User_Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.iti_project.Login_Register.User;
import com.example.iti_project.R;

public class Custom_dialog_change_name extends AppCompatDialogFragment {

    EditText et_custom_change_account_name;
    Button btn_custom_cancel_change_name, btn_custom_edit_change_name;
    Custom_Dialog_change_name_Interface custom_dialog_change_name_interface;
    private OnPositiveClickListener onPositiveClickListener;
    private OnNeutralClickListener onNeutralClickListener;

    View view;
    User user;
    String name, password, conf_password;
    Profile profile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_dialog_change_name,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_custom_change_account_name = view.findViewById(R.id.et_custom_change_account_name);
        btn_custom_cancel_change_name = view.findViewById(R.id.btn_custom_cancel_change_name);
        btn_custom_edit_change_name = view.findViewById(R.id.btn_custom_edit_change_name);
        btn_custom_edit_change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveClickListener.onPositiveButtonClicked();
                String new_name = et_custom_change_account_name.getText().toString();
                custom_dialog_change_name_interface.apply_new_name(new_name);
                dismiss();
            }
        });

        btn_custom_cancel_change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNeutralClickListener.onNeutralButtonClicked();
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnPositiveClickListener) {
            onPositiveClickListener = (OnPositiveClickListener) context;
        }
        else {
            throw new RuntimeException("please implement listener:Positive");
        }
        if (context instanceof OnNeutralClickListener) {
            onNeutralClickListener = (OnNeutralClickListener) context;
        }
        else {
            throw new RuntimeException("please implement listener:Neutral");
        }

        custom_dialog_change_name_interface = (Custom_Dialog_change_name_Interface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onPositiveClickListener = null;
        onNeutralClickListener = null;
    }

    public interface OnPositiveClickListener {
        void onPositiveButtonClicked();
    }
    public interface OnNeutralClickListener {
        void onNeutralButtonClicked();
    }

    public interface Custom_Dialog_change_name_Interface {
        void apply_new_name(String new_name);
    }
}
