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
        todos.add(new ToDoItem("Eat breakfast", "Maybe now?"));
        todos.add(new ToDoItem("Eat lunch", "At 12NN"));
        todos.add(new ToDoItem("Program project", "project.this"));
        todos.add(new ToDoItem("Eat snacks", "when Jane offers you some"));
        todos.add(new ToDoItem("Eat dinner", "or probably not"));
        todos.add(new ToDoItem("Develop apps", "for GA"));
        todos.add(new ToDoItem("Sleep", "until you want to"));

        List<ToDoItem> todos2 = new LinkedList<>();
        todos2.add(new ToDoItem("Eat breakfast", "But cook it first!"));
        todos2.add(new ToDoItem("Eat lunch"));
        todos2.add(new ToDoItem("Program project"));
        todos2.add(new ToDoItem("Eat snacks"));
        todos2.add(new ToDoItem("Eat dinner"));
        todos2.add(new ToDoItem("Develop apps"));
        todos2.add(new ToDoItem("Sleep"));

        mToDoLists.add(new ToDoList("Reminder", todos));
        mToDoLists.add(new ToDoList("Other stuff", todos2));
    }

    public List<ToDoItem> getToDoItems(int i){
        return mToDoLists.get(i).getToDoList();
    }
}
