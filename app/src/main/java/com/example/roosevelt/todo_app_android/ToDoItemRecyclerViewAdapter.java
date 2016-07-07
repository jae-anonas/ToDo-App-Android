package com.example.roosevelt.todo_app_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by roosevelt on 7/7/16.
 */
public class ToDoItemRecyclerViewAdapter extends RecyclerView.Adapter<ToDoItemViewHolder> {

    List<ToDoItem> mToDoItems;

    public ToDoItemRecyclerViewAdapter(List<ToDoItem> mToDoItems) {
        this.mToDoItems = mToDoItems;

    }

    @Override
    public ToDoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.todo_item_layout, parent, false);

        ToDoItemViewHolder viewHolder = new ToDoItemViewHolder(parentView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ToDoItemViewHolder holder, int position) {
        final int pos = position;
        ToDoItem todo = mToDoItems.get(pos);
        holder.mTitleView.setText(todo.getToDo());
        holder.mDescriptionView.setText(todo.getDateTimeCreated().toString());
        holder.mCheckBoxStatus.setChecked(todo.isDone());
        holder.mCheckBoxStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ToDoItem item = mToDoItems.get(pos);
                boolean selected = !item.isDone();
                CheckBox cbo = (CheckBox) compoundButton;
                cbo.setChecked(selected);
                item.setStatus(selected);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }
}
