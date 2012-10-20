package com.example.shareimageapp.model;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.shareimageapp.R;

public class PhotoListAdapter extends BaseAdapter {
	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null;
	private ArrayList<PhotoModel> mPhotoModelList;

	// Keeping the currently selected item
	int mCurrSelected = -1;

	// Since most of the actions gets the id but needs the position,
	// we'll map Ids to Positions

	public PhotoListAdapter(Context context,
			ArrayList<PhotoModel> photoModelList) {
		super();
		this.mContext = context;
		this.mPhotoModelList = photoModelList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mPhotoModelList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mPhotoModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		PhotoModel photoModel = mPhotoModelList.get(position);
		return 0;
	}
	
	public String getItemIdString(int position) {
		PhotoModel photoModel = mPhotoModelList.get(position);
		return photoModel.getImageId().toString();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			mLayoutInflater = LayoutInflater.from(mContext);
			convertView = mLayoutInflater.inflate(R.layout.photo_model, null);
			holder = new ViewHolder();
			holder.mImageView = (ImageView) convertView
					.findViewById(R.id.photo_imageView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PhotoModel photoItem = mPhotoModelList.get(position);
		if (photoItem.isIsItemSelected()) {
			convertView.setBackgroundColor(Color.BLUE);
		} else {
			convertView.setBackgroundColor(Color.BLACK);
		}
		holder.mImageView.setImageURI(photoItem.getImageId());
		return convertView;
	}

	/**
	 * Setting the item in the argumented position - as selected.
	 * 
	 * @param position
	 * @return
	 */
	public String setSelectable(int position) {
		// The -1 value means that no item is selected
		if (mCurrSelected != -1) {
			PhotoModel photoModel = (PhotoModel) getItem(mCurrSelected);
			photoModel.setIsItemSelected(false);
		}

		// Selecting the item in the position we got as an argument
		if (position != -1) {
			PhotoModel photoModel = (PhotoModel) getItem(position);
			photoModel.setIsItemSelected(true);
			mCurrSelected = position;
		}

		// Making the list redraw
		notifyDataSetChanged();

		return getSelectedId();
	}

	public String getSelectedId() {
		if (mCurrSelected == -1)
			return null;
		else {
			return getItemIdString(mCurrSelected);
		}
	}

	static class ViewHolder {
		ImageView mImageView;
	}
}
