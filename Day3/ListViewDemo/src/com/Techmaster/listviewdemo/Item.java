package com.Techmaster.listviewdemo;

public class Item {
	private String mTitle = "";
	private String mDetails = "";
	
	public Item(final String title, final String details) {
		this.mTitle = title;
		this.mDetails = details;
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
}