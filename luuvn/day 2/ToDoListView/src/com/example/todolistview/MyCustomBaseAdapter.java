package com.example.todolistview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MyCustomBaseAdapter extends BaseAdapter {

	private static ArrayList<Work> mWorkArrayList;
	private LayoutInflater mInflater;

	public static class ViewHolder {
		CheckBox cbList;
		TextView textWork;
		TextView textTime;
	}

	public MyCustomBaseAdapter(Context context, ArrayList<Work> work) {
		mInflater = LayoutInflater.from(context);
		mWorkArrayList = work;
	}

	public int getCount() {
		return mWorkArrayList.size();
	}

	public Object getItem(int position) {
		return mWorkArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		final Work work = mWorkArrayList.get(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list, null);
			holder = new ViewHolder();
			holder.cbList = (CheckBox) convertView.findViewById(R.id.check_work);
			holder.textTime = (TextView) convertView.findViewById(R.id.hour_view);
			holder.textWork = (TextView) convertView.findViewById(R.id.work_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (work != null) {
//			holder.textWork = (TextView) convertView.findViewById(R.id.work_view);
//			holder.textTime = (TextView) convertView.findViewById(R.id.hour_view);
//			holder.cbList = (CheckBox) convertView.findViewById(R.id.check_work);

			holder.cbList.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					work.setChecked(isChecked);
				}
			});

			holder.textWork.setText(work.getWorkContent());
			holder.textTime.setText(work.getTimeContent());
			holder.cbList.setChecked(work.isChecked());
		}

		return convertView;
	}

}
