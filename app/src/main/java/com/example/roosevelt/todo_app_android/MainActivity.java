package com.example.roosevelt.todo_app_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_lists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        List<ToDoList> toDoLists = new LinkedList<>();
        toDoLists.add(new ToDoList("To do list 1"));
        toDoLists.add(new ToDoList("To do list 2"));
        toDoLists.add(new ToDoList("To do list 3"));
        toDoLists.add(new ToDoList("To do list 4"));
        toDoLists.add(new ToDoList("To do list 5"));
        toDoLists.add(new ToDoList("To do list 6"));

        ToDoListRecyclerViewAdapter adapter = new ToDoListRecyclerViewAdapter(toDoLists);
        mRecyclerView.setAdapter(adapter);

    }
}
