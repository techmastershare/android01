package com.davidlee.model;

import com.davidlee.todolist.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

	private Context mContext;
	private Integer[] mImageIds = { R.drawable.a1, R.drawable.a2,
			R.drawable.a4, R.drawable.android,

			R.drawable.a1, R.drawable.a2, R.drawable.a4, R.drawable.android, };

	public GalleryAdapter(Context context) {
		this.mContext = context;
	}

	public int getCount() {
		return mImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView mImageView = new ImageView(mContext);

		mImageView.setImageResource(mImageIds[position]);
		mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
		mImageView.setLayoutParams(new Gallery.LayoutParams(200, 120));

		return mImageView;
	}

}
