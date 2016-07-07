package com.example.roosevelt.todo_app_android;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ToDoListRecyclerViewAdapter adapter;
    List<ToDoList> toDoLists;
    ToDoData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wrapper);

        //Initialize data
        data = ToDoData.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_lists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new ToDoListRecyclerViewAdapter(data.mToDoLists);
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //insert code for adding ToDoList
                getUserInput().show();
            }
        });

    }

    private AlertDialog getUserInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_get_title_layout, null);
        final EditText userInput = (EditText) v.findViewById(R.id.listname);
        userInput.setHint("Enter name of list here");
        builder.setView(v)
                .setTitle("Create a new to do list")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code for adding to ToDoList
                        String title = userInput.getText().toString();
                        if(title.trim().equals("")){
                            Toast.makeText(v.getContext(), "You can't create a list with no title.", Toast.LENGTH_SHORT).show();
                        } else {
                            ToDoList toDoList = new ToDoList(title);
                            addToDoList(toDoList);
                            adapter.notifyDataSetChanged();
                        }
                        Log.e("okaaaaaay", "reached this");
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        return builder.create();
    }


    private void addToDoList(ToDoList list){
        data.mToDoLists.add(list);
    }
}
