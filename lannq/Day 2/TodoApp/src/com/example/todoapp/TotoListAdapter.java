package com.example.todoapp;

import java.util.List;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TotoListAdapter extends BaseAdapter {

	private Context mContext;
	private List<TodoItem> mTodoList;

	public TotoListAdapter(Context context, List<TodoItem> todoList) {
		super();
		this.mContext = context;
		this.mTodoList = todoList;
	}

	@Override
	public int getCount() {
		return mTodoList.size();
	}

	@Override
	public Object getItem(int pos) {
		return mTodoList.get(pos);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// get Item
		TodoItem item = mTodoList.get(position);
		ViewHolder viewHolder = new ViewHolder();
		// inflating list view layout if null
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.todolist, null);
			
			TextView tvTodoContent = (TextView) convertView
					.findViewById(R.id.todo_content_textview);
			TextView tvDeadline = (TextView) convertView
					.findViewById(R.id.deadline_textview);
			TextView tvStatus = (TextView) convertView
					.findViewById(R.id.status_textview);
			viewHolder.todoContent = tvTodoContent.getText().toString();
			viewHolder.deadline = tvDeadline.getText().toString();
			viewHolder.status = tvStatus.getText().toString();
			convertView.setTag(viewHolder);
		}
		else {
			convertView.getTag();
		}

		// Set todo content
		TextView tvTodoContent = (TextView) convertView
				.findViewById(R.id.todo_content_textview);
		tvTodoContent.setText(item.getTodoContent());

		// Set Deadline
		TextView tvDeadline = (TextView) convertView
				.findViewById(R.id.deadline_textview);
		tvDeadline.setText(item.getDeadline());

		// Set Status
		TextView tvStatus = (TextView) convertView
				.findViewById(R.id.status_textview);
		tvStatus.setText(item.getStatus());

		if (item.getStatus().equalsIgnoreCase(TodoItem.DONE)) {
			tvStatus.setTextColor(Color.GREEN);
		} else {
			tvStatus.setTextColor(Color.RED);
		}
		return convertView;
	}

	public class ViewHolder {
		String todoContent;
		String status;
		String deadline;
	}
}
