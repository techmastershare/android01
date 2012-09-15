package com.example.listviewimage;

public class Item {
	private int mThumbId = 0;
	private String mTitle = "";
	private String mDetails = "";
	
		
	public Item(int mThumbId, String mTitle, String mDetails) {
		super();
		this.mThumbId = mThumbId;
		this.mTitle = mTitle;
		this.mDetails = mDetails;
	}
	public void setTitle(final String title) {
		this.mTitle = title;
	}
	public String getTile() {
		return this.mTitle;
	}
	
	public void setDetails(final String details) {
		this.mDetails = details;
	}
	public String getDetails() {
		return this.mDetails;
	}

	public int getThumbId() {
		return mThumbId;
	}

	public void setThumbId(int mThumbId) {
		this.mThumbId = mThumbId;
	}
	
}
