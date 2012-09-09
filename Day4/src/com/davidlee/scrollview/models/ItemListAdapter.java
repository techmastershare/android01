package com.davidlee.scrollview.models;

import com.davidlee.scrollview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ItemListAdapter extends BaseAdapter {
	
	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null;
	private ItemModel mItemModel = null;
	
	public ItemListAdapter(final Context context, final ItemModel itemModel) {
		this.mContext = context;
		mItemModel = itemModel;
		this.mLayoutInflater = LayoutInflater.from(mContext);
	}

	////////////////////////////////////
	public int getCount() {
		return mItemModel.count();
	}

	public Object getItem(int position) {
		return mItemModel.get(position);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_horizontal_list_view, null);
			holder = new ViewHolder();
			holder.mImageView = (ImageView) convertView.findViewById(R.id.poster_image_view);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		ItemEntity item = mItemModel.get(position);
		holder.mImageView.setImageResource(item.getImageId());
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView mImageView;
	}

}
