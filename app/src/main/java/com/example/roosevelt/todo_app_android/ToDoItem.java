package com.example.roosevelt.todo_app_android;

import java.util.Date;


/**
 * Created by roosevelt on 7/4/16.
 */
public class ToDoItem {

    private String mToDo;
    private boolean mStatus;
    private Date mDateTimeCreated;
    private Date mDateTimeDone;

    public ToDoItem(String mToDo) {
        this.mToDo = mToDo;
        this.mStatus = false;
        this.mDateTimeCreated = new Date();
    }

    public String getToDo() {
        return mToDo;
    }

    public void setToDo(String mToDo) {
        this.mToDo = mToDo;
    }

    public boolean isDone() {
        return mStatus;
    }

    public void setStatus(boolean mStatus) {
        if (mStatus)
            mDateTimeDone = new Date();
        this.mStatus = mStatus;
    }

    public Date getDateTimeCreated() {
        return mDateTimeCreated;
    }

    public Date getDateTimeDone() {
        if (mDateTimeCreated == null)
            mDateTimeDone = new Date();
        return mDateTimeDone;
    }



}
