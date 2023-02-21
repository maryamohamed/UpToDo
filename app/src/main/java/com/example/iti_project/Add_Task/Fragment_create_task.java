package com.example.iti_project.Add_Task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iti_project.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Objects;

public class Fragment_create_task extends BottomSheetDialogFragment {

    // Variables
    EditText create_et_task_name, create_et_task_description;
    TextView create_et_task_date, create_et_task_time;
    Button create_btn_add_task;

    View view;
    public static final String TAG = "ActionBottomDialog";

    private Database database;


    int day, month, year;
    int minute, hour;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    public static Fragment_create_task newInstance() {
        return new Fragment_create_task();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        create_et_task_name = view.findViewById(R.id.create_et_task_name);
        create_et_task_description = view.findViewById(R.id.create_et_task_description);
        create_et_task_date = view.findViewById(R.id.create_et_task_date);
        create_et_task_time = view.findViewById(R.id.create_et_task_time);
        create_btn_add_task = view.findViewById(R.id.create_btn_add_task);

        create_et_task_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        (view1, year, monthOfYear, dayOfMonth) -> {
                            create_et_task_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            datePickerDialog.dismiss();
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        create_et_task_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                timePickerDialog = new TimePickerDialog(getActivity(),
                        (view12, hourOfDay, minute) -> {
                            create_et_task_time.setText(hourOfDay + ":" + minute);
                            timePickerDialog.dismiss();
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String name = bundle.getString("task");
            String description = bundle.getString("description");
            String date = bundle.getString("date");
            String time = bundle.getString("time");

            create_et_task_name.setText(name);
            create_et_task_description.setText(description);
            create_et_task_date.setText(date);
            create_et_task_time.setText(time);

        }

        database = new Database(getActivity());
        database.write_into_database();

        create_et_task_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("UseRequireInsteadOfGet")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    create_btn_add_task.setEnabled(false);
                    create_btn_add_task.setTextColor(Color.GRAY);
                }
                else{
                    create_btn_add_task.setEnabled(true);
                    create_btn_add_task.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        final boolean finalIsUpdate = isUpdate;

        create_btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = create_et_task_name.getText().toString();
                String description = create_et_task_description.getText().toString();
                String date = create_et_task_date.getText().toString();
                String time = create_et_task_time.getText().toString();

                if (finalIsUpdate) {
                    database.update_task(bundle.getInt("id"), name, description, date, time);
                } else {
                    TASK task = new TASK();
                    task.setName(name);
                    task.setDescription(description);
                    task.setDate(date);
                    task.setTime(time);
                    database.insert_task(task);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener)
            ((DialogCloseListener) activity).handleDialogClose(dialog);
    }
}