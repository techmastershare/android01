package com.davidlee.scrollview.models;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidlee.scrollview.R;

public class ItemListVodAdapter extends BaseAdapter {

	private static ArrayList<VodEntity> arrListVod;
	private LayoutInflater layoutInflater;
	

	public ItemListVodAdapter(Context context, ArrayList<VodEntity> results) {
		arrListVod = results;
		layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return arrListVod.size();
	}

	public Object getItem(int index) {
		return arrListVod.get(index);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHoler;

		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.layout_vod_list_row,
					null);
			viewHoler = new ViewHolder();
			viewHoler.itemImage = (ImageView) convertView
					.findViewById(R.id.poster);
			viewHoler.txtName = (TextView) convertView
					.findViewById(R.id.name);
			viewHoler.txtTime = (TextView) convertView
					.findViewById(R.id.time);
			viewHoler.txtTimeAgo = (TextView) convertView
					.findViewById(R.id.time_ago);
			
			convertView.setTag(viewHoler);
		} else {
			viewHoler = (ViewHolder) convertView.getTag();
		}

		VodEntity vod = arrListVod.get(position);
		viewHoler.txtName.setText(vod.getName());
		viewHoler.txtTime.setText("Thời lượng: " + vod.getTime() + " phút");
		viewHoler.txtTimeAgo.setText("Cách đây " + vod.getAddedDate() + " tháng");
		viewHoler.itemImage.setImageResource(vod.getImageId());

		return convertView;
	}

	static class ViewHolder {
		TextView txtName;
		TextView txtTime;
		TextView txtTimeAgo;
		ImageView itemImage;
	}

}
