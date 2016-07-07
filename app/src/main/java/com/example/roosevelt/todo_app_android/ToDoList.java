package com.example.roosevelt.todo_app_android;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ToDoList {

    private String mTitle;
    private List<ToDoItem> mToDoList;
    private Date mDateTimeCreated;

    public ToDoList(String mTitle) {
        this.mTitle = mTitle;
        mToDoList = new LinkedList<>();
        mDateTimeCreated = new Date();
    }

    public ToDoList(String mTitle, List<ToDoItem> items) {
        this.mTitle = mTitle;
        mToDoList = items;
        mDateTimeCreated = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDateAsString(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
        return formatter.format(mDateTimeCreated);
    }

    public long getDateAsLong(){
        return mDateTimeCreated.getTime();
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void addToDo(ToDoItem item){
        mToDoList.add(item);
    }

    public void removeToDo(int index){
        if(!mToDoList.isEmpty())
            mToDoList.remove(index);
    }

}
