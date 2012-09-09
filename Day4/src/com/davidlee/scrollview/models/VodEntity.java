package com.davidlee.scrollview.models;

public class VodEntity {

	private String mId;
	private String mName;
	private String mTime;
	private String mAddedDate;
	private String mPrice;
	private int mImageId;

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		this.mId = id;
	}
	
	public void setName(String name) {
		this.mName = name;
	}

	public String getName() {
		return mName;
	}

	public void setTime(String time) {
		this.mTime = time;
	}

	public String getTime() {
		return mTime;
	}

	public String getAddedDate() {
		return mAddedDate;
	}

	public void setAddedDate(String addedDate) {
		this.mAddedDate = addedDate;
	}
	
	public String getPrice() {
		return mPrice;
	}

	public void setPrice(String price) {
		this.mPrice = price;
	}

	public int getImageId() {
		return mImageId;
	}

	public void setImageUrl(int imageId) {
		this.mImageId = imageId;
	}
	
	public VodEntity() {
	}
	
	public VodEntity(String id, String name, String time, String addedDate, String price, int imageId) {
		this.mId = id;
		this.mName = name;
		this.mTime = time;
		this.mAddedDate = addedDate;
		this.mPrice = price;
		this.mImageId = imageId;
	}
	
}
