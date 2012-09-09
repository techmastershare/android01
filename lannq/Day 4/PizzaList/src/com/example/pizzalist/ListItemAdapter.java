package com.example.pizzalist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItemAdapter extends BaseAdapter{

	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null;
	private ArrayList<CategoryItem> mListItem = null;
	
	
	public ListItemAdapter(Context context, ArrayList<CategoryItem> listItem) {
		super();
		this.mContext = context;
		this.mListItem = listItem;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			mLayoutInflater = LayoutInflater.from(mContext);
			convertView = mLayoutInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.mImageView = (ImageView) convertView.findViewById(R.id.item_imageView);
			holder.mTitleTextView = (TextView) convertView.findViewById(R.id.itemTitle_textView);
			holder.mDescriptionTextView = (TextView) convertView.findViewById(R.id.itemDes_textView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CategoryItem categoryItem = mListItem.get(position);
		holder.mImageView.setImageResource(categoryItem.getImageId());
		holder.mTitleTextView.setText(categoryItem.getItemTitle());
		holder.mDescriptionTextView.setText(categoryItem.getItemDescription());
		return convertView;
	}
	
	static class ViewHolder {
		ImageView mImageView;
		TextView mTitleTextView;
		TextView mDescriptionTextView;
	}

}
