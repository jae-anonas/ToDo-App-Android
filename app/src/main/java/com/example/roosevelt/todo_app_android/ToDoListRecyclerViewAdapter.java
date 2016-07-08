package com.example.roosevelt.todo_app_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

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
        final ToDoList toDoList = mToDoLists.get(pos);
        holder.mTitleView.setText(toDoList.getTitle());
        holder.mDescriptionView.setText(toDoList.getDateAsString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open its list of todos
                //for now, just open the activity
                Intent intent = new Intent(view.getContext(), ToDoItemsPage.class);
                intent.putExtra("listIndex", pos);
                view.getContext().startActivity(intent);

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editListDetail(toDoList, view).show();

                return false;
            }
        });
    }


    private AlertDialog editListDetail(final ToDoList toDoList, View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View v = inflater.inflate(R.layout.dialog_edit_delete_list, null);
        final EditText userInputNewTitle = (EditText) v.findViewById(R.id.title);
        userInputNewTitle.setText(toDoList.getTitle());
        builder.setView(v)
                .setTitle("Edit list name")
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code for adding to ToDoList
                        String listTitle = userInputNewTitle.getText().toString();
                        if (!listTitle.trim().equals("")){
                            //edit list name
                            toDoList.setTitle(listTitle);
                            notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete ToDoList
                        mToDoLists.remove(toDoList);
                        notifyDataSetChanged();
                    }
                });
        return builder.create();
    }


    @Override
    public int getItemCount() {
        return mToDoLists.size();
    }

    private void removeList(int i){
        mToDoLists.remove(i);
    }

}
