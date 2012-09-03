package com.techmaster.slideimage;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.techmaster.todolist.R;

public class AvatarAdapter extends BaseAdapter {
	private Activity context = null;
	private Integer[] pictures = null;
	private int background;

	public AvatarAdapter(Activity activity, Integer[] pictures) {
		context = activity;
		this.pictures = pictures;
		TypedArray typedArray = context
				.obtainStyledAttributes(R.styleable.Gallery);
		background = typedArray.getResourceId(
				R.styleable.Gallery_android_galleryItemBackground, 1);
		typedArray.recycle();
	}

	@Override
	public int getCount() {

		return pictures.length;
	}

	@Override
	public Object getItem(int arg0) {

		return pictures[arg0];
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(pictures[arg0]);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
		imageView.setBackgroundResource(background);

		return imageView;
	}
}
