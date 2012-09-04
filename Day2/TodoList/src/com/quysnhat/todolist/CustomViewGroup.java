package com.quysnhat.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomViewGroup extends LinearLayout {

	public CheckBox cb; 
    public TextView taskName; 
    public TextView createTime;
    public TextView status;
    
	public CustomViewGroup(Context context) {
		super(context);
		
		LayoutInflater li = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list, this, true);
		
		cb = (CheckBox) findViewById(R.id.chek_task);
		taskName = (TextView) findViewById(R.id.task_name_textView);
		createTime = (TextView) findViewById(R.id.create_time_textView);
		status = (TextView) findViewById(R.id.status_textView);
		
	}

}
