package com.example.slideimageview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomAdapter extends BaseAdapter {

	private Context mContext = null;
	private ArrayList<ImageContent> mArrayListItem = null;

	public MyCustomAdapter(Context context,
			ArrayList<ImageContent> arrayListItem) {
		super();
		this.mContext = context;
		this.mArrayListItem = arrayListItem;
	}

	public int getCount() {
		return mArrayListItem.size();
	}

	public Object getItem(int position) {
		return mArrayListItem.get(position);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		ImageContent item = mArrayListItem.get(position);

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item_view, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.image_view);
			holder.titleView = (TextView) convertView
					.findViewById(R.id.title_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.imageView.setImageResource(item.getmImageId());
		holder.titleView.setText(item.getmTitleView());
		return convertView;
	}

	static class ViewHolder {
		ImageView imageView;
		TextView titleView;
	}

}
