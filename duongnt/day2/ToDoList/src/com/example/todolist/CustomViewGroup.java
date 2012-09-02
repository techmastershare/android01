package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomViewGroup extends LinearLayout {

	public CheckBox mCb;
	public TextView mWorkContent;
	public TextView mTimeContent;

	public CustomViewGroup(Context context) {
		super(context);

		LayoutInflater li = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list, this, true);

		mCb = (CheckBox) findViewById(R.id.check_work);
        mWorkContent = (TextView) findViewById(R.id.work_content);
        mTimeContent = (TextView) findViewById(R.id.time_content);
	}

}
