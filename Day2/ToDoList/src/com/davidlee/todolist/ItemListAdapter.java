package com.davidlee.todolist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemListAdapter extends BaseAdapter {
	
	private static ArrayList<TaskEntity> lstTask;
	private LayoutInflater layoutInflater;
	
	public ItemListAdapter(Context context, ArrayList<TaskEntity> result) {
		lstTask = result;
		layoutInflater = LayoutInflater.from(context);
		
	}

	public int getCount() {
		return lstTask.size();
	}

	public Object getItem(int index) {
		return lstTask.get(index);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			viewHolder.tvCreatedDate = (TextView) convertView.findViewById(R.id.tvCreatedDate);
			viewHolder.tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.tvTitle.setText(lstTask.get(position).getTitle());
		viewHolder.tvCreatedDate.setText(lstTask.get(position).getCreatedDate());;
		viewHolder.tvStatus.setText(lstTask.get(position).getStatus());;
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView tvTitle;
		TextView tvCreatedDate;
		TextView tvStatus;
	}

}
