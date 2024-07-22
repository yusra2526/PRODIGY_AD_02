package com.yusra.todo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> tasks;

    public TaskAdapter(@NonNull Context context, ArrayList<String> tasks) {
        super(context, R.layout.list_item, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView textViewTask = convertView.findViewById(R.id.textViewTask);
        final Button buttonDone = convertView.findViewById(R.id.buttonDone);
        final Button buttonRemove = convertView.findViewById(R.id.buttonRemove);

        final String task = tasks.get(position);
        textViewTask.setText(task);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.set(position, task + " (Done)");
                textViewTask.setTextColor(Color.GRAY);
                buttonDone.setEnabled(false);
                buttonRemove.setEnabled(false);
                notifyDataSetChanged();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
