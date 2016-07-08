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
import android.widget.Toast;

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

        //get list of to-do items from selected ToDoList object
        int index = getIntent().getIntExtra("listIndex", 0);
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);
        mToDoList = ToDoData.getInstance().mToDoLists.get(index);

        adapter = new ToDoItemRecyclerViewAdapter(mToDoList.getToDoList());
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setAlpha(0.45f);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInput().show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private AlertDialog getUserInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ToDoItemsPage.this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_get_title_desc_layout, null);
        final EditText userInputTaskTitle = (EditText) v.findViewById(R.id.todoname);
        final EditText userInputTaskDesc = (EditText) v.findViewById(R.id.tododesc);
        builder.setView(v)
                .setTitle("Create a new task")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code for adding to ToDoList
                        String taskTitle = userInputTaskTitle.getText().toString();
                        String taskDesc = userInputTaskDesc.getText().toString();
                        if (!taskTitle.trim().equals("")){
                            ToDoItem toDoItem = new ToDoItem(taskTitle, taskDesc);
                            addToDoList(toDoItem);
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(v.getRootView().getContext(), "You need a title", Toast.LENGTH_SHORT);
//                            Snackbar.make(v.getRootView(),"You need a title", Snackbar.LENGTH_SHORT)
//                                    .setAction("Action", null).show();
                        }
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
