package com.example.roosevelt.todo_app_android;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by roosevelt on 7/7/16.
 */
public class ToDoData {

    List<ToDoList> mToDoLists;


    private static ToDoData ourInstance = new ToDoData();

    public static ToDoData getInstance() {
        return ourInstance;
    }

    private ToDoData() {
        mToDoLists = new LinkedList<>();

        List<ToDoItem> todos = new LinkedList<>();
        todos.add(new ToDoItem("Eat breakfast"));
        todos.add(new ToDoItem("Eat lunch"));
        todos.add(new ToDoItem("Program project"));
        todos.add(new ToDoItem("Eat snacks"));
        todos.add(new ToDoItem("Eat dinner"));
        todos.add(new ToDoItem("Develop apps"));
        todos.add(new ToDoItem("Sleep"));

        mToDoLists.add(new ToDoList("Reminder", todos));
        mToDoLists.add(new ToDoList("Other stuff", todos));
    }

    public List<ToDoItem> getToDoItems(int i){
        return mToDoLists.get(i).getToDoList();
    }
}
