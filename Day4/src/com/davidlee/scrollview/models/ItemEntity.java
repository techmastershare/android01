package com.davidlee.scrollview.models;

public class ItemEntity {
	
	private int mId = -1;
	private int mImageId = -1;
	
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public int getImageId() {
		return mImageId;
	}
	public void setImageId(int imageId) {
		this.mImageId = imageId;
	}
	
	public ItemEntity(final int id, final int imageId) {
		this.mId = id;
		this.mImageId =  imageId;
	}
	
	public ItemEntity() {
	}
	
}
