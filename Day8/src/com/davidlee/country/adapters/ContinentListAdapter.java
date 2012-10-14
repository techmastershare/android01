package com.davidlee.country.adapters;

import com.davidlee.country.activities.R;
import com.davidlee.country.common.ItemList;
import com.davidlee.country.models.ItemListModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContinentListAdapter extends BaseAdapter {

	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null; 
	private ItemListModel mItemListModel = null;
	
	public ContinentListAdapter(final Context context) {
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ItemListModel getItemListModel() {
		return mItemListModel;
	}

	public void setItemListModel(ItemListModel itemListModel) {
		this.mItemListModel = itemListModel;
	}

	public int getCount() {
		if (mItemListModel == null)
			return 0;
		return mItemListModel.count();
	}

	public Object getItem(int position) {
		if (mItemListModel == null)
			return null;
		return mItemListModel.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.continent_item_view, null);
			viewHolder = new ViewHolder();
			
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		ItemList item = mItemListModel.get(position);
		if (item != null) {
			viewHolder.mTextView.setText(item.getName());
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView mTextView;
	}
}
