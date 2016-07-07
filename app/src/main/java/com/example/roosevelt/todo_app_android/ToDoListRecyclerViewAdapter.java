package com.example.roosevelt.todo_app_android;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by roosevelt on 7/7/16.
 */
public class ToDoListRecyclerViewAdapter extends RecyclerView.Adapter<ToDoListViewHolder> {
    List<ToDoList> mToDoLists;

    public ToDoListRecyclerViewAdapter(List<ToDoList> mToDoLists) {
        this.mToDoLists = mToDoLists;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.list_item_layout, parent, false);

        ToDoListViewHolder viewHolder = new ToDoListViewHolder(parentView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ToDoListViewHolder holder, int position) {
        final int pos = position;
        ToDoList toDoList = mToDoLists.get(pos);
        holder.mTitleView.setText(toDoList.getTitle());
        holder.mDescriptionView.setText(toDoList.getDateAsString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open its list of todos
                //for now, just open the activity
                Intent intent = new Intent(view.getContext(), ToDoItemsPage.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mToDoLists.size();
    }
}
