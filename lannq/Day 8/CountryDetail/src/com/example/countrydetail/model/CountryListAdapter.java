package com.example.countrydetail.model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.countrydetail.R;

public class CountryListAdapter extends BaseAdapter{

	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null;
	private CountryListModel mCountryListModel;
	
	public CountryListAdapter(Context context, CountryListModel countryListModel) {
		super();
		this.mContext = context;
		this.mCountryListModel = countryListModel;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCountryListModel.count();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCountryListModel.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.country_item, null);
			holder = new ViewHolder();
			holder.mImageView = (ImageView) convertView.findViewById(R.id.countryFlag_imageView);
			holder.mNameTextView = (TextView) convertView.findViewById(R.id.countryName_textView);
			holder.mRegionTextView = (TextView) convertView.findViewById(R.id.countryRegion_textView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CountryItem countryItem = mCountryListModel.get(position);
		holder.mImageView.setImageResource(countryItem.getFlagImageId());
		holder.mNameTextView.setText(countryItem.getName());
		holder.mRegionTextView.setText(countryItem.getRegion());
		return convertView;
	}
	
	static class ViewHolder {
		ImageView mImageView;
		TextView mNameTextView;
		TextView mRegionTextView;
	}

}
