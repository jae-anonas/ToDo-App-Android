package com.example.roosevelt.todo_app_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by roosevelt on 7/7/16.
 */
public class ToDoListViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTitleView;
    public TextView mDescriptionView;

    public ToDoListViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.colorbox);
        mTitleView = (TextView) itemView.findViewById(R.id.text1);
        mDescriptionView = (TextView) itemView.findViewById(R.id.text2);
    }
}
