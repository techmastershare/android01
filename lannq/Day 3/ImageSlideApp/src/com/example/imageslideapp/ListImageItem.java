package com.example.imageslideapp;


public class ListImageItem {
	private String mImageItem;
	private	String mImageTitle;
	
	
	public String getImageItem() {
		return mImageItem;
	}
	public void setImageItem(String imageItem) {
		this.mImageItem = imageItem;
	}
	public String getImageTitle() {
		return mImageTitle;
	}
	public void setmIageTitle(String imageTitle) {
		this.mImageTitle = imageTitle;
	}
	public ListImageItem(String mImageTitle) {
		super();
		this.mImageTitle = mImageTitle;
	}
	
	
}
