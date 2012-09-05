package com.quysnhat.todolist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TodoListAdapter extends BaseAdapter {
	
	private static ArrayList<Task> taskArrayList;
	private Callback mCallback = null;
	
	private LayoutInflater mInflater;
	
	public interface Callback{
		public void OnTaskNameClick(final Task task);
	}
	
	public void setCallback(final Callback callback){
		this.mCallback=callback;
	}
	
	public TodoListAdapter(Context context, ArrayList<Task> tasks){
		taskArrayList = tasks;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return taskArrayList.size();
	}

	public Object getItem(int position) {
		return taskArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		final Task task = (Task) getItem(position);
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.taskName = (TextView) convertView.findViewById(R.id.task_name_view);
			holder.createTime = (TextView) convertView.findViewById(R.id.createTime_view);
			holder.status = (TextView) convertView.findViewById(R.id.status_view);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.chek_task);

			holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Task task = (Task) buttonView.getTag();
					task.setChecked(isChecked);
				}
			});
			
			holder.taskName.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					if(mCallback!=null){
						mCallback.OnTaskNameClick(task);
					}
					
				}
			});
			
//			holder.checkBox.setOnClickListener(listener);
			
			convertView.setTag(holder);
			holder.checkBox.setTag(taskArrayList.get(position));
			
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.taskName.setText(taskArrayList.get(position).getTaskName());
		holder.createTime.setText(taskArrayList.get(position).getCreateTime());
		holder.status.setText(taskArrayList.get(position).getStatus());
		holder.checkBox.setChecked(taskArrayList.get(position).isChecked());
		
		return convertView;
	}
	
	private final OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			if (v instanceof CheckBox) {
				CheckBox chk = (CheckBox) v;

				// switch (chk.getId()) {
				// }
				notifyDataSetChanged();
				// notifyDataSetInvalidated();
			}
		}
	};
	
	static class ViewHolder{
		TextView taskName;
		TextView createTime;
		TextView status;
		CheckBox checkBox;
	}
	
	

}
