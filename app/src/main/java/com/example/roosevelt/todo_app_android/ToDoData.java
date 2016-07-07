package com.example.roosevelt.todo_app_android;

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

    }
}
