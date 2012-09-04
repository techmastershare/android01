package com.quysnhat.todolist;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListTaskApdapter extends ArrayAdapter<Task> {
	
	ArrayList<Task> array;
	int resource;
	Context context;

	public ListTaskApdapter(Context context, int textViewResourceId,
			ArrayList<Task> objects) {
		super(context, textViewResourceId, objects);
		
		this.context = context; 
        resource = textViewResourceId; 
        array = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View taskView = convertView;
		
		if(taskView == null){
			taskView = new CustomViewGroup(getContext());
		}
		
		final Task task = array.get(position);
		
		if(task != null){
			TextView taskName = ((CustomViewGroup)taskView).taskName;
			TextView createTime = ((CustomViewGroup)taskView).createTime;
			CheckBox checkWork = ((CustomViewGroup)taskView).cb;
			TextView status = ((CustomViewGroup)taskView).status;
						
			checkWork.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				//@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					task.setChecked(isChecked);					
				}
			});
			
			taskName.setText(task.getTaskName());
			createTime.setText(task.getCreateTime());
			checkWork.setChecked(task.isChecked());
			status.setText(task.getStatus());
		}
		
		return taskView;
	}

}
