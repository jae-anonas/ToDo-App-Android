package com.example.roosevelt.todo_app_android;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class ToDoItemsPage extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ToDoList mToDoList;
    ToDoItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_items_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mToDoList = ToDoData.getInstance().mToDoLists.get(getIntent().getIntExtra("listIndex", 0));


        adapter = new ToDoItemRecyclerViewAdapter(mToDoList.getToDoList());
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInput().show();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private AlertDialog getUserInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ToDoItemsPage.this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_get_title_layout, null);
        final EditText userInput = (EditText) v.findViewById(R.id.listname);
        userInput.setHint("Enter task here");
        builder.setView(v)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code for adding to ToDoList
                        ToDoItem toDoItem = new ToDoItem(userInput.getText().toString());
                        addToDoList(toDoItem);
                        adapter.notifyDataSetChanged();
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
    private void addToDoList(ToDoItem list){
        mToDoList.getToDoList().add(list);
    }

}
