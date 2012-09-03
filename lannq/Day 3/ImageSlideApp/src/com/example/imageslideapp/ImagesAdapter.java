package com.example.imageslideapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ImagesAdapter extends BaseAdapter {

	private Context mContext = null;
	private List<ListImageItem> mListItem = null;
	
	
	
	public ImagesAdapter(Context context, List<ListImageItem> listItem) {
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
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return mListItem.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Get Content
		ListImageItem item = mListItem.get(position);
		
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item, null);
		}
		
		TextView imageTitleTv = (TextView) convertView.findViewById(R.id.itemTitle_textView);
		imageTitleTv.setText(item.getImageTitle());
		return convertView;
	}

}
