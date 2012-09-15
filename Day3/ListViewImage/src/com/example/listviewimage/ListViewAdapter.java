package com.example.listviewimage;
import java.util.ArrayList;
import java.util.List;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<Item> {
	
	private ArrayList<Item> mItemList = null;
	
	private Context mContext = null;

	public ListViewAdapter(Context context, int textViewResourceId,
			List<Item> listItem) {
		super(context, textViewResourceId, listItem);
		this.mItemList=(ArrayList<Item>) listItem;
		this.mContext=context;
	}
	public void setItemList(final ArrayList<Item> itemList) {
		this.mItemList = itemList;
	}
	
	public int getCount() {
		return this.mItemList.size();
	}

	public Item getItem(int position) {
		return this.mItemList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		final Item item = (Item) this.getItem(position);
		
		if (convertView == null) {
			LayoutInflater mLayoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mLayoutInflater.inflate(R.layout.item_list_view, null);
			holder = new ViewHolder();
			holder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar_image_view);
			holder.avatarImageView.setImageResource(item.getThumbId());

			holder.titleTextView = (TextView) convertView
					.findViewById(R.id.title_text_view);
			holder.titleTextView.setText(item.getTile());
		
			holder.detailsTextView = (TextView) convertView.findViewById(R.id.details_text_view);
			holder.detailsTextView.setText(item.getDetails());
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	static class ViewHolder {
		ImageView avatarImageView;
		TextView titleTextView;
		TextView detailsTextView;
	}

}
