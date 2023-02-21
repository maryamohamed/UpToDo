package com.example.iti_project.Add_Task;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iti_project.Home;
import com.example.iti_project.R;

import java.util.ArrayList;

public class Task_Adapter extends RecyclerView.Adapter<Task_Adapter.Task_ViewHolder> {

    private ArrayList<TASK> tasks;
    private Database database;
    private Home activity;
    private Context context;
    RecyclerView recyclerView;

    public Task_Adapter( Database database, Home activity) {
        this.database = database;
        this.activity = activity;
    }

    public Task_Adapter(ArrayList<TASK> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    public Task_Adapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public Task_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutom_task_layout,null,false);
        Task_ViewHolder task_viewHolder = new Task_ViewHolder(view);
        return task_viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Task_ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        database.write_into_database();

        TASK task = tasks.get(position);
        holder.custom_tv_task_name.setText(task.getName());
        holder.custom_tv_task_description.setText(task.getDescription());
        holder.custom_tv_date.setText(task.getDate());
        holder.custom_tv_time.setText(task.getTime());

        holder.custom_btn_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pop_up_menu(v, position);
            }
        });

    }

    public void show_pop_up_menu(View view, int position) {
        final TASK task = tasks.get(position);
        PopupMenu popupMenu = new PopupMenu(activity, view);
        popupMenu.getMenuInflater().inflate(R.menu.options, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.Complete:
                    AlertDialog.Builder builder_complete = new AlertDialog.Builder(activity);
                    builder_complete.setTitle("Task Completed?");
                    builder_complete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Delete_task(position);
                            Show_complete_dialog();
                        }
                    });
                    builder_complete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder_complete.show();
                    break;
                case R.id.Update:
                    AlertDialog.Builder builder_update = new AlertDialog.Builder(activity);
                    builder_update.setTitle("Update this task?");
                    builder_update.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Fragment_create_task fragment_create_task = new Fragment_create_task();
                            fragment_create_task.show(activity.getSupportFragmentManager(), Fragment_create_task.TAG);
                            Update_task(position);
                        }
                    });
                    builder_update.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder_update.show();
                    break;
                case R.id.Delete:
                    AlertDialog.Builder builder_delete = new AlertDialog.Builder(activity);
                    builder_delete.setTitle("Delete this task");
                    builder_delete.setMessage("Are you sure ?");
                    builder_delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Delete_task(position);
                        }
                    });
                    builder_delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder_delete.show();
                    break;
            }
            return false;
        });
        popupMenu.show();
    }

    public void Show_complete_dialog () {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.task_completed);
        Button Done = dialog.findViewById(R.id.completed_btn_done);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {

        return tasks.size();
    }


    public void set_tasks (ArrayList<TASK> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public void Delete_task(int position) {
        TASK task = tasks.get(position);
        database.delete_task(task.getId());
        tasks.remove(position);
        notifyItemRemoved(position);
    }

    public void Update_task(int position) {
        TASK task = tasks.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", task.getId());
        bundle.putString("name", task.getName());
        bundle.putString("description", task.getDescription());
        bundle.putString("date", task.getDate());
        bundle.putString("time", task.getTime());
        Fragment_create_task fragment_create_task = new Fragment_create_task();
        fragment_create_task.setArguments(bundle);
        fragment_create_task.show(activity.getSupportFragmentManager(), Fragment_create_task.TAG);
    }


    public static class Task_ViewHolder extends RecyclerView.ViewHolder {
        // variables
        TextView custom_tv_task_name, custom_tv_task_description, custom_tv_complete;
        TextView custom_tv_date, custom_tv_time;
        Button custom_btn_options;
        public Task_ViewHolder(@NonNull View itemView) {
            super(itemView);
            // inflate
            custom_tv_task_name = itemView.findViewById(R.id.custom_tv_task_name);
            custom_tv_task_description = itemView.findViewById(R.id.custom_tv_task_description);
            custom_tv_complete = itemView.findViewById(R.id.custom_tv_complete);
            custom_tv_date = itemView.findViewById(R.id.custom_tv_date);
            custom_tv_time = itemView.findViewById(R.id.custom_tv_time);
            custom_btn_options = itemView.findViewById(R.id.custom_btn_options);
        }
    }

}
