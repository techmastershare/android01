package com.example.shareimageapp.model;

import android.net.Uri;

public class PhotoModel {
	private String mName;
	private String mDate;
	private Uri mImageId;
	private boolean mIsItemSelected;

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(String date) {
		this.mDate = date;
	}

	public Uri getImageId() {
		return mImageId;
	}

	public void setImageId(Uri imageId) {
		this.mImageId = imageId;
	}

	public PhotoModel(String name, String date, Uri imageId) {
		super();
		this.mName = name;
		this.mDate = date;
		this.mImageId = imageId;
	}

	public boolean isIsItemSelected() {
		return mIsItemSelected;
	}

	public void setIsItemSelected(boolean isItemSelected) {
		this.mIsItemSelected = isItemSelected;
	}
	
	

}
