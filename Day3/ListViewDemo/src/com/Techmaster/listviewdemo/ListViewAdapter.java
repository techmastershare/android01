package com.Techmaster.listviewdemo;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	
	private ArrayList<Item> mItemList = null;
	private Context mContext = null;
	private Callback mCallback = null;
	
	private LayoutInflater mLayoutInflater = null;
	
	public interface Callback{
		public void OnItemImage(final Item item);
	}
	
	public void setCallback(final Callback callback){
		this.mCallback=callback;
	}
	
	public ListViewAdapter(Context context, final ArrayList<Item> itemList) {
		this.mContext = context;
		this.mItemList = itemList;
		
		// Initialize a layout inflater to create items of the list view
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	public void setItemList(final ArrayList<Item> itemList) {
		this.mItemList = itemList;
	}
	
	public int getCount() {
		return this.mItemList.size();
	}

	public Object getItem(int position) {
		return this.mItemList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		final Item item = (Item) getItem(position);
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_list_view, null);
			holder = new ViewHolder();
			holder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar);
			holder.avatarImageView.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					if(mCallback!=null){
						mCallback.OnItemImage(item);
					}
					
				}
			});
			holder.titleTextView = (TextView) convertView
					.findViewById(R.id.title_text_view);
			holder.detailsTextView = (TextView) convertView.findViewById(R.id.details_text_view);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		//holder.avatarImageView.setText(searchArrayList.get(position).getName());
		holder.titleTextView.setText(item.getTile());
		holder.detailsTextView.setText(item.getDetails());

		return convertView;
	}

	static class ViewHolder {
		ImageView avatarImageView;
		TextView titleTextView;
		TextView detailsTextView;
	}
}
