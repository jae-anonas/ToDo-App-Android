package com.example.roosevelt.todo_app_android;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

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
        final ToDoItem todo = mToDoItems.get(pos);
        holder.mTitleView.setText(todo.getToDo());
//        holder.mDescriptionView.setText(todo.getDateTimeCreated().toString());
        holder.mDescriptionView.setText(todo.getDescription());
        holder.mCheckBoxStatus.setChecked(todo.isDone());
        holder.mImageView.setBackgroundResource(todo.getColor());
        holder.mCheckBoxStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean selected = !todo.isDone();
                CheckBox cbo = (CheckBox) compoundButton;
                cbo.setChecked(selected);
                todo.setStatus(selected);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editListDetail(todo, view).show();
                return false;
            }
        });
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTagColor(todo, view).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }


    private AlertDialog editListDetail(final ToDoItem toDoItem, View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View v = inflater.inflate(R.layout.dialog_edit_delete_item, null);
        final EditText userInputNewTitle = (EditText) v.findViewById(R.id.title);
        final EditText userInputNewDesc = (EditText) v.findViewById(R.id.desc);
        userInputNewTitle.setText(toDoItem.getToDo());
        userInputNewDesc.setText(toDoItem.getDescription());
        builder.setView(v)
                .setTitle("Edit list name")
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code for adding to ToDoList
                        String listTitle = userInputNewTitle.getText().toString();
                        String listDesc = userInputNewDesc.getText().toString();
                        if (!listTitle.trim().equals("")){
                            //edit list name
                            toDoItem.setToDo(listTitle);
                            toDoItem.setDescription(listDesc);
                            notifyDataSetChanged();
                        }
                        else{
                            //make toast
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
                        mToDoItems.remove(toDoItem);
                        notifyDataSetChanged();
                    }
                });
        return builder.create();
    }

    private AlertDialog setTagColor(final ToDoItem toDo, final View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Choose a color Tag")
                .setItems(R.array.colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case 0:
                                toDo.setColor(R.color.green);
                                break;
                            case 1:
                                toDo.setColor(R.color.yellow);
                                break;
                            case 2:
                                toDo.setColor(R.color.red);
                                break;
                            default:
                                toDo.setColor(R.color.green);
                        }
                        notifyDataSetChanged();
                    }
                });

        return builder.create();

    }
}
